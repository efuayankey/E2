import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * class Test handles operations for states and trees data
 * provides search, sort, and display functionality
 * @author: Efua Yankey
 * @version: 11
 */
public class Test{
    public static <E1, E2> int search(ArrayList<Pair<E1,E2>> list, E1 key){
        for (int i = 0; i<list.size(); i++){
            Pair<E1,E2> pair = list.get(i); //  get. the pair at index i
            E1 pairFirst = pair.getFirst(); // get the first elemet of pair
            if (pairFirst.equals(key)){
                return i;
            }      
    }
    return -1;
}
    public static void main(String[] args) { //java Test hello 125 -command-line
        //validate the arguments if there is any
        if(args.length == 0){
            System.out.println("No arguments provided (states or trees)");
            System.exit(0); //end the program
        }
        if (args.length > 1){
            System.out.println("Too many argument (one argument: tree or states)");
            System.exit(0); //end the program
        }
        String type = args[0]; //get the command-line argument
        if (!type.equals("tree") && !type.equals("states")){
            System.out.println("Invalid data type. states and trees are the only supported types");
        }

        switch(type){
            case "states":
                statesOperations();
                break;
            case "trees":
                treesOperations();
                break;

        }
    }
    public static void statesOperations(){
        ArrayList<Pair<String, String>> states = new ArrayList<>(); //create a list with a capacity = 10 //instanstiation
        //how the arrayList size grows: n * 1.5
        readStates(states, "states.txt");
        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do { 
            printMenu("states");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation){
                case 1: //view
                print(states);
                    break;
                case 2: //search
                    System.out.println("Enter a state: ");
                    String name = keyboard.nextLine();
                    int index = search(states, name);
                    if(index == -1){
                        System.out.println("State not found");
                    }
                    else{
                        System.out.println("State found: " + states.get(index));
                    }
                    break;
                case 3: //sort by name
                    states.sort(new ComparatorByFirst());
                    print(states);
                    break;
                case 4: //sort by capital
                    states.sort(new ComparatorBySecond());
                    print(states);
                    break;
                case 5: //exit
                    break;
                default:
                    System.out.println("Invalid operation(1 to 5)");
            }
        } while (operation !=5);

    }

    public static <E> void print(ArrayList<E> list){
        for (E element: list){
            System.out.print(element);
        }
    }
    public static void printMenu(String type){
        System.out.println("Select an operation: ");
        System.out.println("1: View all " + type);
        System.out.println("2: Search  " + type +  " states by name");
        System.out.println("3: Sort " + type +  " by name");
        if(type.equals("states")){
            System.out.println("4: Sort  " + type +  " by capital");
        }
        else{
            System.out.println("4: Sort " + type +  " by height");
        }
      
        System.out.println("5: Exit");

    }

    public static void readStates(ArrayList<Pair<String, String>> list, String filename){
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|"); // use \\ infront of | because split() take regex as input, and | means or in regex
                String name = items[0];
                String capital = items[1];
                Pair<String, String> state = new Pair<>(name, capital);
                list.add(state);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void readTrees(ArrayList<Pair<String, Integer>> list, String filename){
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|"); // use \\ infront of | because split() take regex as input, and | means or in regex
                String name = items[0];
                Integer height = Integer.parseInt(items[1]);
                Pair<String, Integer> trees = new Pair<>(name, height);
                list.add(trees);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void treesOperations(){
        ArrayList<Pair<String, Integer>> trees = new ArrayList<>(); //create a list with a capacity = 10 //instanstiation
        //how the arrayList size grows: n * 1.5
        readTrees(trees, "trees.txt");
        Scanner keyboard = new Scanner(System.in);
        int operation = 0;
        do { 
            printMenu("trees");
            operation = Integer.parseInt(keyboard.nextLine());
            switch(operation){
                case 1: //view
                print(trees);
                    break;
                case 2: //search
                    System.out.println("Enter a tree: ");
                    String name = keyboard.nextLine();
                    int index = search(trees, name);
                    if(index == -1){
                        System.out.println("Tree not found");
                    }
                    else{
                        System.out.println("Tree found: " + trees.get(index));
                    }
                    break;
                case 3: //sort by name
                    trees.sort(new ComparatorByFirst());
                    print(trees);
                    break;
                case 4: //sort by capital
                    trees.sort(new ComparatorBySecond());
                    print(trees);
                    break;
                case 5: //exit
                    break;
                default:
                    System.out.println("Invalid operation(1 to 5)");
            }
        } while (operation !=5);

    }
}