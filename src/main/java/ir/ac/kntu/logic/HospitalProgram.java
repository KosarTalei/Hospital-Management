package ir.ac.kntu.logic;

import ir.ac.kntu.department.Item;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.menu.*;
import ir.ac.kntu.person.Doctor;
import ir.ac.kntu.person.Patient;
import ir.ac.kntu.person.Security;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.PatientUser;
import ir.ac.kntu.user.SecurityUser;
import ir.ac.kntu.user.User;

public class HospitalProgram {

    public enum Option {
        DEFINE_HOSPITAL,SIGN_ADMIN,LOGIN_ADMIN, LOGIN_SECURITY,LOGIN_PATIENT,
        EXIT, UNDEFINED }

    public static void main(String[] argv)  {

        HandleMenuOption handleMenuOption = new HandleMenuOption();

        Option option;

        Hospital hospital = null;

        MainMenu.getInstance().printTheMenu();

        option = MainMenu.getInstance().getOption();

        while (option != Option.EXIT) {
            hospital = handleMenuOption.handleTheOption(hospital,option);
            MainMenu.getInstance().printTheMenu();
            option = MainMenu.getInstance().getOption();
        }
        ScannerWrapper.getInstance().close();
    }

    public Hospital defineHospital() {
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

    public void loginPatient(Hospital hospital){

        String prompt="Enter the password(your national number):";
        String password = ScannerWrapper.getInstance().getInput(prompt);
        Patient patient = getPatient(hospital);
        PatientUser patientUser = new PatientUser(patient);
        patientUser.setHospital(hospital);
        hospital.setCurrentPatient(patient);
        if(patientUser.login(patient.getId(),password)){
            System.out.println("Patient successfully defined.");
            patientUserOption(hospital);
        }else{
            System.out.println("Wrong username or password!");
        }
    }

    private Patient getPatient(Hospital hospital) {
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        return handleMenuOption.getPatient(hospital);
    }

    private void patientUserOption(Hospital hospital) {
        PatientUserMenu.getInstance().printTheMenu();
        PatientUser.Option option= PatientUserMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != PatientUser.Option.EXIT) {
            hospital = handleMenuOption.handlePatientUserOption(option,hospital);
            PatientUserMenu.getInstance().printTheMenu();
            option = PatientUserMenu.getInstance().getOption();
        }
    }

    public void signAdmin(Hospital hospital) {
        Admin admin = new Admin();
        admin = admin.signAdmin();
        admin.setHospital(hospital);
        if(admin.addUser(admin)) {
            System.out.println("Successfully signed!");
        }else {
            System.out.println("Security already signed!");
        }
    }

    public void loginAdmin(Hospital hospital){

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

    private void adminOption(Hospital hospital) {
        AdminMenu.getInstance().printTheMenu();
        Admin.Option option= AdminMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Admin.Option.EXIT) {
            hospital = handleMenuOption.handleAdminOption(option,hospital);
            AdminMenu.getInstance().printTheMenu();
            option = AdminMenu.getInstance().getOption();
        }
    }

    public void signSecurity(Hospital hospital) {
        SecurityUser securityUser = new SecurityUser();
        securityUser = securityUser.sign();
        securityUser.setHospital(hospital);
        if (securityUser.addUser(securityUser)){
            System.out.println("Successfully signed!");
            moreAccess(securityUser);
        }else {
            System.out.println("Security already signed!");
        }
    }

    public void moreAccess(SecurityUser securityUser){
        String prompt = "Do you want more access to admin for the user? y/n";
        String choice = ScannerWrapper.getInstance().getInput(prompt);
        if(choice.equals("y")){
            Admin admin = new Admin();
            admin.signAdmin(securityUser);
            admin.isAllowedToDoThis();
        }
    }

    public void loginSecurity(Hospital hospital){
        String prompt="Enter the userName:";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password:";
        String password = ScannerWrapper.getInstance().getInput(prompt);

        SecurityUser security = new SecurityUser(userName, password, "Security");
        security.setHospital(hospital);
        hospital.setCurrentSecurityUser(security);
        if(security.login(userName,password)){
            System.out.println("Security successfully defined.");
            securityUserOption(hospital);
        }else {
            System.out.println("Security should signed first!");
        }
    }

    private Security getSecurity(Hospital hospital) {
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        return handleMenuOption.getSecurity(hospital);
    }

    public void securityOption(Hospital hospital) {
        PersonnelMenu.getInstance().printTheMenu();
        Doctor.Option option= PersonnelMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Doctor.Option.EXIT) {
            hospital = handleMenuOption.handleSecurityOption(option,hospital);
            PersonnelMenu.getInstance().printTheMenu();
            option = PersonnelMenu.getInstance().getOption();
        }
    }

    public void nurseOption(Hospital hospital) {
        PersonnelMenu.getInstance().printTheMenu();
        Doctor.Option option= PersonnelMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Doctor.Option.EXIT) {
            hospital = handleMenuOption.handleNurseOption(option,hospital);
            PersonnelMenu.getInstance().printTheMenu();
            option = PersonnelMenu.getInstance().getOption();
        }
    }

    public void patientOption(Hospital hospital) {
        PatientMenu.getInstance().printTheMenu();
        Patient.Option option = PatientMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Patient.Option.EXIT) {
            hospital = handleMenuOption.handlePatientOption(option, hospital);
            PatientMenu.getInstance().printTheMenu();
            option = PatientMenu.getInstance().getOption();
        }
    }

    public void doctorOption(Hospital hospital) {
        PersonnelMenu.getInstance().printTheMenu();
        Doctor.Option option= PersonnelMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Doctor.Option.EXIT) {
            hospital = handleMenuOption.handleDoctorOption(option,hospital);
            PersonnelMenu.getInstance().printTheMenu();
            option = PersonnelMenu.getInstance().getOption();
        }
    }
    public void facilityOption(Hospital hospital) {
        PersonnelMenu.getInstance().printTheMenu();
        Doctor.Option option= PersonnelMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Doctor.Option.EXIT) {
            hospital = handleMenuOption.handleFacilityOption(option,hospital);
            PersonnelMenu.getInstance().printTheMenu();
            option = PersonnelMenu.getInstance().getOption();
        }
    }
    private void securityUserOption(Hospital hospital) {
        SecurityMenu.getInstance().printUserMenu();
        SecurityUser.Option option= SecurityMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != SecurityUser.Option.EXIT) {
            hospital = handleMenuOption.handleSecurityUserOption(option,hospital);
            SecurityMenu.getInstance().printUserMenu();
            option = SecurityMenu.getInstance().getOption();
        }
    }

    public void itemOption(Hospital hospital) {
        ItemsMenu.getInstance().printTheMenu();
        Item.Option option= ItemsMenu.getInstance().getOption();
        HandleMenuOption handleMenuOption = new HandleMenuOption();
        while (option != Item.Option.EXIT) {
            hospital = handleMenuOption.handleItemOption(option,hospital);
            ItemsMenu.getInstance().printTheMenu();
            option = ItemsMenu.getInstance().getOption();
        }
    }

}