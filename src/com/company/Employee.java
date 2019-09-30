package com.company;

import java.util.Date;

public class Employee extends Person {
    public Employee(String name, String surname, Date birthDate, int employeeNumber, double salary, String role, String reportsTo) {
        super(name, surname, birthDate, employeeNumber, salary, role, reportsTo);
    }
}
