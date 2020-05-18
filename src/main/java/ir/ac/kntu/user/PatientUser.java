package ir.ac.kntu.user;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.person.Patient;
import ir.ac.kntu.logic.Hospital;

public class PatientUser {

    public enum Option{
        DATA,SHIFT,INVOICE,EXIT, UNDEFINED
    }

    private Department department;
    private User user;

    public PatientUser(Patient patient) {

        this.user = new User(patient);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean login(String userName, String password) {
        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    private boolean isAllowedToDoThis() { 
	    return true;
	}

    public boolean correctPassword(String password) {
        for (Patient patient : department.getPatients()) {
            if (patient.getNationalNum().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean correctUsername(String userName) {
        for (Patient patient : department.getPatients()) {
            if (patient.getId().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(Patient patient) {
        if(!department.getPatients().contains(patient)){
            department.getPatients().add(patient);
            return true;
        }
        return false;
    }

    public Patient getUser(String userName) {
        for (Patient patient : department.getPatients()) {
            if (user.getUserName().equals(userName)) {
                return patient;
            }
        }
        return null;
    }

}
