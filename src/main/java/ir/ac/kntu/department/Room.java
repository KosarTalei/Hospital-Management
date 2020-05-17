package ir.ac.kntu.department;

import ir.ac.kntu.person.Patient;

import java.util.ArrayList;
import java.util.Objects;

public class Room{

    private Department department;
    private int bedsNum;
    private String roomNum;
    private ArrayList<Patient> occupants;

    private ArrayList<Item> items;
    private boolean hasTV;
    private boolean hasRefrigerator;
    private boolean hasAirConditioner;
    private String roomClass;

    public Room(){

    }

    public Room(String roomNum,int bedsNum,Department department) {
        this.department = department;
        this.roomNum = roomNum;
        this.bedsNum = bedsNum;
        occupants = new ArrayList<Patient>();
        items = new ArrayList<Item>();
    }

    public void setItems(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }

    public void setHasRefrigerator(boolean hasRefrigerator) {
        this.hasRefrigerator = hasRefrigerator;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getRoomClass() {
        return roomClass;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(roomNum, room.roomNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNum);
    }

    @Override
    public String toString() {
        return "Room{" +
                "department=" + department.getName() +
                ", bedsNum=" + bedsNum +
                ", roomNum='" + roomNum + '\'' +
                ", hasTV=" + hasTV +
                ", hasRefrigerator=" + hasRefrigerator +
                ", hasAirConditioner=" + hasAirConditioner +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }
}