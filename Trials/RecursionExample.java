import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursionExample {
    
    public static void main(String[] args) {
        // Create a list of numbers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        numbers.add(25);
        
        System.out.println("List: " + numbers);
        
        // Test the count method
        int total = count(numbers);
        System.out.println("Total count: " + total);
    }
   
    
    // PUBLIC METHOD - User calls this
    public static int count(List<Integer> numbers) {
        Iterator<Integer> iter = numbers.iterator();
        return countHelper(iter);
    }
    
    // HELPER METHOD - Does the recursion
    private static int countHelper(Iterator<Integer> iter) {
        // BASE CASE - no more elements
        if (!iter.hasNext()) {
            return 0;
        }
        
        // RECURSIVE CASE
        iter.next();  // Move to next element
        
        // Count this element (1) + count the rest
        return 1 + countHelper(iter);
    }
    
}
/**
 * EXPECTED OUTPUT:**
```
List: [5, 10, 15, 20, 25]
Total count: 5
 */