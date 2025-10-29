import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Efua Yankey
 * @version 1.0
 * StudentManagement manages a collection of students and courses.
 * it provides functionality for enrollment, filtering, sorting, and various queries
 * on students and courses using two ListRepository instances.
 */
public class StudentManagement {
    private ListRepository<Integer, Student> students;
    private ListRepository<String, Course> courses;

    /**
     * initializes empty repositories for students and courses
     */
    public StudentManagement() {
        students = new ListRepository<>();
        courses = new ListRepository<>();
    }

    /**
     * method to add a student
     * Time Complexity: O(n) - due to find operation in add method
     * @param s the student to add
     * @return true if added successfully, false if student already exists
     */
    public boolean addStudent(Student s) {
        return students.add(s.getId(), s);
    }

    /**
     * method to add a course
     * Time Complexity: O(n) - due to find operation in add method
     * @param c the course to add
     * @return true if added successfully, false if course already exists
     */
    public boolean addCourse(Course c) {
        return courses.add(c.getNumber(), c);
    }

    /**
     * method to find a student by id
     * Time Complexity: O(n) - linear search through students
     * @param id the student id
     * @return the Student object if found, null otherwise
     */
    public Student findStudent(int id) {
        return students.find(id);
    }

    /**
     * method to find a course by number
     * Time Complexity: O(n) - linear search through courses
     * @param courseNumber the course number
     * @return the Course object if found, null otherwise
     */
    public Course findCourse(String courseNumber) {
        return courses.find(courseNumber);
    }

    /**
     * method to remove a student and drop them from all courses
     * Time Complexity: O(n*m) - n students, m courses to check and drop from
     * @param id the student id
     * @return the removed Student object if found, null otherwise
     */
    public Student removeStudent(int id) {
        Student student = students.find(id);
        if (student == null) {
            return null;
        }
        
        // Drop student from all courses
        ArrayList<Course> allCourses = courses.all();
        for (Course course : allCourses) {
            course.drop(student);
        }
        
        return students.remove(id);
    }

    /**
     * method to remove a course
     * Time Complexity: O(n) - linear search and removal
     * @param courseNumber the course number
     * @return the removed Course object if found, null otherwise
     */
    public Course removeCourse(String courseNumber) {
        return courses.remove(courseNumber);
    }

    /**
     * method to enroll a student in a course
     * Time Complexity: O(n) - find operations for student and course
     * @param studentId the student id
     * @param courseNumber the course number
     * @return 1 if enrolled successfully, 0 if already enrolled, -1 if course not found, -2 if student not found
     */
    public int enrollStudent(int studentId, String courseNumber) {
        Student student = students.find(studentId);
        if (student == null) {
            return -2;
        }
        
        Course course = courses.find(courseNumber);
        if (course == null) {
            return -1;
        }
        
        boolean enrolled = course.enroll(student);
        return enrolled ? 1 : 0;
    }

    /**
     * method to drop a student from a course
     * Time Complexity: O(n) - find operations for student and course
     * @param studentId the student id
     * @param courseNumber the course number
     * @return 1 if dropped successfully, 0 if not enrolled, -1 if course not found, -2 if student not found
     */
    public int dropStudent(int studentId, String courseNumber) {
        Student student = students.find(studentId);
        if (student == null) {
            return -2;
        }
        
        Course course = courses.find(courseNumber);
        if (course == null) {
            return -1;
        }
        
        boolean dropped = course.drop(student);
        return dropped ? 1 : 0;
    }

    /**
     * method to get the total number of students
     * Time Complexity: O(1) - constant time
     * @return the count of students
     */
    public int getStudentCount() {
        return students.count();
    }

    /**
     * method to get the total number of courses
     * Time Complexity: O(1) - constant time
     * @return the count of courses
     */
    public int getCourseCount() {
        return courses.count();
    }

