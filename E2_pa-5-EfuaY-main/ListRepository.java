import java.util.ArrayList;

/**
 * ListRepository is a generic repository implementation using an ArrayList of Pairs.
 * It stores items with associated IDs and provides CRUD operations (Create, Read, Update, Delete).
 * @author Efua Yankey
 * @version 1.0
 * @param <ID> the type of the ID
 * @param <T> the type of the items stored
 */
public class ListRepository<ID, T> implements Repository<ID, T> {
    private ArrayList<Pair<ID, T>> list;

    /**
     * nnitializes an empty ArrayList
     */
    public ListRepository() {
        list = new ArrayList<>();
    }

    /**
     * method to find an item in the repository
     * @param id the id of the item
     * @return The T object associated with id if found, null otherwise
     */
    public T find(ID id) {
        for (Pair<ID, T> pair : list) {
            if (pair.getFirst().equals(id)) {
                return pair.getSecond();
            }
        }
        return null;
    }

    /**
     * method to add a new item to the repository
     * @param id the id of the new item
     * @param item the new item
     * @return true if the item was added, 
     *         false if an item with the given id already exists in the repository
     */
    public boolean add(ID id, T item) {
        if (find(id) != null) {
            return false;
        }
        list.add(new Pair<>(id, item));
        return true;
    }

    /**
     * method to remove an item from the repository
     * @param id the id of the item to be removed
     * @return the removed item if found, null otherwise
     */
    public T remove(ID id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFirst().equals(id)) {
                T item = list.get(i).getSecond();
                list.remove(i);
                return item;
            }
        }
        return null;
    }

    /**
     * method to get the number of items in the repository
     * @return the total number of items in the repository
     */
    public int count() {
        return list.size();
    }

    /**
     * method to get a copy of the list of items in the repository
     * @return a list of all the items (T objects) in the repository
     */
    public ArrayList<T> all() {
        ArrayList<T> items = new ArrayList<>();
        for (Pair<ID, T> pair : list) {
            items.add(pair.getSecond());
        }
        return items;
    }
}