/*
Write  a  program  to create  a  simple  counting  thread.  
It  will  count  to  100,  pausing  one second  between  each  number.  
Also,  in  keeping  with  counting  theme,  it  will  output  a  string every ten number.
*/
class Counter extends Thread
    {
        public void run()
        {	
            try
            {	
                System.out.println("Counting numbers from "+1+" to "+10);
                for(int counter = 1; counter <= 100; counter++)
                {
                    System.out.print("["+counter+"]");
                    // printing a statement after every 10 numbers
                    if(counter%10 == 0)
                    {
                        System.out.println("\nCounted till "+counter+"\n");
                        if(counter!=100){
                            System.out.println("Counting numbers from "+(counter+1)+" to "+(counter+10));
                        }
                    }
                    
                    // to pause for 1 second between each number
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException ie)
            {
                Thread.currentThread().interrupt();
            }
                    
        }
    }
public class A1 {
    public static void main(String[] args) {
		//creating an object of counter class
		Counter count_to_100 = new Counter();
		// calling the start method of Thread class
		count_to_100.start();
    }
}
