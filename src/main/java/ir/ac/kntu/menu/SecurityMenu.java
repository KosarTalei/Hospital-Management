package ir.ac.kntu.menu;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.user.SecurityUser;

public class SecurityMenu {
    private static SecurityMenu instance = new SecurityMenu();

    private SecurityMenu() {
    }

    public static SecurityMenu getInstance() {
        return instance;
    }

    public SecurityUser.Option getOption() {
        SecurityUser.Option[] options = SecurityUser.Option.values();
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return SecurityUser.Option.UNDEFINED;
    }

    public void printUserMenu(){
        System.out.println("***********************************");
        System.out.println("Menu:");
        System.out.println("1.Patient data");
        System.out.println("2.Doctor data");
        System.out.println("3.Nurse data");
        System.out.println("4.Facility data");
        System.out.println("5.Rooms data");
        System.out.println("6-Exit.");
        System.out.println("***********************************");
        System.out.print("\r\nPlease select your choice: ");
    }

}
