package ir.ac.kntu.user;

import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.person.Security;
import ir.ac.kntu.logic.Hospital;

public class SecurityUser {
    public enum Option{
        PATIENT,DOCTOR,NURSE,PERSONNEL,ROOM,EXIT,UNDEFINED
    }
    private Security security;
    private Hospital hospital;
    private User user;

    public SecurityUser(){
    }
    public SecurityUser(String userName, String password, String role) {
        this.user = new User(userName,password,"Security");
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public SecurityUser sign(){
        String prompt="Enter the userName:";
        String userName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter the password:";
        String password = ScannerWrapper.getInstance().getInput(prompt);

        return new SecurityUser(userName,password,"Security");
    }

    public boolean login(String userName,String password) {
        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    private boolean isAllowedToDoThis() { 
	    return true;
	}

    public boolean addUser(SecurityUser security) {
        if(!hospital.getSecuritiesUser().contains(security)){
            hospital.getSecuritiesUser().add(security);
            return true;
        }
        return false;
    }

    public SecurityUser getUser(String userName) {
        for(SecurityUser security: hospital.getSecuritiesUser()){
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
