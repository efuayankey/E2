import java.util.Comparator;
/**
 * class ComparatorBySecond compares pairs by their second element
 * implements Comparator interface for sorting purposes
 * @author: Efua Yankey
 * @version: 11
 */
public class ComparatorBySecond<E1, E2 extends Comparable<E2>> implements Comparator<Pair<E1, E2>>{
    public int compare(Pair<E1,E2> pair1, Pair<E1,E2> pair2){
        E2 p1Second = pair1.getSecond();
        E2 p2Second = pair2.getSecond();
        return p1Second.compareTo(p2Second);
    }
}
