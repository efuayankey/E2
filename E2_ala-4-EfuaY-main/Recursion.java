import java.util.Scanner;

/**
 * class Recursion demonstrates recursive algorithms for character counting and string permutations
 * contains methods to count occurrences of a character in a string and generate all permutations of a string
 * uses recursive approaches to solve both problems by breaking them into smaller subproblems
 * @author: Efua Yankey
 * @version: 11
 */
public class Recursion{
    public static int count(String str, char c){
        if (str.length() == 0){
            return 0;
        }
        if (str.charAt(0) == c){
            return 1 + count(str.substring(1), c); //recursive call Found it! Add 1 + count rest
        }
        else{
            return 0 + count(str.substring(1), c); //recursive call // Not found, just count rest
        }
        }
    
    public static void permutations(String s) {
        permutations("", s);
    }
    
    public static void permutations(String s1, String s2) {
        if (s2.length() == 0) {
            System.out.println(s1);
        }
        else{
            for(int i = 0; i< s2.length(); i++){
                permutations(s1 + s2.charAt(i), s2.substring(0, i) + s2.substring(i+1));
            }
        }
    }
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        //testing count
        System.out.println("Enter a string: ");
        String str = keyboard.nextLine(); // possible for a sentence
        System.out.println("Enter a character: ");
        char c = keyboard.nextLine().charAt(0); // possible for a sentence
        System.out.println(c + " appears " +  count(str, c) + " times in \"" + str + "\"");
        
        //testing permutation
        System.out.println("Enter a string:");
        String permString = keyboard.nextLine();
        permutations(permString);

    }
}


//Write a recursive method public static int sumDigits(int n) that adds all digits of a number.
/**
 * public static int sumDigits(int n){
    if(n == 0){return 0;}          // BASE CASE ✅
    else{
        int lastDigit = n % 10;     // Get last digit ✅
        n = n/10;                   // Remove last digit ✅
        return lastDigit + sumDigits(n);  // Add to sum of rest ✅
    }
}
 */

//RREVERSE STRING
/**
 * public static String reverseString(String str){
    if (str.length()==0){return ""; }
    if (str.length() ==1){return str;}
    else{
        //char first = str.charAt(0); OR
        String first = str.substring(0, 1);      // Get first char as String ✅
        String remain = str.substring(1,str.length()); //same as str.substring(1)

        return reverseString(remain)+first;
    }
    }
 */


/**same
public static String reverseString(String str){
    if (str.length() <= 1){
        return str;
    }
    return reverseString(str.substring(1)) + str.substring(0, 1);
}
 */
