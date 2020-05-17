package ir.ac.kntu.menu;

import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.logic.HospitalProgram;

public class MainMenu{

    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public HospitalProgram.Option getOption() {
        HospitalProgram.Option[] options = HospitalProgram.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return HospitalProgram.Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Hospital options:");
        System.out.println("1-Define new hospital.");
        System.out.println("2-Sign as admin.");
        System.out.println("3-Login as admin.");
        System.out.println("4-Login as Security.");
        System.out.println("5-Login as Patient.");
        System.out.println("6-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}