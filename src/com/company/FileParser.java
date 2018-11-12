package com.company;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class FileParser {

    public static Vector<Person> parseFile(String fileName, Vector<Person> people){
        try
        {
            CsvReader csvReader = new CsvReader(fileName);

            csvReader.readHeaders();

            while (csvReader.readRecord()) {
                String role = csvReader.get("Role");

                if (role.equals("Student")){
                    people.add(new Student(csvReader, "Student"));
                }

                else if (role.equals("TA")){
                    people.add(new TA(csvReader));
                }

                else if (role.equals("Instructor")){
                    people.add(new Instructor(csvReader));
                }

                else if (role.equals("Staff")){
                    people.add(new Staff(csvReader));
                }
                else{
                    Person p = parsePersonWithMissingData(csvReader);
                    if (p != null){
                        people.add(p);
                    }
                }

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    private static Person parsePersonWithMissingData(CsvReader csvReader){
        try
        {
            if (isInstructor(csvReader.get("Courses Taken"), csvReader.get("Courses Taught"))){
                return new Instructor(csvReader);
            }
            if (isTA(csvReader.get("Courses Taken"), csvReader.get("Courses Taught"))){
                return new TA(csvReader);
            }
            if (isStudent(csvReader.get("Courses Taking"), csvReader.get("Courses Taken"))){
                return new Student(csvReader, "Student");
            }
            if (isStaff(csvReader.get("Courses Taking"), csvReader.get("Courses Taken"), csvReader.get("Salary"))){
                return new Staff(csvReader);
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean isInstructor(String coursesTaken, String coursesTeaching){

        if (!coursesTaken.equals("") || coursesTeaching.equals("")){
            return false;
        }

        return true;
    }

    private static boolean isTA(String coursesTaken, String coursesTaught){

        if (coursesTaken.equals("") || coursesTaught.equals("")){
            return false;
        }
        return  true;
    }

    private static boolean isStudent(String coursesTaking, String coursesTaken){

        if (coursesTaking.equals("") && coursesTaken.equals("")){
            return false;
        }
        return  true;
    }

    private static boolean isStaff(String coursesTaking, String coursesTaken, String salary){

        if (coursesTaking.equals("") && coursesTaken.equals("") && !salary.equals("")){
            return true;
        }
        return  false;
    }
}
