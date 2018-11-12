package com.company;
import com.csvreader.CsvReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student extends Person{

    List<String> coursesTaken;
    List<String> coursesTaking;
    String GPA;


    Student(CsvReader csvReader, String role){
        super(csvReader, role);

        try{

            this.GPA = csvReader.get("GPA");
            this.coursesTaking = parseCourses(csvReader.get("Courses Taking"));
            this.coursesTaken = parseCourses(csvReader.get("Courses Taken"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    Student(String coursesTaking, String coursesTaken, String gpa, String firstName, String lastName, String email){
        super(firstName, lastName, email, "Student");

        this.coursesTaking = parseCourses(coursesTaking);
        this.coursesTaken = parseCourses(coursesTaken);
    }

    Student(String coursesTaking, String coursesTaken, String gpa, String firstName, String lastName, String email, String role){
        super(firstName, lastName, email, role);

        this.coursesTaking = parseCourses(coursesTaking);
        this.coursesTaken = parseCourses(coursesTaken);
    }

    public boolean isTakingCourse(String courseName){

        for (String course : coursesTaking){
            if (course.toLowerCase().equals(courseName.toLowerCase())){
                return true;
            }
        }

        return false;
    }

    private List<String> parseCourses(String courses){
        return Arrays.stream(courses.split(",")).map(String::trim).collect(Collectors.toList());
    }

    public String toString(){
        return super.toString() + "\ncourses taken: " + coursesTaken + "\ncourses Taking: " + coursesTaking + "\nGPA: " + GPA + "\n";
    }

}
