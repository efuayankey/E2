/**
 * Class Pair
 */
public class Pair {
    // data members
    private String type;
    private long size;
    /**
     * Constructor
     * @param ext the initial value for type
     * @param s teh initial value for size
     */
    public Pair(String ext, long s){
        type = ext;
        size = s;
    }
    /**
     * Getter for type
     * @return the value of type
     */
    public String getType(){ return type;}
    /**
     * Getter for size
     * @return the value of size
     */
    public long getSize(){ return size;}
    /**
     * Setter for type
     * @param t the new value of type
     */
    public void setType(String t){ type = t;}
    /**
     * Setter for size
     * @param s the new value of size
     */
    public void setSize(long s){ size = s;}

}
