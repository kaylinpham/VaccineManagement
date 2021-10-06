package controllers;

import dto.Vaccine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VaccineList extends ArrayList<Vaccine> {
    private final String PATH = "src/storage/vaccines.dat";

    public VaccineList() {
        readFromFile(PATH);
    }

    public Vaccine getVaccineByID(String ID) {
        for (Vaccine vaccine : this)
            if (vaccine.getID().equalsIgnoreCase(ID)) return vaccine;
        return null;
    }

    private void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Vaccine vaccine : this) oos.writeObject(vaccine);
            System.out.println("Save to file successfully.");
            System.out.println("FILE PATH: " + PATH);

            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save to file failed! " + e.getMessage());
        }
    }

    private void readFromFile(String filename) {
        if (this.size() > 0)
            this.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean flag = true;

            while (flag) {
                Vaccine vaccine = (Vaccine) ois.readObject();
                if (vaccine == null) flag = false;
                else this.add(vaccine);
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
}
