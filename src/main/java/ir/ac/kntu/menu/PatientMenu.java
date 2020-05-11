package ir.ac.kntu.menu;
import ir.ac.kntu.logic.HospitalProgram;

public class PatientMenu {
    private static PatientMenu instance = new PatientMenu();

    private PatientMenu() {
    }

    public static PatientMenu getInstance() {
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
}
