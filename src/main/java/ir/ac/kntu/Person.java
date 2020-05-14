package ir.ac.kntu;

import java.util.ArrayList;

public abstract class Person {

    private PersonMng personMng;

    private String firstName;
    private  String lastName;
    private String id;
    private String position;

    Person(){
    }

    Person( String id,String firstName,String lastName,String position){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    abstract public boolean addPerson(Person person);
    abstract public Person getPerson(String personId);

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
		}
        if (obj == null){
            return false;
		}
        if (getClass() != obj.getClass()){
            return false;
		}
        Person other = (Person) obj;
        if (id == null) {
            if (other.id != null){
                return false;
			}
        } else if (!id.equals(other.id)){
            return false;
		}
        if (firstName == null) {
            if (other.firstName != null){
                return false;
			}
        } else if (!firstName.equals(other.firstName)){
            return false;
		}
        if (lastName == null) {
            if (other.lastName != null){
                return false;
			}
        } else if (!lastName.equals(other.lastName)){
            return false;
		}
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result
                + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Person: " +
                ", id='" + id + '\''+
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}