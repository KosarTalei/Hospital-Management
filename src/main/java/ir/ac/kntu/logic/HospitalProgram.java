package ir.ac.kntu.logic;

import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.menu.*;

import java.util.ArrayList;
import java.util.List;

import static ir.ac.kntu.ScannerWrapper.getInput;

public class HospitalProgram {

    public enum Option {
        DEFINE_HOSPITAL,DEFINE_ADMIN,LOGIN_ADMIN, LOGIN_SECURITY,LOGIN_PATIENT,
        EXIT, UNDEFINED }

    private static List<User> users = new ArrayList<>();

    public static void main(String[] argv) {

        Option option;

        Hospital hospital = null;

        MainMenu.getInstance().printTheMenu();

        option = MainMenu.getInstance().getOption();

        while (option != Option.EXIT) {
            hospital = handleTheOption(hospital, option);
            MainMenu.getInstance().printTheMenu();
            option = MainMenu.getInstance().getOption();
        }
        ScannerWrapper.getInstance().close();
    }

    public static Hospital handleTheOption(Hospital hospital, Option option) {
        switch (option) {
            case DEFINE_HOSPITAL:
                defineHospital();
                break;
            case DEFINE_ADMIN:
                if (hospital == null) {
                    defineAdmin();
                } else {
                    System.out.println("You've already defined the hospital!");
                }
                break;
            case LOGIN_ADMIN:
                loginAdmin();
                break;
            case LOGIN_SECURITY:
                if (hospital != null) {
                    loginSecurity();
                } else {
                    System.out.println("You've already defined the hospital!");
                }
                break;
            case LOGIN_PATIENT:
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }
    public static Hospital defineHospital() {
        Hospital hospital;
        String prompt ="Enter the hospital name: ";
        String name = getInput(prompt);
        prompt ="Enter the hospital address: ";
        String address = getInput(prompt);
        prompt ="Enter the hospital beds number: ";
        int beds = Integer.parseInt(getInput(prompt));

        hospital = new Hospital(name,address,beds);

        System.out.println("Successfully defined.");
        return hospital;
    }
    public static User defineAdmin(){

        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        User admin = new Admin(userName,password,"Admin");

        users.add(admin);
        admin.initialize((ArrayList)users);
        return admin;
    }

    public static void loginAdmin(){

        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        User admin = new Admin(userName,password,"Admin");
        admin.initialize((ArrayList<User>) users);
        if(admin.login(userName,password)){
            admin.getMenu();
        }else{
            System.out.println("Wrong username or password!");
        }

    }
    public static void loginSecurity(){
        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        User user = new Security(userName, password, "Security");
        if(user.login(userName,password)){
            System.out.println("Security is successfully defined.");
        }else {
            System.out.println("Admin should defined Security first!");
        }
    }

}
