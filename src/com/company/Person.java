package com.company;

import com.csvreader.CsvReader;

import java.io.IOException;

public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String role;


    Person(CsvReader csvReader, String role){
        try {
            String firstName = csvReader.get("First Name");
            String lastName = csvReader.get("Last Name");
            String email = csvReader.get("Email");

            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.role = role;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    Person(String firstName, String lastName, String email, String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString(){
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email + "\nRole: " + role;
    }


}
