import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
public class Scraper {
	public static void ScrapPTags(String URL,XSSFSheet spreadsheet,int rowNum,int depth) {
		if(depth>1) {
			return;
		}
		depth++;
		Document docs = null;
		try {
			docs = Jsoup.connect(URL).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").ignoreHttpErrors(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Elements atags = docs.select("a[href]");
		Elements ptags = docs.getElementsByTag("p");
		XSSFRow row;
		for(Element tag:ptags) {
        	String s = tag.text();
        	if(s.length()>1) {
        		row = spreadsheet.createRow(rowNum++);
            	Cell cell = row.createCell(0);
            	cell.setCellValue("<p>");
            	cell = row.createCell(1);
            	cell.setCellValue(tag.text());
            	cell = row.createCell(2);
            	cell.setCellValue("</p>");
        	}
        }
        for(Element tag:atags) {
        	String url = tag.absUrl("href");
        	if(!(url.endsWith(".pdf")||url.endsWith("doc"))) {
        		ScrapPTags(url,spreadsheet,rowNum,depth);
        	}
	    }
	}
	public static void ScrapATags(String URL,XSSFSheet spreadsheet,int rowNum,int depth) {
		if(depth>1) {
			return;
		}
		depth++;
		Document docs = null;
		try {
			docs = Jsoup.connect(URL).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").ignoreHttpErrors(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Elements atags = docs.select("a[href]");
		XSSFRow row;
        for(Element tag:atags) {
        	String s = tag.text();
        	if(s.length()>1) {
        		row = spreadsheet.createRow(rowNum++);
            	Cell cell = row.createCell(0);
            	String s2 = tag.text();
            	String url = tag.absUrl("href");
            	if(url.length()>0 && !(url.endsWith("pdf")||url.endsWith("doc") )) {
            		if(s2.length()>0) {
                		cell.setCellValue(s2);
                	}else {
                		cell.setCellValue("No Text");                    		
                	}
                	cell = row.createCell(1);
                	cell.setCellValue(url);
            	}
            	if(url.endsWith("pdf")||url.endsWith("doc")) {
            		if(s2.length()>0) {
                		cell.setCellValue(s2);
                	}else {
                		cell.setCellValue("No Text");                    		
                	}
                	cell = row.createCell(1);
                	cell.setCellValue(url);
                	 URL urli=null;
					try {
						urli = new URL(url);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     ReadableByteChannel rbc=null;
					try {
						rbc = Channels.newChannel(urli.openStream());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                     FileOutputStream fos;
					try {
						fos = new FileOutputStream("D:\\PEC\\THIRD YEAR\\SEMESTER 5\\Web Tech\\Assignment3_18103019\\WebScraper\\Downloaded Files"+url.toString().substring(url.toString().lastIndexOf('/')));
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
						fos.close();
	                     rbc.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     
                	return;
            	}
            }
	        ScrapATags(tag.absUrl("href"),spreadsheet,rowNum,depth);
	     }
	}
	public static void main( String[] args ) throws FileNotFoundException{  
        Document doc = null;
		try {
			doc = Jsoup.connect("https://pec.ac.in/").ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").ignoreHttpErrors(true).get();
			//System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error Caught");
			e.printStackTrace();
		}  
        try {
            FileWriter myWrr = new FileWriter("HomePageData.txt");
            myWrr.write(doc.toString());
            myWrr.close();
            System.out.println("Successfully written all of the HomePageData to the a file.");
          	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
         }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("ParaTags");
        System.out.println("Now writing all the Para Tags to an excel file...");
        ScrapPTags("https://pec.ac.in/",spreadsheet,0,0);
        System.out.println("Written Para Tags to excel file");
        XSSFSheet spreadsheet2 = workbook.createSheet("AnchorTags");
        System.out.println("Now writing all the Anchor Tags to an excel file...");
        ScrapATags("https://pec.ac.in/",spreadsheet2,0,0);
        System.out.println("Written Anchor Tags to excel file");
        System.out.println("PDF and DOC files have also been downloaded to a folder called \'Downloaded Files\'");
        FileOutputStream out = null;
        try {
        	out = new FileOutputStream(new File("Scrapped_Data.xlsx"));	
        }catch(Exception e) {
        	System.out.println("File already Exists");
        }
        if(out!=null) {
        	 try {
					workbook.write(out);
					out.close();
					System.out.println("Writing to excel file complete");
				} catch (IOException e) {
					e.printStackTrace();
				}
        }else {
        	System.out.println("File already Exists");
        }        
}

}
