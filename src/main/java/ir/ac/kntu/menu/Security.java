package ir.ac.kntu.menu;

import ir.ac.kntu.logic.Hospital;

import static ir.ac.kntu.ScannerWrapper.getInput;

public class Security {
   //PATIENTS DATA,ROOM DATA,PERSON DATA
    public enum Option{
        PATIENT,DOCTOR,NURSE,PERSONNEL,ROOM,EXIT,UNDEFINED
    }
    private Hospital hospital;
    private User user;
    public Security(){
        sign();
    }
    public Security(String userName, String password,String role) {
        User user = new User(userName,password,"Security");
        this.user = user;
    }

    public boolean sign(){
        String prompt="Enter the userName:";
        String userName = getInput(prompt);
        prompt="Enter the password:";
        String password = getInput(prompt);

        Security security = new Security(userName,password,"Security");
        try {
            addUser(security);
            System.out.println("Security successfully added.");
            return true;
        }catch (Exception e){
            System.out.println("Something wrong happened! ");
        }
        return false;
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
        if(!hospital.getSecurities().contains(user)){
            hospital.getSecurities().add(security);
            return true;
        }
        return false;
    }

    public Security getUser(String userName) {
        for(Security security: hospital.getSecurities()){
            if(user.getUserName().equals(userName)){//
                return security;
            }
        }
        return null;
    }

    public boolean correctPassword(String password) {
        for(Security security: hospital.getSecurities()){
            if(user.getPassword().equals(password)){//
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
