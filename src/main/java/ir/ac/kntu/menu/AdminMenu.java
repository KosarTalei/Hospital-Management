package ir.ac.kntu.menu;
import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.logic.HospitalProgram;

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
        System.out.println("1-Add new admin");
        System.out.println("2-Add new security");
        System.out.println("3-Add new patient");
        System.out.println("4-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}
