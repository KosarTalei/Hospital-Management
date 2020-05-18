package ir.ac.kntu.user;

import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.logic.Hospital;

public class Admin {

    public enum Option{
        SIGN_ADMIN,SIGN_SECURITY,SIGN_PATIENT,PATIENT_MENU,DOCTOR_MENU,NURSE_MENU,
        SECURITY_MENU,FACILITY_MENU,ITEM_MENU,EXIT, UNDEFINED
    }

    private Hospital hospital;
    private User user;
    private String commonPass;

    public Admin(){
        setCommonPass("AdminUser");
    }

    public Admin(String userName, String password,String role){

        this.user = new User(userName,password,"Admin");
        setCommonPass("AdminUser");
    }

    public void setCommonPass(String commonPass) {
        this.commonPass = commonPass;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Admin signAdmin(){
        String prompt = "Enter Admin's pass:";
        String pass = ScannerWrapper.getInstance().getInput(prompt);
        if(pass.equals(commonPass)) {
            String userName = ScannerWrapper.getInstance().getInput("Enter the userName:");
            String password = ScannerWrapper.getInstance().getInput("Enter the password:");
            return new Admin(userName, password, "admin");
        }else {
            System.out.println("You have entered wrong admin's password!");
        }
        return null;
    }

    public Admin signAdmin(SecurityUser user){
        String userName = user.getUserName();
        String password = user.getPassword();
        return new Admin(userName,password,"admin");
    }

    private void setPassword(String password) {
        user.setPassword(password);
    }

    private void setUserName(String userName) {
        user.setUserName(userName);
    }

    public boolean login(String userName,String password) {

        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    public boolean isAllowedToDoThis() {
	    return true;
	}

    public boolean correctPassword(String password) {
        for (Admin admin : hospital.getAdmins()) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private Object getPassword() {
        return user.getPassword();
    }

    public boolean correctUsername(String userName) {
        for (Admin admin : hospital.getAdmins()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(Admin admin) {
        if(!hospital.getAdmins().contains(admin)){
            hospital.getAdmins().add(admin);
            return true;
        }
        return false;
    }

    public Admin getUser(String userName) {
        for (Admin admin : hospital.getAdmins()) {
            if (user.getUserName().equals(userName)) {
                return admin;
            }
        }
        return null;
    }
}