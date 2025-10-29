import java.util.Comparator;
class ComparatorByDate implements Comparator<Message> {
    public int compare(Message m1, Message m2) {
        return m1.getDate().compareTo(m2.getDate());
    }
}