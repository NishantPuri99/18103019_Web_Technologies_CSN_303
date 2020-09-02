//Find the smallest n such that Σi = i^2 where
// 1 ≤ i ≤ n is too large to be represented as an int. 
//Maximum Integer Value 2147483647
public class Q4 {
    public static void main(String[] args) {
        int n=1;
        int sum=1;
        long SSquare = 1;
        int smallestN = 1;
        System.out.println("To find out Smallest n such that sumof(i) equals to n^2, \n1 to n and n is too large to be represented as an Integer");
        while(n>0){
            if(SSquare == sum){
                smallestN = n;
                System.out.println("Possible value for N: "+smallestN);
            }
            n++;
            sum+=n;
            SSquare = n*n;
        }
    }
}