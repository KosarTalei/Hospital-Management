package ir.ac.kntu.menu;

import java.util.List;
import ir.ac.kntu.Patient;
import ir.ac.kntu.Person;
import ir.ac.kntu.logic.Hospital;

public class User {

    private Hospital hospital;
    private String userName;
    private String password;
    private String role;

    public User(){

    }

    public User(Patient patient){
        this.userName = patient.getId();
        this.password = patient.getNationalNum();
        this.role = "Patient";
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //public abstract boolean login(String userName,String password);
    //public abstract boolean addUser(User user);
    //public abstract User getUser(String userName);
    //public abstract boolean correctPassword(String password);
    //public abstract boolean correctUsername(String userName);

    public boolean authenticate(String userName, String pass) {
        return this.userName.equals(userName) && this.password.equals(pass);
    }

}