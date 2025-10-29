import java.util.ArrayList;

/**
 * Course represents an academic course with a number, title, and credits.
 * Courses maintain a list of enrolled students and support enrollment management.
 * @author Efua Yankey
 * @version 1.0
 */
public class Course {
    private String number;
    private String title;
    private int credits;
    private ArrayList<Student> enrolledStudents;

    /**
     * Constructor with three parameters
     * @param number the course number
     * @param title the course title
     * @param credits the number of credits
     */
    public Course(String number, String title, int credits) {
        this.number = number;
        this.title = title;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
    }

    /**
     * getter for number
     * @return the course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * getter for title
     * @return the course title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for credits
     * @return the number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * getter for enrolledStudents
     * @return the list of enrolled students
     */
    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    /**
     * setter for number
     * @param number the new course number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * setter for title
     * @param title the new course title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setter for credits
     * @param credits the new number of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * toString method
     * @return a formatted string with course information
     */
    public String toString() {
        return String.format("%-16s%-48s%-8d%d", number, title, credits, enrolledStudents.size());
    }

    /**
     * equals method
     * @param o the object being compared to this course
     * @return true if the two courses have the same number, false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Course) {
            Course c = (Course) o;
            return this.number.equals(c.number);
        }
        return false;
    }

    /**
     * method to enroll a student in this course
     * @param s the student to enroll
     * @return true if the student was added, false if already enrolled
     */
    public boolean enroll(Student s) {
        if (enrolledStudents.contains(s)) {
            return false;
        }
        enrolledStudents.add(s);
        return true;
    }

    /**
     * method to drop a student from this course
     * @param s the student to drop
     * @return true if the student was removed, false if not found
     */
    public boolean drop(Student s) {
        return enrolledStudents.remove(s);
    }

    /**
     * mthod to get the number of enrolled students
     * @return the count of enrolled students
     */
    public int count() {
        return enrolledStudents.size();
    }
}