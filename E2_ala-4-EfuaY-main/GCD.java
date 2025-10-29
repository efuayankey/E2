import java.util.Random;

/**
 * class GCD implements and compares four different algorithms for finding the Greatest Common Divisor
 * contains methods to calculate GCD using brute force, optimized brute force, half-range, and Euclidean approaches
 * also measures execution time and iteration count for performance comparison
 * @author: Efua Yankey
 * @version: 11
 */
public class GCD {
    
    // Time complexity: O(n)
    public static int gcd_1(int m, int n){
        int divisor = 1;
        for(int i=2; i<m && i<n; i++){
            if(m%i == 0 && n%i == 0)
                divisor = i;
        }
        return divisor;
    }
    
    // Time complexity: O(n)
    public static int gcd_2(int m, int n){
        int divisor = 1;
        for(int i=n; i>=1; i--){
            if(m%i == 0 && n%i == 0){
                divisor = i;
                break;
            }
        }
        return divisor;
    }
    
    // Time complexity: O(n/2)
    public static int gcd_3(int m, int n) {
        int divisor = 1;
        if(m%n == 0)
            return n;
        for(int i = n/2; i >= 1; i--){
            if(m%i == 0 && n%i == 0){
                divisor=i;
                break;
            }
        }
        return divisor;
    }
    
    // Time complexity: O(log(n))
    public static int gcd_4(int m, int n){
        if(m%n == 0)
            return n;
        else
            return gcd_4(n, m%n);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        
        System.out.println("Comparing the gcd methods using the execution time");
        System.out.printf("%-8s %-8s %-8s %-8s %-8s %-8s%n", "Number1", "Number2", "gcd_1", "gcd_2", "gcd_3", "gcd_4");
        
        for (int i = 0; i < 20; i++) {
            int num1 = random.nextInt(1000000);
            int num2 = random.nextInt(1000000);
            
            long startTime, endTime, time1, time2, time3, time4;
            
            startTime = System.nanoTime();
            gcd_1(num1, num2);
            endTime = System.nanoTime();
            time1 = endTime - startTime;
            
            startTime = System.nanoTime();
            gcd_2(num1, num2);
            endTime = System.nanoTime();
            time2 = endTime - startTime;
            
            startTime = System.nanoTime();
            gcd_3(num1, num2);
            endTime = System.nanoTime();
            time3 = endTime - startTime;
            
            startTime = System.nanoTime();
            gcd_4(num1, num2);
            endTime = System.nanoTime();
            time4 = endTime - startTime;
            
            System.out.printf("%-8d %-8d %-8d %-8d %-8d %-8d%n", num1, num2, time1, time2, time3, time4);
        }
        
        System.out.println();
        System.out.println("Comparing the gcd methods using the number of iterations");
        System.out.printf("%-8s %-8s %-8s %-8s %-8s %-8s%n", "Number1", "Number2", "gcd_1", "gcd_2", "gcd_3", "gcd_4");
        
        for (int i = 0; i < 20; i++) {
            int num1 = random.nextInt(1000000);
            int num2 = random.nextInt(1000000);
            
            int iter1 = gcd_1_iterations(num1, num2);
            int iter2 = gcd_2_iterations(num1, num2);
            int iter3 = gcd_3_iterations(num1, num2);
            int iter4 = gcd_4_iterations(num1, num2);
            
            System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d%n", num1, num2, iter1, iter2, iter3, iter4);
        }
    }
    
    public static int gcd_1_iterations(int m, int n){
        int divisor = 1;
        int iterations = 0;
        for(int i=2; i<m && i<n; i++){
            iterations++;
            if(m%i == 0 && n%i == 0)
                divisor = i;
        }
        return iterations;
    }
    
    public static int gcd_2_iterations(int m, int n){
        int divisor = 1;
        int iterations = 0;
        for(int i=n; i>=1; i--){
            iterations++;
            if(m%i == 0 && n%i == 0){
                divisor = i;
                break;
            }
        }
        return iterations;
    }
    
    public static int gcd_3_iterations(int m, int n) {
        int divisor = 1;
        int iterations = 0;
        if(m%n == 0)
            return iterations;
        for(int i = n/2; i >= 1; i--){
            iterations++;
            if(m%i == 0 && n%i == 0){
                divisor=i;
                break;
            }
        }
        return iterations;
    }
    
    public static int gcd_4_iterations(int m, int n){
        return gcd_4_iterations_helper(m, n, 0);
    }
    
    private static int gcd_4_iterations_helper(int m, int n, int count){
        if(m%n == 0)
            return count;
        else
            return gcd_4_iterations_helper(n, m%n, count + 1);
    }
}

/*
What I learned from testing these GCD methods:

1. gcd_1 (O(n)): This method checks every number from 2 up to the smaller 
   input number. It's slow because it has to check so many numbers. My tests 
   showed it took the longest time and had the most loops.

2. gcd_2 (O(n)): Even though this method has the same time complexity as 
   gcd_1, it's smarter because it starts from the bigger number and stops early 
   when it finds the answer. This made it faster than gcd_1 in my tests.

3. gcd_3 (O(n)): This one is better because it only checks numbers up to half 
   of the smaller input. Since no number bigger than half can divide evenly into 
   it, this saves time. My results showed it was faster than both gcd_1 and gcd_2.

4. gcd_4 (O(log n)): This is Euclid's algorithm and it was way faster than 
   all the others! It uses a clever trick where it keeps dividing and taking 
   remainders instead of checking every possible number. The tests showed it had 
   the fewest loops and fastest times.

My experiment showed that the actual running times matched what we learned about 
Big-O notation in class. The Euclidean algorithm (gcd_4) was clearly the best 
choice, especially when working with big numbers. This shows why picking the 
right algorithm really matters!
*/
