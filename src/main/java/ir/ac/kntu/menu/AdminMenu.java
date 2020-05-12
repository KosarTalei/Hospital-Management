package ir.ac.kntu.menu;

import ir.ac.kntu.ScannerWrapper;

public class AdminMenu {
    private static AdminMenu instance = new AdminMenu();

    private AdminMenu() {
    }

    public static AdminMenu getInstance() {
        return instance;
    }

    public Admin.Option getOption() {
        Admin.Option[] options = Admin.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Admin.Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Admin options:");
        System.out.println("1-Sign new admin");
        System.out.println("2-Sign new security");
        System.out.println("3-Sign new patient");
        System.out.println("4-Patient Menu");
        System.out.println("5-Doctor Menu");
        System.out.println("6-Nurse Menu");
        System.out.println("7-Department Menu");
        System.out.println("8-Room Menu");
        System.out.println("9-Shift Menu");
        System.out.println("10-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}
