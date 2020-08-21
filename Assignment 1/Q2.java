//Write a program to read a paragraph from the user and replace specific words mentioned in a vector
//to the following format, For example word Happy is reduced to H****.
import java.util.*;
import java.util.Scanner;
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Paragraph");
        String P = sc.nextLine();
        //sc.close();
        Vector<String> v = new Vector<String>();
        boolean EnterMore = true;
        while(EnterMore){
            System.out.println("Enter Word");
            Scanner sc1 = new Scanner(System.in);
            String x = sc1.nextLine();
            x = x.toLowerCase();
            //sc1.close();
            v.add(x);
            System.out.println("Do you wish to enter more ? (1 for yes, 0 for no)");
            Scanner sc2 = new Scanner(System.in);
            int p = sc2.nextInt();
            //sc2.close();
            if(p==0){
                EnterMore = false;
            }else{
                EnterMore = true;
            }
            // sc1.close();
            // sc2.close();
        }
        String printMe = "";
        for(String L: P.split(" ")){
            String temp = L.toLowerCase();
            if(v.contains(temp)){
                String X = ""; 
                X += L.charAt(0);
                for(int i=1;i<L.length();i++){
                    X+="*";
                }
                printMe+=X;
            }else{
                printMe+=L;
            }
            printMe+=" ";
        }
        System.out.println("Final Paragraph is: ");
        System.out.println(printMe);
        sc.close();
    }
}