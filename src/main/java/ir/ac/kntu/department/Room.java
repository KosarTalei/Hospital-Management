package ir.ac.kntu.department;

import ir.ac.kntu.Patient;

import java.util.ArrayList;
import java.util.Objects;

public class Room{

    private Department department;
    private boolean available;
    private int bedsNum;
    private String roomNum;
    private ArrayList<Patient> occupants;

    public Room(String roomNum,int bedsNum,Department department) {
        this.department = department;
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

    public int getBedsNum() {
        return bedsNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return available == room.available &&
                Objects.equals(roomNum, room.roomNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(available, roomNum);
    }

    public String toString() {
        return "Room{" +
                "available=" + available +
                ", occupants=" + occupants +
                ", bedsNum=" + bedsNum +
                ", roomNum=" + roomNum +
                '}';
    }
}
