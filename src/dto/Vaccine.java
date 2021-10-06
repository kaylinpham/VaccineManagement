package dto;

import java.io.Serializable;

public class Vaccine implements Serializable {
    private static final long serialVersionUID = 4L;

    private String ID;
    private String name;
    private String origin;

    public Vaccine(String ID, String name, String origin) {
        this.ID = ID;
        this.name = name;
        this.origin = origin;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
