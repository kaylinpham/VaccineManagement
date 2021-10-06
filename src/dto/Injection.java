package dto;

import controllers.StudentList;

import java.io.Serializable;

public class Injection implements Serializable {
    private static final long serialVersionUID = 10L;

    private String ID;
    private String studentID;
    private String vaccineID;
    private String firstDate;
    private String firstPlace;
    private String secondDate;
    private String secondPlace;

    private static StudentList students = new StudentList();

    public Injection(String ID, String studentID, String vaccineID, String firstDate, String firstPlace, String secondDate, String secondPlace) {
        this.ID = ID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstDate = firstDate;
        this.firstPlace = firstPlace;
        this.secondDate = secondDate;
        this.secondPlace = secondPlace;
    }

    public Injection(String ID, String studentID, String vaccineID, String firstDate, String firstPlace) {
        this.ID = ID;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.firstDate = firstDate;
        this.firstPlace = firstPlace;
        this.secondDate = null;
        this.secondPlace = null;
    }

    public String getID() {
        return ID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(String secondDate) {
        this.secondDate = secondDate;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    @Override
    public String toString() {
        return ID + "-" + studentID + "-" + vaccineID + "-" + firstDate + "-" + firstPlace + "-" + secondDate + "-" + secondPlace;
    }

    public void show() {
        System.out.printf("|%-6s|%-20s|%-10s|%-10s|%-10s|%-15s|%-10s|%-15s|\n", ID, students.getStudentByID(studentID).getName(), studentID, vaccineID, firstDate, firstPlace, secondDate, secondPlace);
    }
}
