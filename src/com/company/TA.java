package com.company;

import com.csvreader.CsvReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TA extends Student {

    List<String> coursesTeaching;
    String salary;

    TA(CsvReader csvReader){
        super(csvReader, "TA");

        try{
            this.coursesTeaching = parseCourses(csvReader.get("Courses Teaching"));
            this.salary = csvReader.get("Salary");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    TA(String coursesTaking, String coursesTaken, String coursesTeaching, String gpa, String firstName, String lastName, String salary, String email){
        super(coursesTaking, coursesTaken, gpa, firstName, lastName, email, "TA");

        this.coursesTeaching = parseCourses(coursesTeaching);
        this.salary = salary;
    }

    public boolean isTeachingCourse(String courseName){
        for (String course: coursesTeaching){
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
        return super.toString().trim() + "\ncourses Teaching: " + this.coursesTeaching + "\nsalary: " + salary + "\n";
    }


}
