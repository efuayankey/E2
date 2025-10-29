/**
 * Student represents a student with an ID, name, major, and GPA
 * Students can be compared by their ID using the equals method
 * @author Efua Yankey
 * @version 1.0
 */
public class Student {
    private int id;
    private String name;
    private String major;
    private double gpa;

    /**
     * Constructor with four parameters
     * @param id the student's id
     * @param name the student's name
     * @param major the student's major
     * @param gpa the student's gpa
     */
    public Student(int id, String name, String major, double gpa) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
    }

    /**
     * getter for id
     * @return the student's id
     */
    public int getId() {
        return id;
    }

    /**
     * getter for name
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for major
     * @return the student's major
     */
    public String getMajor() {
        return major;
    }

    /**
     * getter for gpa
     * @return the student's gpa
     */
    public double getGPA() {
        return gpa;
    }

    /**
     * setter for id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for major
     * @param major the new major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * setter for gpa
     * @param gpa the new gpa
     */
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    /**
     * toString method
     * @return a formatted string with student information
     */
    public String toString() {
        return String.format("%d\t%s\t%s\t%.2f", id, name, major, gpa);
    }

    /**
     * equals method
     * @param o the object being compared to this student
     * @return true if the two students have the same id, false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.id == s.id;
        }
        return false;
    }
}