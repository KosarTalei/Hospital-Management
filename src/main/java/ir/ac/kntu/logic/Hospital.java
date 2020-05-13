package ir.ac.kntu.logic;

import ir.ac.kntu.*;
import ir.ac.kntu.menu.Admin;
import ir.ac.kntu.menu.SecurityUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital {

    private Admin currentAdmin;
    private SecurityUser currentSecurityUser;
    private Patient currentPatient;

    private List<Admin> admins;
    private List<Security> securities;
    private List<SecurityUser> securitiesUser;
    private List<Patient> patients;
    private List<Person> persons;///
    private ArrayList<Nurse> nurses;
    private ArrayList<Doctor> doctors;
    private ArrayList<Facilities> facilities;

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
        doctors = new ArrayList<Doctor>();
        nurses = new ArrayList<Nurse>();
        facilities = new ArrayList<Facilities>();
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentSecurityUser(SecurityUser currentSecurityUser) {
        this.currentSecurityUser = currentSecurityUser;
    }

    public SecurityUser getCurrentSecurityUser() {
        return currentSecurityUser;
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

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Facilities> getFacilities() {
        return facilities;
    }

    public List<SecurityUser> getSecuritiesUser() {
        return securitiesUser;
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
