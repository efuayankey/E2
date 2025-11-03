import java.util.ArrayList;
import java.util.List;
public class First{

    public static void main(String[] args) {
    // With Strings
    List<String> names = new ArrayList<>();
    names.add("Alice");
    names.add("Bob");
    System.out.println(getFirst(names));  // Prints: Alice
    
    // With Integers
    List<Integer> numbers = new ArrayList<>();
    numbers.add(100);
    numbers.add(200);
    System.out.println(getFirst(numbers));  // Prints: 100
    
    // Empty list
    List<String> empty = new ArrayList<>();
    System.out.println(getFirst(empty));  // Prints: null
}
    public static <E> E getFirst(List<E> list) {
    // Your code here
    if(list.isEmpty()){
        return null;
    }
    else{
        return list.get(0);
    }
}







 /**
  *    public static int countGreaterThan(List<Integer> list, int value){
        Iterator<Integer> iter = list.iterator();
        return helperMethod(iter, value);
}
    private static int helperMethod(iter, value){
        while(!iter.hasNext()){
            return 0;
        }

        int current = iter.next();
        if(value > current){
            return 1 + helperMethod(iter, current);
        }
        else{return helperMethod(iter, current);}
        

    }

   THE RECURSION PATTERN (Memorize This!):

    private static ReturnType helper(Iterator<Type> iter, otherParams) {
    // BASE CASE
    if (!iter.hasNext()) {
        return baseValue;  // What to return when done
    }
    
    // RECURSIVE CASE
    Type current = iter.next();  // Process ONE element
    
    // Do something with current, then RECURSE
    return something + helper(iter, otherParams);
}
  */

}
