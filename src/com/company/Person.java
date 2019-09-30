package com.company;

import java.util.Date;

public class Person implements java.lang.Comparable<Person>{
    private String name;
    private String surname;
    private Date birthDate;
    private int employeeNumber;
    private double salary;
    private String role;
    private String reportsTo;

    public Person(String name, String surname, Date birthDate, int employeeNumber, double salary, String role, String reportsTo) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.role = role;
        this.reportsTo = reportsTo;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Override
    public int compareTo(Person o) {
        if (this.getSalary() < o.getSalary()) {
            return -1;
        }
        if (this.getSalary() > o.getSalary()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
