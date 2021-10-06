package controllers;

import dto.Student;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StudentList extends ArrayList<Student> {
    private final String PATH = "src/storage/students.dat";

    public StudentList() {
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
                Student student = (Student) ois.readObject();
                if (student == null) flag = false;
                else this.add(student);
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

    public Student getStudentByID(String ID) {
        for (Student student : this)
            if (student.getID().equalsIgnoreCase(ID)) return student;
        return null;
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Student student : this) oos.writeObject(student);
            System.out.println("Save to file successfully.");
            System.out.println("FILE PATH: " + PATH);

            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save to file failed! " + e.getMessage());
        }
    }
}
