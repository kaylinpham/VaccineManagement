package controllers;

import dto.Injection;
import dto.Student;
import dto.Vaccine;
import utilities.Validator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.util.ArrayList;

public class InjectionList extends ArrayList<Injection> {
    private StudentList students = new StudentList();
    private VaccineList vaccines = new VaccineList();
    private final String PATH = "src/storage/injections.dat";

    public InjectionList() {
        readFromFile(PATH);
    }

    private void readFromFile(String filename) {
        if (this.size() > 0)
            this.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean flag = true;

            while (flag) {
                Injection injection = (Injection) ois.readObject();
                if (injection == null) flag = false;
                else this.add(injection);
            }

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (EOFException e) {
            System.out.println("Read from file successfully!");
        } catch (Exception e) {
            System.out.println("Read from file failed!");
        }
    }

    public void showAllInjectedStudents() {
        if (this.size() == 0) System.out.println("No student has been vaccinated!");
        else {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("|ID%4s|Student Name%8s|Student ID|Vaccine%13s|First One |First Place%4s|Second One|Second Place%3s|\n", "", "", "", "", "");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            for (Injection injection : this)
                injection.show();
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
        }
    }

    private Injection getInjectionByStudentID(String studentID) {
        for (Injection injection : this)
            if (injection.getStudentID().equalsIgnoreCase(studentID)) return injection;
        return null;
    }

    public void showInjectionByStudentID(String studentID) {
        Injection injection = getInjectionByStudentID(studentID);
        Student student = students.getStudentByID(studentID);

        if (student == null) {
            System.out.println("Not found student ID!");
            return;
        }

        if (injection == null) {
            System.out.println("This student has not been vaccinated!");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|ID%4s|Student Name%8s|Student ID|Vaccine%13s|First One |First Place%4s|Second One|Second Place%3s|\n", "", "", "", "", "");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        injection.show();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public Injection addNewInjection() {
        String ID;
        while (true) {
            ID = Validator.getNoBlankString("ID: ");
            if (isDuplicatedID(ID)) System.out.println("This ID already exist!");
            else break;
        }

        Student student = (new Menu<Student>(students)).ref_getChoice();
        Injection info = getInjectionByStudentID(student.getID());

        if (info != null) {
            System.out.println("This student already registered for injection!");
            return null;
        } else {
            Vaccine vaccine = (new Menu<Vaccine>(vaccines)).ref_getChoice();

            String firstDate;
            while (true) {
                firstDate = Validator.getDateString("First date (dd/MM/yyyy): ");
                if (Validator.isFutureDate(firstDate, "dd/MM/yyyy"))
                    System.out.println("The date cannot be in future!");
                else break;
            }

            String firstPlace = Validator.getNoBlankString("First place: ");
            Injection injection = new Injection(ID, student.getID(), vaccine.getID(), firstDate, firstPlace);

            this.add(injection);
            return injection;
        }
    }

    public Injection updateInjectionByID(String ID) {
        Injection injection = getInjectionByID(ID);

        if (injection == null) System.out.println("Injection does not exist!");
        else {
            if (injection.getSecondDate() != null) {
                System.out.println("This student has completed 2 injections!");
                return null;
            } else {
                Menu confirm = new Menu();
                confirm.addOptions("Yes", "No");
                int choice = confirm.getChoice("Do you have vaccine " + vaccines.getVaccineByID(injection.getVaccineID()).getName() + " to vaccinate this student?");

                if (choice == 2) {
                    System.out.println("Do not have suitable vaccine to inject!");
                    return null;
                }

                String secondDate;
                secondDate = Validator.getDateString("Second date (dd/MM/yyyy): ");
                if (!Validator.isEnoughDate(injection.getFirstDate(), secondDate, "dd/MM/yyyy")) {
                    System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
                    return null;
                }

                injection.setSecondDate(secondDate);
                injection.setSecondPlace(Validator.getNoBlankString("Second place: "));
            }
        }

        return injection;
    }

    private boolean isDuplicatedID(String ID) {
        for (Injection injection : this)
            if (injection.getID().equalsIgnoreCase(ID)) return true;
        return false;
    }

    public Injection deleteInjectionByID(String ID) {
        Injection injection = getInjectionByID(ID);
        if (injection == null) {
            System.out.println("Not found injection ID!");
            return null;
        } else {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("|ID%4s|Student Name%8s|Student ID|Vaccine%13s|First One |First Place%4s|Second One|Second Place%3s|\n", "", "", "", "", "");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            injection.show();
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
        }

        Menu confirm = new Menu();
        confirm.addOptions("Yes", "No");
        int choice = confirm.getChoice("Do you want to delete this injection information?");

        if (choice == 1) {
            this.remove(injection);
            return injection;
        } else return null;
    }

    private Injection getInjectionByID(String ID) {
        for (Injection injection : this)
            if (injection.getID().equalsIgnoreCase(ID)) return injection;
        return null;
    }

    public void saveToFile() {
        if (this.size() == 0) {
            System.out.println("No injection information to store!");
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Injection injection : this) oos.writeObject(injection);
            System.out.println("Save to file successfully.");
            System.out.println("FILE PATH: " + PATH);

            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save to file failed! " + e.getMessage());
        }
    }
}
