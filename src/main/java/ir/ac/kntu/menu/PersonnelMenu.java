package ir.ac.kntu.menu;

import ir.ac.kntu.person.Doctor;
import ir.ac.kntu.helper.ScannerWrapper;

public class PersonnelMenu {
    private static PersonnelMenu instance = new PersonnelMenu();

    private PersonnelMenu() {
    }

    public static PersonnelMenu getInstance() {
        return instance;
    }

    public Doctor.Option getOption() {
        Doctor.Option[] options = Doctor.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Doctor.Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Options:");
        System.out.println("1-Add new personnel");//sign
        System.out.println("2-See data personnel");
        System.out.println("3-Remove personnel");
        System.out.println("4-see personnel shifts");
        System.out.println("5-Add new shift");
        System.out.println("6-remove shift");
        System.out.println("7-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}
