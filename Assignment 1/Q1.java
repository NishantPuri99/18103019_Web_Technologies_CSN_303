//Write a program using Java to find the number of times a substring exists in a given string,
//if the order of the characters in the string is irrelevant
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class Q1 {
    public static int CheckAnagram(String s1,String s2){
        HashMap<Character,Integer> map = new HashMap<>();
        if(s1.equals(s2)){
            return 1;
        }
        else{
            for(int i=0;i<s1.length();i++){
                if(map.containsKey(s1.charAt(i))){
                    map.put(s1.charAt(i), map.get(s1.charAt(i))+1);
                }else{
                    map.put(s1.charAt(i), 1);
                }
            }
            for(int i=0;i<s2.length();i++){
                if(map.containsKey(s2.charAt(i))){
                    map.put(s2.charAt(i), map.get(s2.charAt(i))-1);
                }else{
                    map.put(s2.charAt(i), 1);
                }
            }
            boolean a = true;
            for(Map.Entry<Character,Integer> entry :map.entrySet()){
                if(entry.getValue()!=0){
                    a=false;
                    break;
                }
            }
            if(a){
                return 1;
            }else{
                return 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input string:");
        String M = sc.nextLine();
        System.out.println("Enter the substring:");
        String S = sc.nextLine();
        if(S.equals(M)){
            System.out.println("1 Substring exists");
        }else{
            int SS=0;
            for(int i=0;i<M.length()-S.length()+1;i++){
                String ms = M.substring(i, i+S.length());
                SS+=CheckAnagram(S, ms);
            }
            if(SS==0){
                System.out.println("No occurences of given Substring found");
            }else if(SS==1){
                System.out.println("1 Substring exists");
            }else{
                System.out.println(SS+" substring occurences found");
            }

        }
        sc.close();
    }
}