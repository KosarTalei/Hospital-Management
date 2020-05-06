package ir.ac.kntu.logic;

import java.util.Objects;

public class Hospital {

    private String name;
    private String address;
    private int beds;

    public Hospital(String name,String address,int beds){
        this.name = name;
        this.address = address;
        this.beds = beds;
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
        if(!this.address.equals(otherHospital.address)){
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(name, address);
    }
}
