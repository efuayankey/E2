import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * demonstrates stack-based postfix expression evaluation and priority queue processing
 * includes two main functionalities: postfix calculator and print request scheduler
 * @author Efua Yankey
 * @version 1.0
 */
public class Test{
    /**
     * main method that executes postfix evaluation and print request processing
     * part 1: evaluates user-entered postfix expressions using a stack
     * part 2: processes print requests from file using priority queue
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        //PART 1: USING STACK
        Stack<Integer> postfixStack = new Stack<>(); //create an empty stack
        Scanner keyboard = new Scanner (System.in);
        System.out.println("Enter a prefix experssion: ");
        String postfix = keyboard.nextLine();
        String[] tokens = postfix.split(" ");

    try{
        for(String token: tokens){
            if(token.matches("\\d{1,}")){ //a number with one or more digits
                int operand = Integer.parseInt(token);
                postfixStack.push(operand);
            }
            else{
                int operand1 = postfixStack.pop();
                int operand2 = postfixStack.pop();
                switch(token){
                    case "*":
                        postfixStack.push(operand1 * operand2);
                        break;
                    case "+":
                        postfixStack.push(operand1 + operand2);
                        break;
                    case "-":
                        postfixStack.push(operand2 - operand1); //be mindful of the order for '-' and '/'
                        break;
                    case "/":
                        postfixStack.push(operand2 / operand1);
                        break;
                    default:
                        throw new NoSuchElementException();
                }

            }
        }//pop the result
        int result = postfixStack.pop();
        if (postfixStack.isEmpty()){
            System.out.println("Result: " + result);
        }
        else{
            System.out.println("Malformed expression");
        }
            
        }
        catch(NoSuchElementException e){
            System.out.println("Malformed expression");
        }
        //PART 2 : PRIORITY QUEUE
        PriorityQueue<PrintRequest> pQueue = new PriorityQueue<>();
        File file = new File("requests.txt");
        try {
            Scanner read = new Scanner(file);
            while(read.hasNext()){
                int id = read.nextInt();
                String group = read.next();
                long size = read.nextLong();
                PrintRequest pr = new PrintRequest(id, group, size);
                pQueue.offer(pr); //this is the right method to use for this work
            }
            read.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not exception.");
        }
        //processing the request
        long printSpeed = 10000;
        long totalTime = 0;
        while(!pQueue.isEmpty()){
            PrintRequest pr = pQueue.poll();
            long time = pr.getSize()/printSpeed; //time to print the request pr
            totalTime += time;
            System.out.printf("%s\t%s\n", pr, formatTime(time));
        }
        System.out.println("Total printing time: " + formatTime(totalTime));
        }
        
        /**
         * formats time in seconds to DD:HH:MM:SS format
         * converts total seconds into days, hours, minutes, and remaining seconds
         * @param time total time in seconds
         * @return formatted time string in DD:HH:MM:SS format
         */
        public static String formatTime(long time){
            long days = time / 86400;
            long hours = (time % 86400) / 3600;
            long minutes = (time % 3600) / 60;
            long seconds = time % 60;
            return String.format("%02d:%02d:%02d:%02d", (int)days, (int)hours, (int)minutes, (int)seconds);
        }
   
}