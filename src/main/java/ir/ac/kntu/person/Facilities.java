package ir.ac.kntu.person;

import ir.ac.kntu.department.Item;
import ir.ac.kntu.department.Room;
import ir.ac.kntu.helper.Date;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.manage.PatientMng;
import ir.ac.kntu.shift.Schedule;
import ir.ac.kntu.shift.ShiftManagement;
import ir.ac.kntu.shift.TimeSpan;
import java.util.ArrayList;

public class Facilities extends Person implements ShiftManagement {

    private Hospital hospital;

    private Schedule shiftsTaken;
    private Schedule availability;
    private int maxHrs;
    private int minHrs;

    private Boolean check;
    private Room room;

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    private ArrayList<Object> scheduleHolder = new ArrayList<Object>(2);

    public Facilities(){

    }

    public Facilities(String id, String firstName, String lastName,int maxHrs, int minHrs){
        super(id, firstName, lastName, "security");
        this.maxHrs = maxHrs;
        this.minHrs = minHrs;

        availability = new Schedule();
        shiftsTaken = new Schedule();

        scheduleHolder.add(availability);
        scheduleHolder.add(shiftsTaken);
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public boolean addPerson(Person person) {
        if (!hospital.getFacilities().contains(person)) {
            hospital.getFacilities().add((Facilities) person);//?
            return true;
        }
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Facilities facilities : hospital.getFacilities()) {
            if (facilities.getId().equals(personId)) {
                return facilities;
            }
        }
        return null;
    }
    @Override
    public void addShift(int day, TimeSpan shiftTime, int scheduleNumber) {
        Schedule temp = (Schedule) scheduleHolder.get(scheduleNumber);
        temp.add(day, shiftTime);
    }
    @Override
    public void removeShift(int day, TimeSpan shiftTime, int scheduleNumber) {
        Schedule temp = (Schedule)scheduleHolder.get(scheduleNumber);
        temp.remove(day, shiftTime);
    }
    @Override
    public void clearSchedule(int scheduleNumber) {
        Schedule temp = (Schedule)scheduleHolder.get(scheduleNumber);
        temp.clear();
    }
    @Override
    public boolean doesShiftExist(int day, TimeSpan shiftTime, int scheduleNumber) {
        Schedule tempSch = (Schedule)scheduleHolder.get(scheduleNumber);
        ArrayList<Object> list = getDaySchedule(day,scheduleNumber);
        for (Object obj : list) {
            TimeSpan span = (TimeSpan) obj;
            if (tempSch.isShiftWithinShift(shiftTime, span)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public ArrayList<Object> getDaySchedule(int day, int scheduleNumber) {
        Schedule temp = (Schedule)scheduleHolder.get(scheduleNumber);
        return temp.getDayList(day);
    }

    @Override
    public void printSchedule() {
        System.out.println("----------------------------------------------");
        System.out.println(getFirstName()+getLastName());
        System.out.println("[Availability]");
        System.out.println("-----------------------");
        for (int i = 0; i < 7; i++) {
            ArrayList<Object> tempList = getDaySchedule(i,0);
            for (Object obj : tempList) {
                TimeSpan span = (TimeSpan) obj;
                System.out.println("    Shift "+i+"______");
                System.out.println("        Time In : " + span.getTimeIn());
                System.out.println("        Time Out: " + span.getTimeOut());
            }
        }

        System.out.println("[Shifts Taken]");
        System.out.println("-----------------------");
        for (int i = 0; i < 7; i++) {
            ArrayList<Object> tempList = getDaySchedule(i,1);
            for (Object obj : tempList){
                TimeSpan span = (TimeSpan) obj;
                System.out.println("    Shift "+i+"______");
                System.out.println("        Time In : " + span.getTimeIn());
                System.out.println("        Time Out: " + span.getTimeOut());
            }
        }
    }

    public int getMaxHrs() {
        return maxHrs;
    }

    public int getMinHrs() {
        return minHrs;
    }

    public Schedule getShiftsTaken() {
        return shiftsTaken;
    }

    public void checkFacility(Facilities facility){
        Room room = facility.getRoom();
        for(Item item : room.getItems()){
            if(!item.getHealthy()){
                item.setHealthy(true);
                facility.setCheck(true);
                PatientMng patientMng = new PatientMng();
                Date date = patientMng.getDate("check up");
                item.setCheckUp(date);
                System.out.println("facility"+facility+" checked "+item.getItemName()+" of this room");
            }
        }
    }

    public boolean sayFacility(Facilities facility,TimeSpan time,Room room,int day) {
        ArrayList<Object> list = facility.getDaySchedule(day, 1);
        for (Object obj : list) {
            TimeSpan tSpan = (TimeSpan) obj;
            if( tSpan.getTimeIn() <= time.getTimeIn() && time.getTimeIn()<= time.getTimeOut() ){
                facility.setCheck(false);
                facility.setRoom(room);
                System.out.println("Facility "+facility+" was selected");
                return true;
            }
        }
        return false;
    }

    public void printFacility() {
        int i = 0;
        for (Object obj : hospital.getFacilities()) {
            Facilities emp = (Facilities) obj;
            System.out.print("Facility #" + i);
            emp.printSchedule();
            i++;
        }
    }

    @Override
    public String toString() {
        return super.toString()+
                ", maxHrs=" + maxHrs +
                ", minHrs=" + minHrs;
    }
}
