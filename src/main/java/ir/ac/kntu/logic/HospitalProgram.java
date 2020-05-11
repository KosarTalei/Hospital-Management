package ir.ac.kntu.logic;

import ir.ac.kntu.Person;
import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.department.*;
import ir.ac.kntu.menu.*;

import static ir.ac.kntu.ScannerWrapper.getInput;

public class HospitalProgram {

    public enum Option {
        DEFINE_HOSPITAL,SIGN_ADMIN,LOGIN_ADMIN, LOGIN_SECURITY,LOGIN_PATIENT,
        EXIT, UNDEFINED }

    public static void main(String[] argv) {

        Option option;

        Hospital hospital = null;

        MainMenu.getInstance().printTheMenu();

        option = MainMenu.getInstance().getOption();

        while (option != Option.EXIT) {

            hospital = HospitalProgram.handleTheOption(hospital,option);
            MainMenu.getInstance().printTheMenu();
            option = MainMenu.getInstance().getOption();
        }
        ScannerWrapper.getInstance().close();
    }

    public static Hospital handleTheOption(Hospital hospital, Option option) {
        switch (option) {
            case DEFINE_HOSPITAL:
                if (hospital == null) {
                    hospital = defineHospital();
                } else {
                    System.out.println("You've already defined the hospital!");
                }
                break;
            case SIGN_ADMIN:
                Admin admin = new Admin();
                Admin admin1 = admin.signAdmin();
                admin1.setHospital(hospital);
                admin1.addUser(admin);
                break;
            case LOGIN_ADMIN:
                loginAdmin(hospital);
                break;
            case LOGIN_SECURITY:
                loginSecurity(hospital);
                break;
            case LOGIN_PATIENT:
                getPerson(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public static Hospital defineHospital() {
        String prompt ="Enter the hospital name: ";
        String name = getInput(prompt);
        prompt ="Enter the hospital address: ";
        String address = getInput(prompt);
        prompt ="Enter the hospital beds number: ";
        int beds = Integer.parseInt(getInput(prompt));

        Hospital hospital = new Hospital(name,address,beds);

        System.out.println("Successfully defined.");
        return hospital;
    }

    public static void loginAdmin(Hospital hospital){

        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        Admin admin = new Admin(userName,password,"Admin");
        admin.setHospital(hospital);

        if(admin.login(userName,password)){
            AdminMenu.getInstance().printTheMenu();
            Admin.Option option= AdminMenu.getInstance().getOption();
            while (option != Admin.Option.EXIT) {
                hospital = handleAdminOption(option,hospital);
                MainMenu.getInstance().printTheMenu();
                option = AdminMenu.getInstance().getOption();
            }
        }else{
            System.out.println("Wrong username or password!");
        }
    }
    public static Hospital handleAdminOption(Admin.Option option,Hospital hospital) {
        switch (option){
            case ADMIN:
                Admin admin = new Admin();
                break;
            case SECURITY:
                Security security = new Security();
                break;
            case PATIENT:
                Person person = getPerson(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }
    private static Person getPerson(Hospital hospital){
        String prompt="Enter patient's id:";
        String id = getInput(prompt);
        for(Person person : hospital.getPersons()){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }
    private static Person getPer(Hospital hospital){
        String prompt="Enter patient's id:";
        String id = getInput(prompt);
        for(Person person : hospital.getPersons()){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }
    public static void loginSecurity(Hospital hospital){
        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        Security security = new Security(userName, password, "Security");
        if(security.login(userName,password)){
            System.out.println("Security is successfully defined.");
            SecurityMenu.getInstance().printTheMenu();
            Security.Option option= SecurityMenu.getInstance().getOption();
            while (option != Security.Option.EXIT) {
                handleSecurityOption(option,hospital);
                MainMenu.getInstance().printTheMenu();
                option = SecurityMenu.getInstance().getOption();
            }
        }else {
            System.out.println("Admin should defined Security first!");
        }
    }

    public static void handleSecurityOption(Security.Option option,Hospital hospital) {
        switch (option){
            case PATIENT:case DOCTOR:case NURSE:case PERSONNEL:
                Person person = getPerson(hospital);
                System.out.println(person);
                break;
            case ROOM:
                Room room = checkRoom();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    public static Room checkRoom(){
        String prompt="Enter the room's department:";
        String department = getInput(prompt);
        prompt ="Enter the room number:";
        String number = getInput(prompt);

        Department department1 = Department.class.cast(department);//?

        for (Room room : department1.getRooms()){
            if (room.getRoomNum().equals(number)){
                return room;
            }
        }
        return null;
    }

}















/*if(department.equals("burn")){
        Burn burn = new Burn();
        for(Room room : burn.getRooms()){
        if (room.getRoomNum().equals(number)){
        return room;
        }
        }
        }
        if(department.equals("emergency")){
        Emergency emergency = new Emergency();
        for(Room room : emergency.getRooms()){
        if (room.getRoomNum().equals(number)){
        return room;
        }
        }
        }
        if(department.equals("ICU")){
        ICU icu = new ICU();
        for(Room room : icu.getRooms()){
        if (room.getRoomNum().equals(number)){
        return room;
        }
        }
        }*/