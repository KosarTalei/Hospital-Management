package ir.ac.kntu;

import ir.ac.kntu.logic.Hospital;
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
    private ArrayList<Object> scheduleHolder = new ArrayList<Object>(2);

    public Facilities(String id, String firstName, String lastName,int maxHrs, int minHrs){
        super(id, firstName, lastName, "security");
        this.maxHrs = maxHrs;
        this.minHrs = minHrs;

        availability = new Schedule();
        shiftsTaken = new Schedule();

        scheduleHolder.add(availability);
        scheduleHolder.add(shiftsTaken);
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
                System.out.println("    Shift______");
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
                System.out.println("    Shift______");
                System.out.println("        Time In : " + span.getTimeIn());
                System.out.println("        Time Out: " + span.getTimeOut());
            }
        }
    }
}
