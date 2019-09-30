package com.company;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

enum MenuOptions {
    Continue,
    FindEmployee,
    CheckAge,
    PrintOrganization,
    HighestEarners,
    Exit
}

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        ArrayList<String> textFileData = ReadTextFile("StaffData.txt");
        Person[] staff = new Person[textFileData.size()];

        //Manager manager = new Manager();
        Manager manager = new Manager(textFileData.get(0).split(",")[0],
                textFileData.get(0).split(",")[1],
                ParseDate(textFileData.get(0).split(",")[2]),
                Integer.parseInt(textFileData.get(0).split(",")[3]),
                Double.parseDouble(textFileData.get(0).split(",")[4]),
                textFileData.get(0).split(",")[5],
                textFileData.get(0).split(",")[6]);
        Employee employee1 = new Employee(textFileData.get(1).split(",")[0],
                textFileData.get(1).split(",")[1],
                ParseDate(textFileData.get(1).split(",")[2]),
                Integer.parseInt(textFileData.get(1).split(",")[3]),
                Double.parseDouble(textFileData.get(1).split(",")[4]),
                textFileData.get(1).split(",")[5],
                textFileData.get(1).split(",")[6]);
        Employee employee2 = new Employee(textFileData.get(2).split(",")[0],
                textFileData.get(2).split(",")[1],
                ParseDate(textFileData.get(2).split(",")[2]),
                Integer.parseInt(textFileData.get(2).split(",")[3]),
                Double.parseDouble(textFileData.get(2).split(",")[4]),
                textFileData.get(2).split(",")[5],
                textFileData.get(2).split(",")[6]);
        Trainee trainee1 = new Trainee(textFileData.get(3).split(",")[0],
                textFileData.get(3).split(",")[1],
                ParseDate(textFileData.get(3).split(",")[2]),
                Integer.parseInt(textFileData.get(3).split(",")[3]),
                Double.parseDouble(textFileData.get(3).split(",")[4]),
                textFileData.get(3).split(",")[5],
                textFileData.get(3).split(",")[6]);
        Trainee trainee2 = new Trainee(textFileData.get(4).split(",")[0],
                textFileData.get(4).split(",")[1],
                ParseDate(textFileData.get(4).split(",")[2]),
                Integer.parseInt(textFileData.get(4).split(",")[3]),
                Double.parseDouble(textFileData.get(4).split(",")[4]),
                textFileData.get(4).split(",")[5],
                textFileData.get(4).split(",")[6]);

        staff[0] = manager;
        staff[1] = employee1;
        staff[2] = employee2;
        staff[3] = trainee1;
        staff[4] = trainee2;

        //Menu
        PrintMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        MenuFunc(choice, staff);
    }

    //Method for reading from text file
    public static ArrayList<String> ReadTextFile(String fileName) throws IOException {
        ArrayList<String> rawText = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr =new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                rawText.add(line);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rawText;
    }

    //Method to print Menu
    public static void PrintMenu() {
        System.out.println("1. Find employee");
        System.out.println("2. Find employees older than specified date");
        System.out.println("3. Print organizational structure");
        System.out.println("4. Find highest earners");
        System.out.println("5. Exit");
    }

    //Method for menu functionality
    public static void MenuFunc(int which, Person[] staff) {
        MenuOptions menuOption = MenuOptions.values()[which];
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            switch (menuOption) {

                // 1
                case FindEmployee:
                    System.out.println("Enter name of staff member: ");
                    String input = scanner.nextLine();
                    //input not working as param unless literal
                    Person foundPerson = FindEmployee(input, staff);
                    NumberFormat numFormat = NumberFormat.getCurrencyInstance();
                    System.out.println("Name: " + foundPerson.getName());
                    System.out.println("Surname: " + foundPerson.getSurname());
                    System.out.println("Birth Date: " + sdf.format(foundPerson.getBirthDate()));
                    System.out.println("Employee Number: " + foundPerson.getEmployeeNumber());
                    System.out.println("Salary: " + numFormat.format(foundPerson.getSalary()));
                    System.out.println("Role: " + foundPerson.getRole());
                    System.out.println("Reports to: " + foundPerson.getReportsTo());
                    break;

                // 2
                case CheckAge:
                    System.out.println("Enter date: ");
                    String inputDate = scanner.nextLine();
                    System.out.println("Staff members born before entered date: ");
                    ArrayList<String> olderStaff = OlderThan(inputDate, staff);

                    for (String item:olderStaff
                    ) {
                        System.out.println(item);
                    }
                    break;

                // 3
                case PrintOrganization:
                    PrintOrganization(staff);
                    break;

                // 4
                case HighestEarners:
                    SortStaff(staff);
                    break;

                case Exit:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Selection");
                    break;

            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Method to parse a string value to a Date
    public static Date ParseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Method to find employee details based on input name
    public static Person FindEmployee(String name, Person[] staff) {
        Person person = new Person();

        for (Person item:staff
             ) {
            if (item.getName().equals(name)) {
                person = item;
            }
        }
        return person;
    }

    // Method to find all employees born before input date
    public static ArrayList<String> OlderThan(String dateString, Person[] staff) {
        ArrayList<String> olderPeople = new ArrayList<>();
        Date inputDate = ParseDate(dateString);
        for (Person item:staff
             ) {
            if (inputDate.after(item.getBirthDate())) {
                olderPeople.add(item.getName());
            }
        }
        return olderPeople;
    }

    // Method to print organizational structure
    public static void PrintOrganization(Person[] staff) {
        System.out.println("Organizational Structure");
        for (Person item:staff
             ) {
            if (item.getReportsTo().equals("None")) {
                System.out.println(item.getName() + " " + item.getSurname() + " (" + item.getRole() + ")");
                System.out.println("|");
                System.out.println("|");
            }
        }
        for (Person item:staff
        ) {
            if (item.getReportsTo().equals("John Smith")) {
                System.out.println("--> " + item.getName() + " " + item.getSurname() + " (" + item.getRole() + ")");
                System.out.println("    |");
                System.out.println("    |");
            }
        }
        for (Person item:staff
        ) {
            if (item.getReportsTo().equals("Jane Doe")) {
                System.out.println("    --> " + item.getName() + " " + item.getSurname() + " (" + item.getRole() + ")");
                System.out.println("        |");
                System.out.println("        |");
            }
        }
        for (Person item:staff
        ) {
            if (item.getReportsTo().equals("Jim Bean")) {
                System.out.println("        --> " + item.getName() + " " + item.getSurname() + " (" + item.getRole() + ")");
                System.out.println("        |");
                System.out.println("        |");
            }
        }
    }

    //Method for sorting staff by salary per tier
    public static void SortStaff(Person[] staff) {
        String highestManager = "Empty";
        String highestEmployee = "Empty";
        String highestTrainee = "Empty";
        double highestManagerSalary = 0;
        double highestEmployeeSalary = 0;
        double highestTraineeSalary = 0;
        NumberFormat numFormat = NumberFormat.getCurrencyInstance();

        for (Person item:staff
             ) {
            if (item.getRole().equals("Manager")) {
                if (item.getSalary() > highestManagerSalary) {
                    highestManager = item.getName() + " " + item.getSurname();
                    highestManagerSalary = item.getSalary();
                }
            }
            if (item.getRole().equals("Employee")) {
                if (item.getSalary() > highestEmployeeSalary) {
                    highestEmployee = item.getName() + " " + item.getSurname();
                    highestEmployeeSalary = item.getSalary();
                }
            }
            if (item.getRole().equals("Trainee")) {
                if (item.getSalary() > highestTraineeSalary) {
                    highestTrainee = item.getName() + " " + item.getSurname();
                    highestTraineeSalary = item.getSalary();
                }
            }
        }
        System.out.println("The highest earning member in each tier level is: ");
        System.out.println("Managers: " + highestManager + " - " + numFormat.format(highestManagerSalary));
        System.out.println("Employees: " + highestEmployee + " - " + numFormat.format(highestEmployeeSalary));
        System.out.println("Trainees: " + highestTrainee + " - " + numFormat.format(highestTraineeSalary));
    }
}