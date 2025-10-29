/**
 * represents a print request with an ID, group priority, and file size
 * implements Comparable to enable priority-based ordering in queues
 * @author Efua Yankey
 * @version 1.0
 */
public class PrintRequest implements Comparable<PrintRequest>{
    private int id;
    private String group;
    private long size;

    /**
     * default constructor that creates a PrintRequest with default values
     */
    public PrintRequest(){
        id = 0;
        group = "watch";
        size = 0;
    }

    /**
     * parameterized constructor that creates a PrintRequest with specified values
     * @param id the unique identifier for this print request
     * @param gr the group name that determines priority level
     * @param size the file size in bytes
     */
    public PrintRequest(int id, String gr, long size){
        this.id = id;
        group = gr;
        this.size = size;
    }

    /**
     * returns the ID of this print request
     * @return the unique identifier
     */
    public int getID(){return id;}
    
    /**
     * returns the group name of this print request
     * @return the group name that determines priority
     */
    public String getGroup(){return group;}
    
    /**
     * returns the file size of this print request
     * @return the size in bytes
     */
    public long getSize(){return size;}

    /**
     * sets the ID of this print request
     * @param id the new unique identifier
     */
    public void setID(int id){this.id = id;}
    
    /**
     * sets the group name of this print request
     * @param gr the new group name
     */
    public void setGroup(String gr){group = gr;}
    
    /**
     * sets the file size of this print request
     * @param size the new size in bytes
     */
    public void setSize(long size){this.size = size;}

    /**
     * returns a formatted string representation of this print request
     * formats file size with appropriate units (Bytes, KB, MB, GB)
     * @return formatted string with ID, group, and size
     */
    public String toString(){
        double temp = size;
        String out = "";
        if(size > 1000000000){
            temp = temp/1000000000.0;
            out = String.format("%.1f", temp) + "GB";
        }
        else if(size > 1000000){
            temp = temp/1000000.0;
            out = String.format("%.1f", temp) + "MB";
        }
         else if(size > 1000){
            temp = temp/1000.0;
            out = String.format("%.1f", temp) + "KB";
        }
        else{
            out = size + "Bytes";
        }
        return String.format("%10d\t%-10s\t%-10s", id, group, out);
    }

    /**
     * calculates the priority level based on group name
     * lower numbers indicate higher priority (root=0, admin=1, user=2, batch=3)
     * @return priority level as integer, -1 if group not found
     */
    private int getPriority(){
        String[] priorities = {"root", "admin", "user", "batch"};
        for (int i=0; i < priorities.length; i++){
            if(this.group.equals(priorities[i])){
                return i;
            }
        } 
        return -1;
    }
    
    /**
     * compares this print request with another based on priority
     * implements natural ordering for priority queue (lower priority number = higher priority)
     * @param pr the other PrintRequest to compare against
     * @return negative if this has higher priority, positive if lower, 0 if equal
     */
    public int compareTo(PrintRequest pr){
        return this.getPriority() - pr.getPriority();
    }
}

//Natural ordering: means define compareTo, so we have to implement the interface comparable