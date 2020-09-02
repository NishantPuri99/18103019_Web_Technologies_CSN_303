//Write a program to implement counting sort 
//(with input in the range 0 to 20 and input can be repeated multiple times)
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Q2 {
    public static int[] CountingSort(int[] arr){
        int N = arr.length;
        int frequency[] = new int[21];
        for(int i=0;i<N;i++){
            frequency[arr[i]]++;    
        }
        int index[] = new int[21];
        index[0] = frequency[0];
        for(int i=1;i<20;i++){
            index[i] = frequency[i] + index[i-1];
        }
        ArrayList<Integer> O = new ArrayList<Integer>();
        for(int i=0;i<21;i++){
            while(frequency[i]>0){
                O.add(i);
                index[i]--;
                frequency[i]--;
            }
        }
        for(int i=0;i<O.size();i++){
           arr[i] = O.get(i);
        }
        return arr;
    }
    public static void main(String[] args) {
        System.out.println("Enter the number of elements in the array that you wish to sort");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] IPArray = new int[N];
        System.out.println("Do you want the array elements to be random (Y or N ?)");
        char C = sc.next().charAt(0);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<21; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        if(C == 'Y' || C == 'y'){
            for(int i=0;i<N;i++){
                IPArray[i] = list.get(i)%21;
            }
        }else{
            System.out.println(("Please enter "+N+" numbers between 0 and 20 (Numbers can be repeated)"));
            for(int i=0;i<N;i++){
                int number = sc.nextInt();
                IPArray[i] = number;
            }
        }
        System.out.print("\nInput array:    ");
        for(int i=0;i<N;i++){
            System.out.print(IPArray[i]+" ");
        }
        System.out.println(" is to be sorted using Counting Sort");
       int[] OPArray =  CountingSort(IPArray);
        System.out.print("Sorted array:   ");
        for(int i=0;i<N;i++){
            System.out.print(OPArray[i]+" ");
        }
        sc.close();
    }
}