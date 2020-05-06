package ir.ac.kntu.menu;

public class Security extends User {
   //PATIENTS DATA,ROOM DATA,PERSON DATA
    public Security(String userName, String password,String role) {
        super(userName, password, role);

    }

    @Override
    public boolean login(String userName,String password) {
        final boolean isAuthenticated = correctPassword(password) && correctUsername(userName);
        final boolean isAuthorized = isAllowedToDoThis();

        return isAuthenticated && isAuthorized;
    }

    private boolean isAllowedToDoThis(){ 
	    return true;
	}

    @Override
    public boolean addUser(User user) {
        if(!getUsers().contains(user)){
            getUsers().add(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(String userName) {
        for(int i=0;i<getUsers().size();i++){
            if((getUsers().get(i)).getUserName().equals(userName)){
                return getUsers().get(i);
            }
        }
        return null;
    }

    @Override
    public boolean correctPassword(String password) {
        for(int i=0;i<getUsers().size();i++){
            if((getUsers().get(i)).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean correctUsername(String userName) {
        for(int i=0;i<getUsers().size();i++){
            if((getUsers().get(i)).getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }}
