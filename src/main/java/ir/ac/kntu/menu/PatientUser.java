package ir.ac.kntu.menu;

import ir.ac.kntu.Patient;
import ir.ac.kntu.logic.Hospital;

public class PatientUser {
    private Hospital hospital;
    private User user;

    public PatientUser(Object obj){
        User user = new User((Patient)obj);
        this.user = user;
    }

    public boolean login(String userName,String password) {
        System.out.println("+"+correctPassword(password));
        System.out.println("++"+correctUsername(userName));
        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    private boolean isAllowedToDoThis() { 
	    return true;
	}

    public boolean correctPassword(String password) {
        for (Patient patient : hospital.getPatients()) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean correctUsername(String userName) {
        for (Patient patient : hospital.getPatients()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(Patient patient) {
        if(!hospital.getPatients().contains(patient)){
            hospital.getPatients().add(patient);
            return true;
        }
        return false;
    }

    public Patient getUser(String userName) {
        for (Patient patient : hospital.getPatients()) {
            if (user.getUserName().equals(userName)) {
                return patient;
            }
        }
        return null;
    }

}
