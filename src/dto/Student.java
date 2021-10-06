package dto;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ID;
    private String name;

    public Student(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ID + "-" + name;
    }

    public void show() {
        System.out.printf("|%8s|%-20s|\n", ID, name);
    }
}
