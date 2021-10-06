package main;

import controllers.InjectionList;
import controllers.Menu;
import dto.Injection;
import services.AuthService;
import utilities.Validator;

public class VaccineManagement {
    public static void main(String[] args) {
        InjectionList injections = new InjectionList();
        Menu<String> adMenu = new Menu<>();
        Menu<String> guestMenu = new Menu<>();
        Menu<String> subMenu = new Menu<>();
        subMenu.addOptions("Yes", "No");
        boolean flag = true;

        System.out.println("FPT University Student Vaccination Management - @ 2021 by <SE150249 - Phạm Hà Giang>");
        boolean isLogin = AuthService.login();

        adMenu.addOptions(
                "Show information all students have been injected",
                "Add student's vaccine injection information",
                "Updating information of students' vaccine injection",
                "Delete student vaccine injection information",
                "Search for injection information by studentID",
                "Store injection information to file",
                "Logout",
                "Quit"
        );

        guestMenu.addOptions(
                "Search for injection information by studentID",
                "Login",
                "Quit"
        );


        while (flag) {
            int choice;

            if (isLogin) {
                choice = adMenu.getChoice("---ADMIN MENU---");
                switch (choice) {
                    case 1:
                        injections.showAllInjectedStudents();
                        break;
                    case 2:
                        while (true) {
                            Injection added = injections.addNewInjection();
                            if (added != null) {
                                System.out.println("Add new injection successfully.");
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                                System.out.printf("|ID%4s|Student Name%8s|Student ID|Vaccine ID|First One |First Place%4s|Second One|Second Place%3s|\n", "", "", "", "");
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                                added.show();
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                            }
                            int subChoice = subMenu.getChoice("Do you want to continue adding another injection information?");
                            if (subChoice == 2) break;
                        }
                        break;
                    case 3:
                        while (true) {
                            if (injections.size() == 0){
                                System.out.println("No students to update!");
                                break;
                            }

                            String ID = Validator.getNoBlankString("Enter injection's ID to update: ");
                            Injection updated = injections.updateInjectionByID(ID);
                            if (updated != null) {
                                System.out.println("Update injection successfully.");
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                                System.out.printf("|ID%4s|Student Name%8s|Student ID|Vaccine ID|First One |First Place%4s|Second One|Second Place%3s|\n", "", "", "", "");
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                                updated.show();
                                System.out.println("---------------------------------------------------------------------------------------------------------");
                            }
                            int subChoice = subMenu.getChoice("Do you want to continue updating another injection information?");
                            if (subChoice == 2) break;
                        }
                        break;
                    case 4:
                            while (true) {
                                if (injections.size() == 0){
                                    System.out.println("No students to delete!");
                                    break;
                                }

                                String ID = Validator.getNoBlankString("Enter injection's ID to delete: ");
                                Injection deleted = injections.deleteInjectionByID(ID);
                                if (deleted != null) System.out.println("Delete successfully.");
                                int subChoice = subMenu.getChoice("Do you want to continue removing another injection information?");
                                if (subChoice == 2) break;
                            }
                        break;
                    case 5:
                        while (true) {
                            if (injections.size() == 0){
                                System.out.println("No student has been vaccinated!");
                                break;
                            }

                            String ID = Validator.getNoBlankString("Enter student's ID: ");
                            injections.showInjectionByStudentID(ID);
                            int subChoice = subMenu.getChoice("Do you want to continue searching another injection information?");
                            if (subChoice == 2) break;
                        }
                        break;
                    case 6:
                        injections.saveToFile();
                        break;
                    case 7:
                        System.out.println("You are logged out.");
                        isLogin = false;
                        break;
                    case 8:
                        flag = false;
                        break;
                }
            } else {
                choice = guestMenu.getChoice("---GUEST MENU---");

                switch (choice) {
                    case 1:
                        while (true) {
                            String ID = Validator.getNoBlankString("Enter student's ID: ");
                            injections.showInjectionByStudentID(ID);
                            int subChoice = subMenu.getChoice("Do you want to continue searching another injection information?");
                            if (subChoice == 2) break;
                        }
                        break;
                    case 2:
                        isLogin = AuthService.login();
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            }
        }

        System.out.println("Goodbye <3");
        System.gc();
    }
}
