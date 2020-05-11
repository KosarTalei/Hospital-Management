package ir.ac.kntu.logic;

import ir.ac.kntu.Patient;
import ir.ac.kntu.Person;
import ir.ac.kntu.menu.Admin;
import ir.ac.kntu.menu.Security;
import ir.ac.kntu.menu.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital {

    private User currentUser;

    private List<Admin> admins;
    private List<Security> securities;
    private List<Patient> patients;
    private List<Person> persons;

    public enum Option {
        LOGIN_ADMIN, LOGIN_SECURITY,LOGIN_PATIENT,
        EXIT, UNDEFINED }

    private String name;
    private String address;
    private int beds;

    public Hospital(String name,String address,int beds){
        this.name = name;
        this.address = address;
        this.beds = beds;

        admins = new ArrayList<Admin>();
        securities = new ArrayList<Security>();
        patients = new ArrayList<Patient>();
        persons = new ArrayList<Person>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null){
            return true;
        }
        if(!(obj instanceof Hospital)){
            return false;
        }
        Hospital otherHospital =(Hospital) obj;
        if(!this.name.equals(otherHospital.name)){
            return false;
        }
        return this.address.equals(otherHospital.address);
    }

    public int hashCode() {
        return Objects.hash(name, address);
    }
}
