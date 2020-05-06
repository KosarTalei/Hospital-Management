package ir.ac.kntu.menu;

public class AdminMenu implements Menu {
    @Override
    public void show() {
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
