package ir.ac.kntu.menu;

import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.logic.Hospital;

public class Security {
    public enum Option{
        PATIENT,DOCTOR,NURSE,PERSONNEL,ROOM,EXIT,UNDEFINED
    }

    private Hospital hospital;
    private User user;

    public Security(){
    }
    public Security(String userName, String password,String role) {
        this.user = new User(userName,password,"Security");
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Security sign(){
        String prompt="Enter the userName:";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password:";
        String password = ScannerWrapper.getInstance().getInput(prompt);

        Security security = new Security(userName,password,"Security");
        return security;
    }

    public boolean login(String userName,String password) {
        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    private boolean isAllowedToDoThis() { 
	    return true;
	}

    public boolean addUser(Security security) {
        if(!hospital.getSecurities().contains(security)){
            hospital.getSecurities().add(security);
            return true;
        }
        return false;
    }

    public Security getUser(String userName) {
        for(Security security: hospital.getSecurities()){
            if(user.getUserName().equals(userName)){
                return security;
            }
        }
        return null;
    }

    public boolean correctPassword(String password) {
        for(Security security: hospital.getSecurities()){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean correctUsername(String userName) {
        for(Security security: hospital.getSecurities()){
            if(user.getUserName().equals(userName)){//
                return true;
            }
        }
        return false;
    }
}
