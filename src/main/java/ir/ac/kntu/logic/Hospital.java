package ir.ac.kntu.logic;

import ir.ac.kntu.department.*;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.person.*;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.SecurityUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hospital {

    private Department main;
    private Department icu;
    private Department burn;
    private Department emergency;

    private Admin currentAdmin;
    private SecurityUser currentSecurityUser;
    private Patient currentPatient;

    private List<Admin> admins;
    private List<SecurityUser> securitiesUser;
    //private List<Person> persons;
    private ArrayList<Facilities> facilities;
    private ArrayList<Security> securities;

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
        facilities = new ArrayList<Facilities>();
        securitiesUser = new ArrayList<SecurityUser>();
        //persons = new ArrayList<Person>();
        newDepartment();
    }

    public void newDepartment(){
        this.main = new Main();
        this.icu = new ICU();
        this.burn = new Burn();
        this.emergency = new Emergency();
    }

    public Department getIcu() {
        return icu;
    }

    public Department getBurn() {
        return burn;
    }

    public Department getMain() {
        return main;
    }

    public Department getEmergency() {
        return emergency;
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

    public String getName() {
        return name;
    }

    public SecurityUser getCurrentSecurityUser() {
        return currentSecurityUser;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Security> getSecurities() {
        return securities;
    }

    public ArrayList<Facilities> getFacilities() {
        return facilities;
    }

    public List<SecurityUser> getSecuritiesUser() {
        return securitiesUser;
    }

    public Department getDepartment(Hospital hospital){
        String dpName = ScannerWrapper.getInstance().getInput("Enter department:");
        switch (dpName) {
            case "EMG":
                return hospital.getEmergency();
            case "Burn":
                return hospital.getBurn();
            case "ICU":
                return hospital.getIcu();
            case "Main":
                return hospital.getMain();
            default:
                System.out.println("Wrong choice! ");
        }
        return null;
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