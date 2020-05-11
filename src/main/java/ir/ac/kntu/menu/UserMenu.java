package ir.ac.kntu.menu;
import ir.ac.kntu.logic.HospitalProgram;

public class UserMenu {

    private static UserMenu instance = new UserMenu();

    private UserMenu() {
    }

    public static UserMenu getInstance() {
        return instance;
    }

    public void printTheMenu() {
        System.out.println("***********************************");
        System.out.println("Menu:");
        System.out.println("1.Login as admin");
        System.out.println("2.Login as Personnel");
        System.out.println("3.Login as Security");
        System.out.println("4.Sign in");
        System.out.println("5-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }
}
