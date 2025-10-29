import java.util.ArrayList;
/**
 * Generic interface Repository 
 * with two generic types ID and T
 */
public interface Repository<ID,T>{
    /**
     * Method to find an item in the repository
     * @param id the id of the item
     * @return The T object associated with id if found, null otherwise
     */
    public T find(ID id);
    /**
     * Method to add a new item to the repository
     * @param id the id of the new item
     * @param item the new item
     * @return true if the item was added, 
     *         false if an item with the given id already exists in the repository
     */
    public boolean add(ID id, T item);
    /**
     * Method to remove an item from the repository
     * @param id the id of the item to be removed
     * @return true if an item with the given id was found and removed
     *         false if no item has the given id
     */
    public T remove(ID id);
    /**
     * Method the get the number of items in the repository
     * @return the total number of items in the repository
     */
    public int count();
    /**
     * Method to get a copy of the list of items in the repository
     * @return a list of all the items (T objects) in the repository
     */
    public ArrayList<T> all();
}