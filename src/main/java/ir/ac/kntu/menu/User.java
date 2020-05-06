package ir.ac.kntu.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private List<User> users;

    private String userName;
    private String password;
    private String role;

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

    public List<User> getUsers() {
        return users;
    }

    public void initialize(ArrayList<User> users){
        this.users = new ArrayList<>(users);
    }

    public abstract boolean login(String userName,String password);
    public abstract boolean addUser(User user);
    public abstract User getUser(String userName);
    public abstract boolean correctPassword(String password);
    public abstract boolean correctUsername(String userName);

    public Menu getMenu() {
        return new UserMenu();
    }

    public boolean authenticate(String userName, String pass) {
        return this.userName.equals(userName) && this.password.equals(pass);
    }
}