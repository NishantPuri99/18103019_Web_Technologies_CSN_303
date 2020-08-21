//WAP using Java to check if two strings/numbers are anagrams of each other 
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First String");
        String s1 = sc.nextLine();
        System.out.println("Enter Second String");
        String s2 = sc.nextLine();
        HashMap<Character,Integer> map = new HashMap<>();
        if(s1.length()!=s2.length() || s1.length()==0 || s2.length()==0){
            System.out.println(s1+" and "+s2+" are Not Anagrams!");
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
                System.out.println(s1+" and "+s2+" are Valid Anagarms");
            }else{
                System.out.println(s1+" and "+s2+" are Not Anagrams!");
            }
        }
        sc.close();
    }
}