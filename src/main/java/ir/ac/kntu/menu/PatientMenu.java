package ir.ac.kntu.menu;

import ir.ac.kntu.person.Patient;
import ir.ac.kntu.helper.ScannerWrapper;

public class PatientMenu {
    private static PatientMenu instance = new PatientMenu();

    private PatientMenu() {
    }

    public static PatientMenu getInstance() {
        return instance;
    }

    public Patient.Option getOption() {
        Patient.Option[] options = Patient.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Patient.Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Options:");
        System.out.println("1-Add new Patient");//sign
        System.out.println("2-Patient hospitalisation");
        System.out.println("3-See patient data");
        System.out.println("4-Change patient data");
        System.out.println("5-Invoice patient");
        System.out.println("6-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
    public Patient.ChangeOption getChangeOption() {
        Patient.ChangeOption[] options = Patient.ChangeOption.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Patient.ChangeOption.UNDEFINED;
    }

    public void printChangeMenu() {
        System.out.println("***********************************");
        System.out.println("Change:");
        System.out.println("1-First name");
        System.out.println("2-Last name");
        System.out.println("3-Age");
        System.out.println("4-Insurance");
        System.out.println("5-Illness");
        System.out.println("6-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}