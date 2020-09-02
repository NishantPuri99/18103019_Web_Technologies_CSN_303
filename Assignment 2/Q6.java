//Write  a ConsoleProgram that  reads  in  a  number  from  the  user  and  then 
//displays  the  Hailstone sequence  for  that  number  
//(Pick some positive  integer and call it n. 
//If n is even, divide it by two. If n is odd, multiply it by three and add one. 
//Continue this process until n is equal to one)
import java.util.Scanner;
public class Q6 {
  public static void main(String[] args) {
    System.out.println("Enter the number who's Hailstone sequence you wish to print");
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    if(N >= Integer.MAX_VALUE){
      while(N >= Integer.MAX_VALUE){
        System.out.println("Input to Large. Please enter an Integer less than "+Integer.MAX_VALUE);
        N = sc.nextInt();
      }      
    }
    if(N<=0){
      while(N<=0){
        System.out.println("Wrong input. Please enter a positive Integer");
        N = sc.nextInt();
      }
    }
    sc.close();
    System.out.println("Hailstone sequence of "+N+" is:");
    System.out.print(N+" ");
    while(N!=1){
        if(N > Integer.MAX_VALUE || N<=0){
          System.out.println("Current Value Exceeded Maximum Integer value, exiting...");
          break;
        }
        if(N%2==0){
            N/=2;
        }else{
            N*=3;
            N++;
        }
        System.out.print(N+" ");
    }
  }  
}