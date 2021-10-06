package services;

import controllers.Menu;
import utilities.Validator;

public class AuthService {
    private static final String ADMIN_USERNAME = "giangph";
    private static final String ADMIN_PASSWORD = "se150249";

    public static boolean auth() {
        String username = Validator.getNoBlankString("Username: ");
        String password = Validator.getNoBlankString("Password: ");
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    public static boolean login() {
        Menu<String> subMenu = new Menu<String>();
        subMenu.addOptions("Yes", "No");
        boolean isLogin;

        while (true) {
            isLogin = auth();
            if (!isLogin) {
                System.out.println("Invalid username or password!");
                if (subMenu.getChoice("Do you want to login again?") == 2) {
                    System.out.println("You are logged in as a guest.");
                    return isLogin;
                }
            } else {
                System.out.println("Login successfully.");
                return isLogin;
            }
        }
    }
}
