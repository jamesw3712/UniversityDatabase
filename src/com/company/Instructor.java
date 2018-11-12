package com.company;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Instructor extends Person {

    String salary;
    List<String> coursesTeaching;

    Instructor(CsvReader csvReader){
        super(csvReader, "Instructor");

        try{
            this.coursesTeaching = this.parseCoursesTaught(csvReader.get("Courses Teaching"));
            this.salary = csvReader.get("Salary");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    Instructor(String coursesTeaching, String firstName, String lastName, String salary, String email){
        super(firstName, lastName, email, "Instructor");

        this.coursesTeaching = parseCoursesTaught(coursesTeaching);
        this.salary = salary;
    }

    public boolean isTeachingcourse(String courseName){
        for (String course : coursesTeaching){
            if (course.toLowerCase().equals(courseName.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    private List<String> parseCoursesTaught(String coursesTaught){
        return Arrays.stream(coursesTaught.split(",")).map(String::trim).collect(Collectors.toList());
    }

    public String toString(){
        return super.toString() + "\nSalary: " + salary + "\nCourses teaching: " + coursesTeaching + "\n";
    }
}
