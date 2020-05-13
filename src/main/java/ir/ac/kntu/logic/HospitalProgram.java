package ir.ac.kntu.logic;

import ir.ac.kntu.*;
import ir.ac.kntu.department.*;
import ir.ac.kntu.menu.*;

import java.io.IOException;

public class HospitalProgram {

    public enum Option {
        DEFINE_HOSPITAL,SIGN_ADMIN,LOGIN_ADMIN, LOGIN_SECURITY,LOGIN_PATIENT,
        EXIT, UNDEFINED }

    public static void main(String[] argv) throws IOException {

        Option option;

        Hospital hospital = null;

        MainMenu.getInstance().printTheMenu();

        option = MainMenu.getInstance().getOption();

        while (option != Option.EXIT) {
            hospital = handleTheOption(hospital,option);
            MainMenu.getInstance().printTheMenu();
            option = MainMenu.getInstance().getOption();
        }
        ScannerWrapper.getInstance().close();
    }

    public static Hospital handleTheOption(Hospital hospital, Option option) throws IOException {
        switch (option) {
            case DEFINE_HOSPITAL:
                if (hospital == null) {
                    hospital = defineHospital();
                } else {
                    System.out.println("You've already defined the hospital!");
                }
                break;
            case SIGN_ADMIN:
                signAdmin(hospital);
                break;
            case LOGIN_ADMIN:
                loginAdmin(hospital);
                break;
            case LOGIN_SECURITY:
                loginSecurity(hospital);
                break;
            case LOGIN_PATIENT:
                loginPatient(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public static Hospital defineHospital() throws IOException {
        String prompt ="Enter the hospital name: ";
        String name = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter the hospital address: ";
        String address = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter the hospital beds number: ";
        int beds = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Hospital hospital = new Hospital(name,address,beds);
        System.out.println("Successfully defined.");
        return hospital;
    }

    public static void loginPatient(Hospital hospital){

        String prompt="Enter the userName(your id):";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password(your national number):";
        String password = ScannerWrapper.getInstance().getInput(prompt);
        Patient patient = getPatient(hospital,userName);
        PatientUser patientUser = new PatientUser(patient);
        hospital.setCurrentPatient(patient);
        if(patientUser.login(userName,password)){
            System.out.println("Patient successfully defined.");
            patientUserOption(hospital);
        }else{
            System.out.println("Wrong username or password!");
        }
    }

    private static void patientUserOption(Hospital hospital) {
        PatientUserMenu.getInstance().printTheMenu();
        PatientUser.Option option= PatientUserMenu.getInstance().getOption();
        while (option != PatientUser.Option.EXIT) {
            hospital = handlePatientUserOption(option,hospital);
            PatientUserMenu.getInstance().printTheMenu();
            option = PatientUserMenu.getInstance().getOption();
        }
    }

    public static Hospital handlePatientUserOption(PatientUser.Option option,Hospital hospital) {
        Patient patient = hospital.getCurrentPatient();
        switch (option){
            case DATA:
                System.out.println(patient);
                break;
            case SHIFT:
                Doctor doctor = patient.getDoctor();
                doctor.printSchedule();
                break;
            case INVOICE:
                Booking booking = new Booking();
                Payment payment = new Payment(booking);
                payment.pay(patient);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private static Patient getPatient(Hospital hospital,String id){
        for(Patient patient : hospital.getPatients()){
            if(patient.getId().equals(id)){
                return patient;
            }
        }
        return null;
    }

    private static Person getPerson(Hospital hospital){
        String prompt="Enter patient's id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        for(Person person : hospital.getPersons()){
            if(person.getId().equals(id)){
                return person;
            }
        }
        return null;
    }

    private static void signAdmin(Hospital hospital) {
        Admin admin = new Admin();
        admin = admin.signAdmin();
        admin.setHospital(hospital);
        if(admin.addUser(admin)) {
            System.out.println("Successfully signed!");
        }else {
            System.out.println("Security already signed!");
        }
    }
    public static void loginAdmin(Hospital hospital){

        String prompt="Enter the userName:";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password:";
        String password = ScannerWrapper.getInstance().getInput(prompt);

        Admin admin = new Admin(userName,password,"Admin");
        hospital.setCurrentAdmin(admin);
        admin.setHospital(hospital);

        if(admin.login(userName,password)){
            System.out.println("Admin successfully defined.");
            adminOption(hospital);
        }else{
            System.out.println("Wrong username or password!");
        }
    }
    private static void adminOption(Hospital hospital) {
        AdminMenu.getInstance().printTheMenu();
        Admin.Option option= AdminMenu.getInstance().getOption();
        while (option != Admin.Option.EXIT) {
            hospital = handleAdminOption(option,hospital);
            AdminMenu.getInstance().printTheMenu();
            option = AdminMenu.getInstance().getOption();
        }
    }
    public static Hospital handleAdminOption(Admin.Option option,Hospital hospital) {
        switch (option){
            case SIGN_ADMIN:
                signAdmin(hospital);
                break;
            case SIGN_SECURITY:
                signSecurity(hospital);
                break;
            case SIGN_PATIENT:
                Person person = getPerson(hospital);
                break;
            case PATIENT_MENU:
                patientOption(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }
    private static void patientOption(Hospital hospital) {
        PatientMenu.getInstance().printTheMenu();
        Patient.Option option= PatientMenu.getInstance().getOption();
        while (option != Patient.Option.EXIT) {
            hospital = handlePatientOption(option,hospital);
            PatientMenu.getInstance().printTheMenu();
            option = PatientMenu.getInstance().getOption();
        }
    }
    public static Hospital handlePatientOption(Patient.Option option,Hospital hospital) {
        switch (option){
            case NEW:
                PersonMng personMng = new PersonMng(hospital);
                personMng.createPerson();
                break;
            case SEE:
                String prompt = "Enter patient id:";
                String id = ScannerWrapper.getInstance().getInput(prompt);
                Patient patient = new Patient();
                patient = (Patient)patient.getPerson(id);
                System.out.println(patient);
                break;
            case CHANG:

            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }
    private static void signSecurity(Hospital hospital) {
        SecurityUser securityUser = new SecurityUser();
        securityUser = securityUser.sign();
        securityUser.setHospital(hospital);
        if (securityUser.addUser(securityUser)){
            System.out.println("Successfully signed!");
        }else {
            System.out.println("Security already signed!");
        }
    }

    public static void loginSecurity(Hospital hospital){
        String prompt="Enter the userName:";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password:";
        String password = ScannerWrapper.getInstance().getInput(prompt);

        SecurityUser security = new SecurityUser(userName, password, "Security");
        hospital.setCurrentSecurityUser(security);
        if(security.login(userName,password)){
            System.out.println("Security successfully defined.");
            securityOption(hospital);
        }else {
            System.out.println("Security should signed first!");
        }
    }

    private static void securityOption(Hospital hospital) {
        System.out.println("Security is successfully defined.");
        SecurityMenu.getInstance().printTheMenu();
        SecurityUser.Option option= SecurityMenu.getInstance().getOption();
        while (option != SecurityUser.Option.EXIT) {
            hospital = handleSecurityOption(option,hospital);
            SecurityMenu.getInstance().printTheMenu();
            option = SecurityMenu.getInstance().getOption();
        }
    }

    public static Hospital handleSecurityOption(SecurityUser.Option option, Hospital hospital) {
        switch (option){
            case PATIENT:
                String prompt="Enter the id:";
                String id = ScannerWrapper.getInstance().getInput(prompt);
                Patient patient = getPatient(hospital,id);
                System.out.println(patient);
                break;
            case DOCTOR:case NURSE:case PERSONNEL:
                System.out.println(getPerson(hospital));
                break;
            case ROOM:
                Room room = checkRoom();
                System.out.println(room);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public static Room checkRoom(){
        String prompt="Enter the room's department:";
        String department = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter the room number:";
        String number = ScannerWrapper.getInstance().getInput(prompt);

        Department department1 = Department.class.cast(department);//?

        for (Room room : department1.getRooms()){
            if (room.getRoomNum().equals(number)){
                return room;
            }
        }
        return null;
    }

}