package ir.ac.kntu.menu;

import ir.ac.kntu.logic.Hospital;

import static ir.ac.kntu.ScannerWrapper.getInput;

public class Admin {
    public enum Option{
        ADMIN,SECURITY,PATIENT,EXIT, UNDEFINED
    }
    private Hospital hospital;

    private User user;

    public Admin(){
    }

    public Admin(String userName, String password,String role){
        this.user = new User(userName,password,"Admin");
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Admin signAdmin(){

        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        Admin admin = new Admin(userName,password,"admin");
        return admin;
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

    private boolean isAllowedToDoThis() { 
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
        System.out.println("*");
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