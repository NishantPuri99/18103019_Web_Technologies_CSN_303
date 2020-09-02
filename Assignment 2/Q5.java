//Let us define a couple of sets of integers, 
//and let Java compute the set-theoretical operations  
//(union,  intersection  and  complement).  
//We  first  fix our  universe,  which will consist of the 11 elements, 
//Universe = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, A and B will be entered by users. 
//a. Only array can be used as data structure 
//b. Using any efficient data-structure available in Java 
//c. Compare the time to compute the operations
import java.util.Scanner;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;
public class Q5 {
    //Array Approach
    public static boolean exists(int[] arr, int el){
        for(int i=0;i<arr.length;i++){
            if(el == arr[i]){
                return true;
            }
        }
        return false;
    }
    public static int[] unionA(int[] A,int[] B){
        ArrayList<Integer> C = new ArrayList<Integer>();
        for(int i=0;i<A.length;i++){
            C.add(A[i]);
        }
        for (int i = 0; i < B.length; i++) {
            if(!C.contains(B[i])){
                C.add(B[i]);
            }
        }
        int[] arr = new int[C.size()];
        for(int i=0;i<arr.length;i++){
            arr[i] = C.get(i);
        }
        return arr;
    }
    public static int[] intersectionA(int[] A,int[] B){
        ArrayList<Integer> D = new ArrayList<Integer>();
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                if(A[i] == B[j]){
                    D.add(A[i]);
                }
            }
        }
        int[] arr = new int[D.size()];
            for(int i=0;i<arr.length;i++){
                arr[i] = D.get(i);
            }
            return arr;
    }
    public static int[] complementA(int[] U, int[] A){
        int[] arr = new int[U.length - A.length];
        if(arr.length!=0){
            int j=0;
            for(int i=0;i<U.length;i++){
                boolean add = true;
                for(int k=0;k<A.length;k++){
                    if(U[i]==A[k]){
                        add=false;
                        break;
                    }
                }
                if(add){
                    arr[j] = U[i];
                    j++;
                }
            }
        }
        return arr;
    }

    //DS Approach (HashSet)
    public static HashSet<Integer> unionH(HashSet<Integer> A, HashSet<Integer> B){
        HashSet<Integer> C = new HashSet<Integer>();
        Iterator<Integer> ita = A.iterator();
        while(ita.hasNext()){
            C.add(ita.next());   
        }
        Iterator<Integer> itb = B.iterator();
        while(itb.hasNext()){
            int x = itb.next();
            if(!C.contains(x)){
                C.add(x);
            }        
        }
        return C;
    }
    public static HashSet<Integer> intersectionH(HashSet<Integer> A, HashSet<Integer> B){
        HashSet<Integer> D = new HashSet<Integer>();
        Iterator<Integer> ita = A.iterator();
        while(ita.hasNext()){
            int aa = ita.next();
            Iterator<Integer> itb = B.iterator();
            while(itb.hasNext()){
                int bb = itb.next();
                if(aa==bb){
                    D.add(aa);
                    break;
                }
            } 
        } 
        return D;
    }    
    public static HashSet<Integer> complementH(HashSet<Integer> U,HashSet<Integer> A){
        HashSet<Integer> X = new HashSet<Integer>();
        Iterator<Integer> itu = U.iterator();
        while(itu.hasNext()){
            int e = itu.next();
            if(!A.contains(e)){
                X.add(e);
            }        
        }
        return X;
    }
    //Main
    public static void main(String[] args) {
        //Array Approach
        System.out.println("\nARRAY APPROACH");
        long startTimeA = System.nanoTime();
        int U[] = new int [11];//Universe
        for (int i = 0; i < 11; i++) {
            U[i] = i;
        }
        System.out.println("The Universe is:");
        System.out.print('{');
        for (int i = 0; i < U.length; i++) {
            if(i==U.length-1){
             System.out.println(i+"}");
            }else{
                System.out.print(i+", ");
            }
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of elements in set A");
        int nA = sc.nextInt();
        if(nA>11){
            while(nA>11){
                System.out.println("Please choose a number less than 11 (Set A should belong to the Universe)");
                nA = sc.nextInt();
            }
        }
        System.out.println("Enter elements of set A");
        int A[] = new int[nA];
        for (int i = 0; i < A.length; i++) {
            A[i] = -1;
        }
        for (int i = 0; i < nA; i++) {
            int a = sc.nextInt();
            if(a>11 || a<0){
                System.out.println("Element should belong to the Universe {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}. Please Enter again.");
                a = sc.nextInt();
            }
            if(exists(A, a)){
                System.out.println("Element "+a+" already exists in set A. Please enter some other element");
                i--;
                continue;
            }
            else{ 
                A[i] = a;
            }
        }
        System.out.println("Enter Number of elements in set B");
        int nB = sc.nextInt();
        if(nB>11){
            while(nB>11){
                System.out.println("Please choose a number less than 11 (Set B should belong to the Universe)");
                nB = sc.nextInt();
            }
        }
        System.out.println("Enter elements of set B");
        int B[] = new int[nB];
        for (int i = 0; i < B.length; i++) {
            B[i] = -1;
        }
        for (int i = 0; i < nB; i++) {
            int b = sc.nextInt();
            if(b>11 || b<0){
                System.out.println("Element should belong to the Universe {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}. Please Enter again.");
                b = sc.nextInt();
            }
            if(exists(B, b)){
                System.out.println("Element "+b+" already exists in set B. Please enter some other element");
                i--;
                continue;
            }
            else{ 
                B[i] = b;
            }
        }
        //UNION
        System.out.println("\n1. UNION");
        System.out.print("Union of A: {");
        if(A.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < A.length; i++) {
                if(i==A.length-1){
                    System.out.print(A[i]+"}");
                }else{
                    System.out.print(A[i]+", ");
                }
            }
        }
        System.out.print(" and B: {");
        if(B.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < B.length; i++) {
                if(i==B.length-1){
                    System.out.print(B[i]+"}");
                }else{
                    System.out.print(B[i]+", ");
                }
            }
        }
        System.out.println(" is ");
        int[] C = unionA(A,B);
        if(C.length==0){
            System.out.println("{} (Empty Set)");
        }else{
            System.out.print("{");
            for (int i = 0; i < C.length; i++) {
                if(i==C.length-1){
                    System.out.println(C[i]+"}");
                }else{
                    System.out.print(C[i]+", ");
                }
            }
        }
        //INTERSECTION
        System.out.println("\n2. INTERSECTION");
        System.out.print("Intersection of A: {");
        if(A.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < A.length; i++) {
                if(i==A.length-1){
                    System.out.print(A[i]+"}");
                }else{
                    System.out.print(A[i]+", ");
                }
            }
        }
        System.out.print(" and B: {");
        if(B.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < B.length; i++) {
                if(i==B.length-1){
                    System.out.print(B[i]+"}");
                }else{
                    System.out.print(B[i]+", ");
                }
            }
        }
        System.out.println(" is ");
        int[] D = intersectionA(A,B);
        if(D.length==0){
            System.out.println("{} (Empty Set)");
        }else{
            System.out.print("{");
            for (int i = 0; i < D.length; i++) {
                if(i==D.length-1){
                    System.out.println(D[i]+"}");
                }else{
                    System.out.print(D[i]+", ");
                }
            }
        }
        //COMPLEMENT
        System.out.println("\n3. COMPLEMENT");
        System.out.print("Complement of A: {");
        if(A.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < A.length; i++) {
                if(i==A.length-1){
                    System.out.print(A[i]+"}");
                }else{
                    System.out.print(A[i]+", ");
                }
            }
        }
        System.out.println(" is ");
        if(A.length == 11){
            System.out.println("{} (Empty Set)");
        }else{
            int[] Ac = complementA(U,A);
            System.out.print("{");
            for (int i = 0; i < Ac.length; i++) {
                if(i==Ac.length-1){
                    System.out.println(Ac[i]+"}");
                }else{
                    System.out.print(Ac[i]+", ");
                }
            }
        }
        System.out.println();
        System.out.print("Complement of B: {");
        if(B.length==0){
            System.out.print("}");
        }else{
            for (int i = 0; i < B.length; i++) {
                if(i==B.length-1){
                    System.out.print(B[i]+"}");
                }else{
                    System.out.print(B[i]+", ");
                }
            }
        }
        System.out.println(" is ");
        if(B.length==11){
            System.out.println("{} (Empty Set)");
        }
        else{
            int[] Bc = complementA(U,B);
            System.out.print("{");
            for (int i = 0; i < Bc.length; i++) {
                if(i==Bc.length-1){
                    System.out.print(Bc[i]+"}");
                }else{
                    System.out.print(Bc[i]+", ");
                }
            }
        }
        //sc.close();   
        long endTimeA = System.nanoTime();
        long durationInNanoA = (endTimeA - startTimeA);  //Total execution time in nano seconds
        //Hashset Approach
        System.out.println("\n\nHashSet APPROACH");
        long startTimeH = System.nanoTime();
        HashSet<Integer> Uni = new HashSet<Integer>();
        for(int i=0;i<11;i++){
            Uni.add(i);
        }
        System.out.println("The Universe is:");
        System.out.print('{');
        for (int i = 0; i < 11; i++) {
            if(i==Uni.size()-1){
             System.out.println(i+"}");
            }else{
                System.out.print(i+", ");
            }
        }
        System.out.println();
        //Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of elements in set A");
        int nAh = sc.nextInt();
        if(nAh>11){
            while(nAh>11){
                System.out.println("Please choose a number less than 11 (Set A should belong to the Universe)");
                nAh = sc.nextInt();
            }
        }
        System.out.println("Enter elements of set A");
        HashSet<Integer> HA = new HashSet<Integer>();
        for (int i = 0; i < nAh; i++) {
            int a = sc.nextInt();
            if(a>11 || a<0){
                System.out.println("Element should belong to the Universe {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}. Please Enter again.");
                a = sc.nextInt();
            }
            if(HA.contains(a)){
                System.out.println("Element "+a+" already exists in set A. Please enter some other element");
                i--;
                continue;
            }
            else{ 
                HA.add(a);
            }
        }
        System.out.println("Enter Number of elements in set B");
        int nBh = sc.nextInt();
        if(nBh>11){
            while(nBh>11){
                System.out.println("Please choose a number less than 11 (Set B should belong to the Universe)");
                nBh = sc.nextInt();
            }
        }
        System.out.println("Enter elements of set B");
        HashSet<Integer> HB = new HashSet<Integer>();
        for (int i = 0; i < nBh; i++) {
            int b = sc.nextInt();
            if(b>11 || b<0){
                System.out.println("Element should belong to the Universe {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}. Please Enter again.");
                b = sc.nextInt();
            }
            if(HB.contains(b)){
                System.out.println("Element "+b+" already exists in set A. Please enter some other element");
                i--;
                continue;
            }
            else{ 
                HB.add(b);
            }
        }
        //UNION
        System.out.println("\n1. UNION");
        System.out.print("Union of A: {");
        if(HA.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HA.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HA.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.print(" and B: {");
        if(HB.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HB.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HB.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println(" is ");
        HashSet<Integer> HC = new HashSet<Integer>();
        HC = unionH(HA,HB);
        System.out.print("{");
        if(HC.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HC.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HC.size()){ 
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println();
        //INTERSECTION
        System.out.println("\n2. INTERSECTION");
        System.out.print("Intersection of A: {");
        if(HA.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HA.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HA.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.print(" and B: {");
        if(HB.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HB.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HB.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println(" is ");
        HashSet<Integer> HD = new HashSet<Integer>();
        HD = intersectionH(HA,HB);
        if(HD.size()==0){
            System.out.println("{} (Empty Set)");
        }else{
            System.out.print("{");
            Iterator<Integer> it = HD.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HD.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println();
        //COMPLEMENT
        System.out.println("\n3. COMPLEMENT");
        System.out.print("Complement of A: {");
        if(HA.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HA.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HA.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println(" is ");
        if(HA.size() == 11){
            System.out.println("{} (Empty Set)");
        }else{
            HashSet<Integer> HAc = complementH(Uni,HA);
            System.out.print("{");
            Iterator<Integer> it = HAc.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HAc.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println();
        System.out.print("\nComplement of B: {");
        if(HB.size()==0){
            System.out.print("}");
        }else{
            Iterator<Integer> it = HB.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HB.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        System.out.println(" is ");
        if(HB.size() == 11){
            System.out.println("{} (Empty Set)");
        }else{
            HashSet<Integer> HBc = complementH(Uni,HB);
            System.out.print("{");
            Iterator<Integer> it = HBc.iterator();
            int i=0;
            while(it.hasNext()){
                i++;
                if(i==HBc.size()){
                    System.out.print(it.next()+"}");
                }else{
                    System.out.print(it.next()+", ");
                }
            }
        }
        long endTimeH = System.nanoTime();
        long durationInNanoH = (endTimeH - startTimeH);  //Total execution time in nano seconds
        System.out.println("\n-----------------------------------------------------");
        System.out.println("\nTime of execution using Array is: "+(double)durationInNanoA/(double)1000000000.0+" seconds");
        System.out.println("Time of execution using Hashset is: "+(double)durationInNanoH/(double)1000000000.0+" seconds");
        sc.close();
    }
}