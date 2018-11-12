package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DatabaseSearcher {

    public static void searchDirectory(String [] wordQuerys,  Vector <Person> people){
        for (Person p:people){
            if (nameIsValid(p.getFirstName(), p.getLastName(), wordQuerys)){
                System.out.println(p.toString() + "\n");
            }
        }
    }

    public static String getInstructorForCourse(Vector <Person> people, String courseName){

        for (Person p : people){
            if (p.getClass() == Instructor.class) {
                Instructor i = (Instructor) p;
                if (i.isTeachingcourse(courseName)) {
                    return i.getFirstName() + " " + i.getFirstName();
                }
            }
        }

        return "";
    }

    public static Vector<TA> getTeachingAssistantsForCourse(Vector<Person> people, String courseName){

        Vector <TA> tas = new Vector<>();

        for (Person p : people){
            if (p.getClass() == TA.class) {
                TA ta = (TA) p;
                if (ta.isTeachingCourse(courseName)) {
                    tas.add(ta);
                }
            }
        }

        return tas;
    }

    public static Vector<Student> getStudentsTakingCourse(String courseName, Vector<Person> people){
        Vector <Student> students = new Vector<>();

        for (Person p : people){
            if (p.getClass() == Student.class) {
                Student s = (Student) p;
                if (s.isTakingCourse(courseName)) {
                    students.add(s);
                }
            }
        }

        return students;
    }

    public static Vector<String> getCoursesWithoutInstructors(Vector<Person> people){

        Vector<String> coursesWithoutInstructors = new Vector<>();
        Vector<String> allCourses = getCurrentStudentCourses(people);

        for (String course : allCourses){
            if (!courseHasInstructor(people, course)){
                coursesWithoutInstructors.add(course);
            }
        }

        return coursesWithoutInstructors;
    }

    public static Vector<Instructor> getInstructors(Vector <Person> people){

        Vector<Instructor> instructors = new Vector<>();

        for (Person p : people){
            if (p.getClass() == Instructor.class){
                Instructor i = (Instructor) p;
                instructors.add(i);
            }
        }

        return  instructors;
    }

    private static boolean courseHasInstructor(Vector<Person> people, String courseName){

        for (Person p: people){
            if (p.getClass() == Instructor.class){
                Instructor i = (Instructor) p;
                if(i.isTeachingcourse(courseName)){
                    return true;
                }
            }
        }

        return false;
    }

    private static Vector<String> getCurrentStudentCourses(Vector<Person> people){

        Vector<String> courses = new Vector<>();

        for (Person p : people){
            List<String> c = _getCurrentStudentCourses(p);

            for (String course:c){
                if (!courses.contains(course)){
                    courses.add(course);
                }
            }
        }

        return courses;
    }

    private static List<String> _getCurrentStudentCourses(Person person){
        List<String> studentCourses = new ArrayList<>();

        if (person.getClass() == Student.class){
            Student s = (Student) person;
            studentCourses = s.coursesTaking;
            return studentCourses;
        }

        return studentCourses;

    }

    private static boolean nameIsValid(String firstName, String lastName, String [] wordQuerys){
        if (wordQuerys.length == 1){

            String nameToSearch = wordQuerys[0];
            if (subStringExists(firstName, nameToSearch) || subStringExists(lastName, nameToSearch)) {
                return true;
            }
        }
        else{
            String searchFirstName = wordQuerys[0];
            String searchLastName = wordQuerys[1];
            if (subStringExists(firstName, searchFirstName) && subStringExists(lastName, searchLastName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean subStringExists(String parentString, String subString){
        if (parentString.indexOf(subString) != -1){
            return true;
        }
        return false;
    }

}
