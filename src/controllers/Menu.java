package controllers;

import utilities.Validator;

import java.util.ArrayList;

public class Menu<E> extends ArrayList<E> {
    public Menu() {
    }

    public Menu(ArrayList<E> list) {
        super(list);
    }

    public int getChoice(String... message) {
        System.out.println("---------------------------------------------------------------------------------------------------------");
        if (message.length != 0) System.out.println(message[0]);

        if (this.size() == 0) {
            System.out.println("No choices");
            return -1;
        }

        for (int i = 0; i < this.size(); i++)
            System.out.println((i + 1) + " - " + this.get(i));
        return Validator.getInteger("Choose: ", 1, this.size());
    }

    public void addOptions(E... options) {
        for (E option : options) this.add(option);
    }

    public int int_getChoice() {
        int res;
        for (int i = 0; i < super.size(); i++) {
            System.out.println((i + 1) + " - " + super.get(i));
        }

        res = Validator.getInteger("Please choose an option 1..." + super.size() + ": ", 1, super.size());
        return res;
    }

    public E ref_getChoice() {
        return super.get(int_getChoice() - 1);
    }
}
