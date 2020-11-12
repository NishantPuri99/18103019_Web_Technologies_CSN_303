/*
Write a program that uses multiple threads to find the integer in the range 1 to 10000 that has the largest number of divisors
but for the range 1 to 100000 (or less, if you don't have a fast computer).At the end of the program, output the elapsed.
*/
import java.util.Scanner;
public class A2 {
   private final static int MAX = 100000; //Upper Limit of range of ints to be checked
   private volatile static int MaxNumOfDivs = 0;  //The largest number of divisors found so far. 
   private volatile static int IntWithMaxDivs; //An integer that has the maximum number of divisors seen so far.
   synchronized private static void report(int maxCountFromThread, 
         int intWithMaxFromThread) {
      if (maxCountFromThread > MaxNumOfDivs) {
         MaxNumOfDivs = maxCountFromThread;
         IntWithMaxDivs = intWithMaxFromThread;
      }
   }
   //Above method is called by thread when it completes its computation
   
   /**
    * A thread belonging to this class counts the number of divisors for all
    * the integers in an assigned range of integers.  The range is specified
    * in the constructor.  The thread finds the integer in the range that 
    * has the largest number of divisors, and a number that has that many
    * divisors.  At the end of its computation, the thread reports its answer 
    * by calling the report() method.
    */
   private static class CountDivisorsThread extends Thread {
      int min, max;
      public CountDivisorsThread(int min, int max) {
         this.min = min;
         this.max = max;
      }
      public void run() {
         int maxDivisors = 0;
         int whichInt = 0;
         for (int i = min; i < max; i++) {
            int divisors = countDivisors(i);
            if (divisors > maxDivisors) {
               maxDivisors = divisors;
               whichInt = i;
            }
         }
         report(maxDivisors,whichInt);
      }
   }
   
   /**
    * Finds the number in the range 1 to MAX that has the largest number of
    * divisors, dividing the work among a specified number of threads.
    */
   private static void countDivisorsWithThreads(int numberOfThreads) {
      System.out.println("\nCounting divisors using " + 
            numberOfThreads + " threads...");
      long startTime = System.currentTimeMillis();
      CountDivisorsThread[] worker = new CountDivisorsThread[numberOfThreads];
      int integersPerThread = MAX/numberOfThreads; 
      int start = 1;  // Starting point of the range of ints for first thread.
      int end = start + integersPerThread - 1;   // End point of the range of ints.
      for (int i = 0; i < numberOfThreads; i++) {
         if (i == numberOfThreads - 1) {
            end = MAX;  // Make sure that the last thread's range goes all
                        // the way up to MAX.  Because of rounding, this
                        // is not automatic.
         }
         worker[i] = new CountDivisorsThread( start, end );
         start = end+1;    // Determine the range of ints for the NEXT thread.
         end = start + integersPerThread - 1;
      }
      MaxNumOfDivs = 0;
      for (int i = 0; i < numberOfThreads; i++)
         worker[i].start();
      for (int i = 0; i < numberOfThreads; i++) {
             // Wait for each worker thread to die, because the results
             // are not complete until all threads have completed and
             // reported their results.
         while (worker[i].isAlive()) {
            try {
               worker[i].join();
            }
            catch (InterruptedException e) {
            }
         }
      }
      // To show the time elapsed in doing the computation
      long elapsedTime = System.currentTimeMillis() - startTime;
      System.out.println("\nThe largest number of divisors " + 
            "for numbers between 1 and " + MAX + " is " + MaxNumOfDivs);
      System.out.println("An integer with that many divisors is " + 
            IntWithMaxDivs);
      System.out.println("Total elapsed time:  " + 
            (elapsedTime/1000.0) + " seconds.\n");
   }
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int numberOfThreads = 0;
      while (numberOfThreads < 1 || numberOfThreads > 10) {
         System.out.print("How many threads do you want to use  (1 to 10) ?  ");
         numberOfThreads = in.nextInt();
         if (numberOfThreads < 1 || numberOfThreads > 10)
            System.out.println("Please enter a number from 1 to 10");
      }
      countDivisorsWithThreads(numberOfThreads);
      in.close();
   }
   // Main function gets Number of threads from user and calls the required function
   public static int countDivisors(int N) {
      int count = 0;
      for (int i = 1; i <= N ; i++) {
         if ( N % i == 0 )
            count ++;
      }
      return count;
   }
   // Finds Number of Divisors of N
}
