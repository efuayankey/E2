import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Test{
    public static void main(String[] args) {
        // Test case 1: Creating an instance of StudentManagement
        System.out.println("Test case 1: Creating an empty instance of StudentManagement");
        StudentManagement sm = new StudentManagement();
        System.out.println(sm.getCourseCount() + " courses in the student management");
        System.out.println(sm.getStudentCount() + " students in the student management");

        // Test case 2: Reading data from the text files (testing addCourse, addStudent, enrollStudent)
        System.out.println("\nTest case 2: Reading data from the text files (testing addCourse, addStudent, enrollStudent)");
        readData("students.txt", "courses.txt", sm);
        System.out.println(sm.getCourseCount() + " courses read from the file \"courses.txt\"");
        System.out.println(sm.getStudentCount() + " students read from the file \"students.txt\"");

        // Test case 3: Test finding a course (successfull)
        System.out.println("\nTest case 3: Test finding a course (successfull)");
        Course c = sm.findCourse("MATH021");
        if(c == null){
            System.out.println("Course MATH021 not found");
        }
        else{
            System.out.println("Course MATH021 found: " + c);
            System.out.println(c.count() + " students are registered in the course");
        }

        // Test case 4: Test finding a course (fail)
        System.out.println("\nTest case 4: Test finding a course (fail)");
        c = sm.findCourse("PSYC404");
        if(c == null){
            System.out.println("Course PSYC404 not found");
        }
        else{
            System.out.println("Course PSYC404 found: " + c);
            System.out.println(c.count() + " students are registered in the course");
        }
        // Test case 5: Test finding a student (successfull)
        System.out.println("\nTest case 5: Test finding a student (successfull)");
        Student s = sm.findStudent(1662166);
        if(s == null){
            System.out.println("Student with id 1662166 not found");
        }
        else{
            System.out.println("Student with id 1662166 found: " + s);
            System.out.println("The student is registered in: " + sm.getStudentCourses(1662166));
        }

        // Test case 6: Test finding a student (fail)
        System.out.println("\nTest case 6: Test finding a student (fail)");
        s = sm.findStudent(9999999);
        if(s == null){
            System.out.println("Student with id 9999999 not found");
        }
        else{
            System.out.println("Student with id 9999999 found: " + s);
            System.out.println("The student is registered in: " + sm.getStudentCourses(9999999));
        }
        // Test case 7: Test removing a course (successfull)
        System.out.println("\nTest case 7: Test removing a course (successfull)");
        c = sm.removeCourse("EES015");
        if(c == null){
            System.out.println("Course EES015 not found");
        }
        else{
            System.out.println("Course EES015 found and removed: " + c);
            System.out.println(c.count() + " students were registered in the course");
        }

        // Test case 8: Test removing a course (fail)
        System.out.println("\nTest case 8: Test removing a course (fail)");
        c = sm.removeCourse("EES105");
        if(c == null){
            System.out.println("Course EES105 not found");
        }
        else{
            System.out.println("Course EES105 found and removed: " + c);
            System.out.println(c.count() + " students were registered in the course");
        }

        // Test case 9: Test removing a student (successfull)
        System.out.println("\nTest case 9: Test removing a student (successfull)");
        ArrayList<String> sCourses = sm.getStudentCourses(5343694);
        s = sm.removeStudent(5343694);
        if(s == null){
            System.out.println("Student with id 5343694 not found");
        }
        else{
            System.out.println("Student with id 5343694 found and removed: " + s);
            System.out.println("The student was registered in: " + sCourses);
        }
        // Test case 10: Test removing a student (fail)
        System.out.println("\nTest case 10: Test removing a student (fail)");
        s = sm.removeStudent(9999999);
        if(s == null){
            System.out.println("Student with id 9999999 not found");
        }
        else{
            System.out.println("Student with id 9999999 found and removed " + s);
            System.out.println("The student was registered in: " + sm.getStudentCourses(9999999));
        }

        // Test case 11: Test dropping a student (successfull)
        System.out.println("\nTest case 11: Test dropping a student (successfull)");
        int response = sm.dropStudent(9619574, "DES111");
        checkResponse(sm, response, 9619574, "DES111");
        
        
        // Test case 12: Test dropping  a course (student not found)
        System.out.println("\nTest case 12: Test dropping  a course (student not found)");
        response = sm.dropStudent(9999999, "MATH021");
        checkResponse(sm, response, 9999999, "MATH021");

        // Test case 13: Test dropping a course (course not found)
        System.out.println("\nTest case 13: Test dropping  a course (course not found)");
        response = sm.dropStudent(2520779, "PSYC400");
        checkResponse(sm, response, 2520779, "PSYC400");

        // Test case 14: Test dropping a course (student not enrolled in the course)
        System.out.println("\nTest case 14: Test dropping  a course (student not enrolled in the course)");
        response = sm.dropStudent(2520779, "DES111");
        checkResponse(sm, response, 2520779, "DES111");

        // Test case 15: Test filter by major
        System.out.println("\nTest case 15: Test filter by major");
        ArrayList<Student> students = sm.filterStudentsByMajor("CSB");
        if(students == null){
            System.out.println("no students found for the major CSB");
        }
        else{
            System.out.println(students.size() + " students in the major CSB");
            for(Student x: students){
                System.out.println(x);
            }
        }
        // Test case 16: Test get top students
        System.out.println("\nTest case 16: Test get top students");
        students = sm.getTopStudents(5);
        if(students == null){
            System.out.println("There are no students");
        }
        else{
            System.out.println(students.size() + " top students:");
            for(Student x: students){
                System.out.println(x);
            }
        }

        // Test case 17: Test get students above given gpa
        System.out.println("\nTest case 17: Test get students above given gpa");
        students = sm.getStudentsAboveGPA(3.5);
        if(students == null){
            System.out.println("There are not students with GPA greater than 3.5");
        }
        else{
            System.out.println(students.size() + " students have a GPA greater than 3.5:");
            for(Student x: students){
                System.out.println(x);
            }
        }

        // Test case 18: Test getting small courses
        System.out.println("\nTest case 18: Test getting small courses");
        ArrayList<Course> courses = sm.getSmallCourses(5);
         if(courses == null){
            System.out.println("There are not courses with less than 5 students");
        }
        else{
            System.out.println(courses.size() + " courses have less than 5 students:");
            for(Course x: courses){
                System.out.println(x);
            }
        }

        // Test case 19: Test getting large courses
        System.out.println("\nTest case 19: Test getting large courses");
        courses = sm.getLargeCourses(5);
        if(courses == null){
            System.out.println("There are not courses with 5 or more students");
        }
        else{
            System.out.println(courses.size() + " courses have 5 or more students:");
            for(Course x: courses){
                System.out.println(x);
            }
        }

        // Test case 20: Test getting courses sorted by enrollment
        System.out.println("\nTest case 20: Test getting courses sorted by enrollment");
        courses = sm.getCoursesSortedbyEnrollment();
        if(courses == null){
            System.out.println("There are no courses");
        }
        else{
            for(Course x: courses){
                System.out.println(x);
            }
        }


    }
    public static void readData(String studentFile, String courseFile, StudentManagement sm){
        // read the courses first
        try(Scanner scan = new Scanner(new File(courseFile))){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] courseTokens = line.split(",");
                String number = courseTokens[0];
                String title = courseTokens[1];
                int credits = Integer.parseInt(courseTokens[2]);
                Course c = new Course(number, title, credits);
                sm.addCourse(c);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File " + courseFile + " not found");
        }

        // Then read the students
        try(Scanner scan = new Scanner(new File(studentFile))){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] studentTokens = line.split(" ");
                int id = Integer.parseInt(studentTokens[0]);
                String name = studentTokens[1];
                String major = studentTokens[2];
                double gpa = Double.parseDouble(studentTokens[3]);
                Student s = new Student(id, name, major, gpa);
                sm.addStudent(s);
                for(int i=4; i<studentTokens.length; i++){
                    String courseNo = studentTokens[i];
                    sm.enrollStudent(id, courseNo);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File " + studentFile + " not found");
        }
    }
    public static void checkResponse(StudentManagement sm, int response, int id, String courseNo){
        switch(response){
            case -2: // student not found
                System.out.println("Student with id " + id + " not found");
                break;
            case -1: // course not found
                System.out.println("Course with number " + courseNo + " not found");
                break;
            case 0: // student not enrolled in the course
                System.out.println("Student with id " + id + " is not enrolled in " + courseNo);
                break;
            case 1: // student dropped successfully
                System.out.println("Student with id " + id + " was dropped successfully from " + courseNo);
                System.out.println("Updated list of courses: " + sm.getStudentCourses(id));
                break;
        }
    }
}
