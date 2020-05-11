package ir.ac.kntu.menu;
import ir.ac.kntu.ScannerWrapper;

public class SecurityMenu {
    private static SecurityMenu instance = new SecurityMenu();

    private SecurityMenu() {
    }

    public static SecurityMenu getInstance() {
        return instance;
    }

    public Security.Option getOption() {
        Security.Option[] options = Security.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Security.Option.UNDEFINED;
    }

    public void printTheMenu(){
        System.out.println("***********************************");
        System.out.println("Menu:");
        System.out.println("1.Patient data");
        System.out.println("2.Doctor data");
        System.out.println("3.Nurse data");
        System.out.println("4.Other personnel data");
        System.out.println("5.Rooms data");
        System.out.println("6-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}
