//Write a java program to compare two strings lexicographically (without using library function).
import java.util.Scanner;
public class Q1{
    public static boolean compareStrings(String s1,String s2){
        for(int i=0,j=0;i<s1.length() && j<s2.length();i++,j++){
            if(s1.charAt(i) > s2.charAt(j)){
                return true;
            }else if(s1.charAt(i) < s2.charAt(j)){
                return false;
            }
        } 
        if(s1.length() > s2.length()){
            return true;
        }else{
            return false;
        }   
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter string 1");
        String s1 = sc.nextLine();
        System.out.println("Please enter string 2");
        String s2 = sc.nextLine();
        if(compareStrings(s1,s2)){
            System.out.println(s1+" is lexicographically larger");
        }else{
            System.out.println(s2+" is lexicographically larger");
        }
        sc.close();
    }
}