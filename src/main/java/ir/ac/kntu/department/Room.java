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
    private boolean tv;
    private boolean refrigerator;
    private boolean airConditioner;
    private String roomClass;
    private boolean available;

    public Room(){

    }

    public Room(String roomNum,int bedsNum,Department department) {
        this.department = department;
        this.roomNum = roomNum;
        this.bedsNum = bedsNum;
        occupants = new ArrayList<Patient>();
        items = new ArrayList<Item>();
        setAvailable(true);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setItems(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void setRefrigerator(boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
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

    public void printItems(){
        int i = 1;
        for (Item item : items) {
            System.out.println("Item #" + i );
            System.out.println(item.toString());
            i++;
        }
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
        return
                "department=" + department.getName() +
                ", bedsNum=" + bedsNum +
                ", roomNum='" + roomNum + '\'' +
                ", hasTV=" + tv +
                ", hasRefrigerator=" + refrigerator +
                ", hasAirConditioner=" + airConditioner +
                ", roomClass='" + roomClass + '\'';
    }
}