package ir.ac.kntu;

import ir.ac.kntu.Patient;
import ir.ac.kntu.Person;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.shift.Schedule;
import ir.ac.kntu.shift.ShiftManagement;
import ir.ac.kntu.shift.TimeSpan;

import java.util.ArrayList;

public class Doctor extends Person implements ShiftManagement {
    public enum Option{
        ADD,SEE,DELETE,SHIFTS,ADD_SHIFT,REMOVE_SHIFT,EXIT,UNDEFINED
    }
    private Hospital hospital;
    private Schedule shiftsTaken;
    private Schedule availability;
    private int maxHrs;
    private int minHrs;
    private ArrayList<Patient> doctorPatientList;
    private ArrayList<Object> scheduleHolder = new ArrayList<Object>(2);

    public Doctor(){

    }

    public Doctor(String id, String firstName, String lastName, int maxHrs, int minHrs) {

        super(id, firstName, lastName, "doctor");
        this.maxHrs = maxHrs;
        this.minHrs = minHrs;

        availability = new Schedule();
        shiftsTaken = new Schedule();

        scheduleHolder.add(availability);
        scheduleHolder.add(shiftsTaken);
    }

    @Override
    public boolean addPerson(Person person) {
        if (!hospital.getDoctors().contains(person)) {
            hospital.getDoctors().add((Doctor) person);//?
            System.out.println("doctor added successfully!");
            return true;
        }
        System.out.println("doctor already exist!");
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Doctor doctor : hospital.getDoctors()) {
            if (doctor.getId().equals(personId)) {
                return doctor;
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
    public void addPatientsToDoctor(Patient patient) {
        this.doctorPatientList.add(patient);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "hospital=" + hospital +
                ", shiftsTaken=" + shiftsTaken +
                ", availability=" + availability +
                ", maxHrs=" + maxHrs +
                ", minHrs=" + minHrs +
                ", doctorPatientList=" + doctorPatientList +
                ", scheduleHolder=" + scheduleHolder +
                "} " + super.toString();
    }
}
