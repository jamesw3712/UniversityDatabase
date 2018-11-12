package com.company;

import com.csvreader.CsvReader;

import java.io.IOException;

public class Staff extends Person {

    String salary;


    Staff(CsvReader csvReader){
        super(csvReader, "Staff");

        try{
            this.salary = csvReader.get("Salary");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    Staff(String salary, String firstName, String lastName, String email){
        super(firstName, lastName, email, "Staff");
        this.salary = salary;
    }

    public String toString(){
        return super.toString() + "\nsalary: " + salary + "\n";
    }
}