    /**
     * method to get all courses a student is enrolled in
     * Time Complexity: O(n*m) - n courses, m students per course to check
     * @param studentId the student id
     * @return list of course numbers, null if student not found
     */
    public ArrayList<String> getStudentCourses(int studentId) {
        Student student = students.find(studentId);
        if (student == null) {
            return null;
        }
        
        ArrayList<String> studentCourses = new ArrayList<>();
        ArrayList<Course> allCourses = courses.all();
        
        for (Course course : allCourses) {
            if (course.getEnrolledStudents().contains(student)) {
                studentCourses.add(course.getNumber());
            }
        }
        
        return studentCourses;
    }

    /**
     * method to filter students by major
     * Time Complexity: O(n) - iterate through all students
     * @param major the major to filter by
     * @return list of students with the given major
     */
    public ArrayList<Student> filterStudentsByMajor(String major) {
        ArrayList<Student> filtered = new ArrayList<>();
        ArrayList<Student> allStudents = students.all();
        
        for (Student student : allStudents) {
            if (student.getMajor().equals(major)) {
                filtered.add(student);
            }
        }
        
        return filtered;
    }

    /**
     * method to get top n students by GPA
     * Time Complexity: O(n log n) - sorting dominates
     * @param n the number of top students to return
     * @return list of top n students sorted by GPA (descending), null if no students
     */
    public ArrayList<Student> getTopStudents(int n) {
        ArrayList<Student> allStudents = new ArrayList<>(students.all());
        
        if (allStudents.isEmpty()) {
            return null;
        }
        
        allStudents.sort(new StudentGPAComparator());
        
        int limit = Math.min(n, allStudents.size());
        return new ArrayList<>(allStudents.subList(0, limit));
    }

    /**
     * method to get students with GPA above a threshold
     * Time Complexity: O(n log n) - filtering is O(n), sorting is O(n log n)
     * @param floor the minimum GPA threshold
     * @return list of students with GPA > floor, sorted by GPA (descending)
     */
    public ArrayList<Student> getStudentsAboveGPA(double floor) {
        ArrayList<Student> filtered = new ArrayList<>();
        ArrayList<Student> allStudents = students.all();
        
        for (Student student : allStudents) {
            if (student.getGPA() > floor) {
                filtered.add(student);
            }
        }
        
        filtered.sort(new StudentGPAComparator());
        return filtered;
    }

    /**
     * method to get courses with enrollment less than a threshold
     * Time Complexity: O(n) - iterate through all courses
     * @param number the maximum enrollment threshold
     * @return list of courses with enrollment < number
     */
    public ArrayList<Course> getSmallCourses(int number) {
        ArrayList<Course> smallCourses = new ArrayList<>();
        ArrayList<Course> allCourses = courses.all();
        
        for (Course course : allCourses) {
            if (course.count() < number) {
                smallCourses.add(course);
            }
        }
        
        return smallCourses;
    }

    /**
     * method to get courses with enrollment greater than or equal to a threshold
     * Time Complexity: O(n) - iterate through all courses
     * @param number the minimum enrollment threshold
     * @return list of courses with enrollment >= number
     */
    public ArrayList<Course> getLargeCourses(int number) {
        ArrayList<Course> largeCourses = new ArrayList<>();
        ArrayList<Course> allCourses = courses.all();
        
        for (Course course : allCourses) {
            if (course.count() >= number) {
                largeCourses.add(course);
            }
        }
        
        return largeCourses;
    }

    /**
     * method to get all courses sorted by enrollment
     * Time Complexity: O(n log n) - sorting dominates
     * @return list of all courses sorted by enrollment (ascending)
     */
    public ArrayList<Course> getCoursesSortedbyEnrollment() {
        ArrayList<Course> allCourses = new ArrayList<>(courses.all());
        allCourses.sort(new CourseEnrollmentComparator());
        return allCourses;
    }
}

/**
 * Comparator to compare students by GPA in descending order
 */
class StudentGPAComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        // descending order (highest GPA first)
        return Double.compare(s2.getGPA(), s1.getGPA());
    }
}

/**
 * Comparator to compare courses by enrollment in ascending order
 */
class CourseEnrollmentComparator implements Comparator<Course> {
    public int compare(Course c1, Course c2) {
        // acsending order (smallest enrollment first)
        return Integer.compare(c1.count(), c2.count());
    }
}