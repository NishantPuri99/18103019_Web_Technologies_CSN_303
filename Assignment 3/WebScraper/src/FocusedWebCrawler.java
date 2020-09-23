import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class FocusedWebCrawler {

	public static void main(String[] args) {
		System.out.println("Web Crawler Running....");
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Faculty Data");
        System.out.println("Writing to Excel File");
		Document document;
		try {
			document = Jsoup.connect("http://www.pec.ac.in/faculty-index").get();

			Element table = document.select("table").get(0);
			Elements rows = table.select("tr");
			XSSFRow row;
			for(int i=0; i<rows.size(); i++)
			{
				Element r = rows.get(i);
				row = spreadsheet.createRow(i);
				Elements cols = r.select("td");
				if(cols.size() <= 1) {
					cols = r.select("th");
				}
				Cell cell = null;
				for(int j=1;j<cols.size()-1;j++) {
					cell = row.createCell(j-1);
					cell.setCellValue(cols.get(j).text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream out = null;
        try {
        	out = new FileOutputStream(new File("Faculty_Details.xlsx"));	
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
		System.out.println("Done!");
	}
}
