package com.company;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        Vector<Person> people = new Vector<Person>();

        while (true) {
            System.out.print("Please enter a command ");
            Scanner scan = new Scanner(System.in);
            String s = scan.next();
            parseUserInput(s, people);
        }

    }

    private static void parseUserInput(String input, Vector<Person> people){

        if (input.equals("1")){
            getCSVAndCreateDirectory(people);
        }
        else if (input.equals("2")){
            addNameToDirectory(people);
        }
        else if (input.equals("3")){
            searchDirectory(people);
        }
        else if (input.equals("4")){
            searchClassInDirectory(people);
        }
        else if (input.equals("5")){
            getClassesWithoutInstructors(people);
        }
        else if (input.equals("6")){
            getClassesWithoutInstructors(people);
        }
    }

    private static void getCSVAndCreateDirectory(Vector<Person> people){
        System.out.print("Please enter the name of your CSV file ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        FileParser.parseFile(fileName, people);

        printDirectory(people);
    }

    private static void addNameToDirectory(Vector<Person> people){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the first and last name of the user you would like to add ");
        String name = scan.nextLine();

        String[] splited = name.split("\\s+");
        String firstName = splited[0];
        String lastName = splited[1];

        System.out.print("Please enter the type of person ");
        String role = scan.nextLine();

        System.out.print("Please enter email: ");
        String email = scan.nextLine();

        if (role.toLowerCase().equals("student")){
            addStudentToDirectory(firstName, lastName, people, email);
        }
        else if (role.toLowerCase().equals("ta")){
            addTAToDirectory(firstName, lastName, people, email);
        }
        else if (role.toLowerCase().equals("instructor")){
            addInstructorToDirectory(firstName, lastName, people, email);
        }
        else if (role.toLowerCase().equals("staff")){
            addStaffToDirectory(firstName, lastName, people, email);
        }

    }

    private static void searchDirectory(Vector<Person> people){
        System.out.print("Please enter the name you would like to search ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        String[] splited = name.split("\\s+");

        DatabaseSearcher.searchDirectory(splited, people);
    }

    public static void searchClassInDirectory(Vector<Person> people){

        System.out.print("Please enter the name of the course you would like to search ");
        Scanner scan = new Scanner(System.in);
        String courseName = scan.nextLine();

        String instructorName = DatabaseSearcher.getInstructorForCourse(people, courseName);
        Vector<TA> teachingAssistants = DatabaseSearcher.getTeachingAssistantsForCourse(people, courseName);
        Vector<Student> students = DatabaseSearcher.getStudentsTakingCourse(courseName, people);

        outPutClassStatistics(instructorName, teachingAssistants, students);

    }

    public static void getClassesWithoutInstructors(Vector<Person> people){
        System.out.println();

        Vector<String> coursesWithoutInstructors = DatabaseSearcher.getCoursesWithoutInstructors(people);
        Vector<Instructor> instructors = DatabaseSearcher.getInstructors(people);

        System.out.println("courses currently being taken by students which do not have an instructor: " + coursesWithoutInstructors);
        System.out.println("Current Instructors with classes:");

        for (Instructor i : instructors){
            System.out.println(i.getFirstName() + " " + i.getLastName() + ": " + i.coursesTeaching);
        }

        System.out.println();
    }

    public static void launchPayRoll(){
        
    }

    private static void addStudentToDirectory(String firstName, String lastName, Vector<Person> people, String email){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the current courses that are being taken separated by comma ");
        String coursesTaking = scan.nextLine();

        System.out.print("Please enter the previous courses taken separated by comma ");
        String coursesTaken = scan.nextLine();

        System.out.print("Please enter the GPA ");
        String gpa = scan.nextLine();

        Student s = new Student(coursesTaking, coursesTaken, gpa, firstName, lastName, email);
        people.add(s);

        System.out.println("Student added to Directory \n" + s.toString());
    }

    private static void addTAToDirectory(String firstName, String lastName, Vector<Person> people, String email){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the current courses that are being taken separated by comma ");
        String coursesTaking = scan.nextLine();

        System.out.print("Please enter the previous courses taken separated by comma ");
        String coursesTaken = scan.nextLine();

        System.out.print("Please enter the GPA ");
        String gpa = scan.nextLine();

        System.out.print("Please enter the salary ");
        String salary = scan.nextLine();

        System.out.print("Please enter the courses teaching separated by comma ");
        String coursesTeaching = scan.nextLine();

        TA tA = new TA(coursesTaking, coursesTaken, coursesTeaching, gpa, firstName, lastName, salary, email);
        people.add(tA);

        System.out.println("TA added to Directory \n" + tA.toString());
    }

    private static void addInstructorToDirectory(String firstName, String lastName, Vector<Person> people, String email){
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the salary ");
        String salary = scan.nextLine();

        System.out.print("Please enter the courses teaching separated by comma ");
        String coursesTeaching = scan.nextLine();

        Instructor instructor = new Instructor(coursesTeaching, firstName, lastName, salary, email);
        people.add(instructor);

        System.out.println("Instructor added to Directory \n" + instructor.toString());
    }

    private static void addStaffToDirectory(String firstName, String lastName, Vector<Person> people, String email){
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the salary ");
        String salary = scan.nextLine();

        Staff staff = new Staff(salary, firstName, lastName, email);
        people.add(staff);

        System.out.println("Staff added to Directory \n" + staff.toString());
    }

    private static void outPutClassStatistics(String instructorName, Vector<TA> teachingAssistants, Vector<Student> students){
        System.out.println("\nCourse Instructor: " + instructorName);
        System.out.print("Teaching assistants: ");

        for (TA p : teachingAssistants){
            System.out.print(p.getFirstName() + " " + p.getLastName() + ", ");
        }

        System.out.print("\nStudents taking the course: ");
        for (Student s : students){
            System.out.print(s.getFirstName() + " " + s.getLastName() + ", ");
        }

        System.out.println("\n");
    }

    private static void printDirectory(Vector<Person> people){
        for (Person p : people){
            System.out.println(p.toString());
        }
    }

}
