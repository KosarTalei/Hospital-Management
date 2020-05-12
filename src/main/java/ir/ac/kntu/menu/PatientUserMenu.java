package ir.ac.kntu.menu;
import ir.ac.kntu.ScannerWrapper;

public class PatientUserMenu {
    private static PatientUserMenu instance = new PatientUserMenu();

    private PatientUserMenu() {
    }

    public static PatientUserMenu getInstance() {
        return instance;
    }

    public void printTheMenu(){
        System.out.println("***********************************");
        System.out.println("Menu:");
        System.out.println("1.Your data");
        System.out.println("2.Your doctor shifts");
        System.out.println("3.Receive invoice");
        System.out.println("4-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }

    public PatientUser.Option getOption() {
        PatientUser.Option[] options = PatientUser.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return PatientUser.Option.UNDEFINED;
    }

}
