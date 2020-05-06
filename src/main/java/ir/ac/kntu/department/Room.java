package ir.ac.kntu.department;

import ir.ac.kntu.Patient;

import java.util.ArrayList;

public abstract class Room extends Department{

    private boolean available;
    private int bedsNum;
    private String roomNum;
    private ArrayList<Patient> occupants;

    public Room(String roomNum,int bedsNum,Department department) {

        super(department.getName());

        this.roomNum = roomNum;
        this.bedsNum = bedsNum;
        setAvailable(false);
        occupants = new ArrayList<Patient>();
    }

    public ArrayList<Patient> getOccupants() {
        return occupants;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setOccupants(ArrayList<Patient> occupants) {
        this.occupants = occupants;
    }

    public boolean addOccupant(Patient patient) {
        if (!occupants.contains(patient)) {
            occupants.add(patient);
            return true;
        }
        return false;
    }

    Patient getOccupant(String personId) {
        for (Patient patient : occupants) {
            if (patient.getId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
