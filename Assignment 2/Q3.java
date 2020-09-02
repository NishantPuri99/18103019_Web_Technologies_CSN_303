//Write a program to sort strings (without using library function).
import java.util.Scanner;
public class Q3 {
    public static String StringSorter(String S){
        char CH[] =  S.toCharArray();
        for(int i=0;i<CH.length;i++){
            for(int j=i;j<CH.length;j++){
                if(j>i){
                    if((int)CH[i] > (int)CH[j]){
                        char c = CH[i];
                        CH[i] = CH[j];
                        CH[j] = c;
                    }
                }
            }
        }
        String O = "";
        for (int i = 0; i < CH.length; i++) {
            O+=CH[i];
        }
        return O;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string that you wish to sort");
        String s1 = sc.nextLine();
        String O = StringSorter(s1);
        System.out.println("Sorted String is: "+O);
        sc.close();
    }
}