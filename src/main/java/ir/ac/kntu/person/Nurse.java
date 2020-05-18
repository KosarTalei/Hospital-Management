package ir.ac.kntu.person;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.shift.Schedule;
import ir.ac.kntu.shift.ShiftManagement;
import ir.ac.kntu.shift.TimeSpan;

import java.util.ArrayList;

public class Nurse extends Person implements ShiftManagement{

    public enum Option{
        ADD,SEE,DELETE,SHIFTS,ADD_SHIFT,REMOVE_SHIFT,EXIT,UNDEFINED
    }

    private Department department;
    private Schedule shiftsTaken;
    private Schedule availability;
    private int maxHrs;
    private int minHrs;
    private ArrayList<Patient> nursePatientList;
    private ArrayList<Object> scheduleHolder = new ArrayList<Object>(2);

    public Nurse(){

    }
    public Nurse(String id, String firstName, String lastName, int maxHrs, int minHrs) {

        super(id, firstName, lastName, "nurse");

        nursePatientList = new ArrayList<>();

        this.maxHrs = maxHrs;
        this.minHrs = minHrs;

        availability = new Schedule();
        shiftsTaken = new Schedule();

        scheduleHolder.add(availability);
        scheduleHolder.add(shiftsTaken);
    }

    public void addPatient(Patient patient) {
        if (!nursePatientList.contains(patient)) {
            patient.setDepartment(department);
            nursePatientList.add(patient);
            System.out.println("patient added to nurse list successfully!");
            return;
        }
        System.out.println("patient already exist!");
    }

    public void removeNurse(Nurse nurse){
        for (Patient patient : nursePatientList){
            System.out.println(patient);
            patient.setNurse(null);
            System.out.println("nurse successfully removed!");
            System.out.println("should add to another nurse.");
        }
        department.getNurses().remove(nurse);
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean addPerson(Person person) {
        if (!department.getNurses().contains(person)) {
            department.getNurses().add((Nurse) person);
            System.out.println("nurse added successfully!");
            return true;
        }
        System.out.println("nurse already exist!");
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Nurse nurse : department.getNurses()) {
            if (nurse.getId().equals(personId)) {
                return nurse;
            }
        }
        return null;
    }
    @Override
    public void addShift(int day, TimeSpan shiftTime, int scheduleNumber) {
        Schedule temp = (Schedule)scheduleHolder.get(scheduleNumber);
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
                System.out.println("    Shift"+ i +"______");
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
                System.out.println("    Shift"+ i +"______");
                System.out.println("        Time In : " + span.getTimeIn());
                System.out.println("        Time Out: " + span.getTimeOut());
            }
        }
    }

    public ArrayList<Patient> getNursePatientList() {
        return nursePatientList;
    }

    public void printNurses() {
        int i = 0;
        for (Object obj : department.getNurses()) {
            Nurse emp = (Nurse) obj;
            System.out.print("Nurse #" + 0);
            emp.printSchedule();
            i++;
        }
    }

    public void printPatients() {
        int i = 1;
        for (Patient emp : nursePatientList) {
            System.out.println("Patient #" + i);
            System.out.println(emp.toString());
            i++;
        }
    }

    @Override
    public String toString() {
        return "Nurse{" +
                super.toString() +
                "hospital=" + department.getName() +
                ", maxHrs=" + maxHrs +
                ", minHrs=" + minHrs +
                ", nursePatientList=" + nursePatientList.size() +" patients" +
                "} ";
    }
}
