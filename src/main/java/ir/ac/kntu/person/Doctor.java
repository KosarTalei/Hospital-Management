package ir.ac.kntu.person;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.shift.Schedule;
import ir.ac.kntu.shift.ShiftManagement;
import ir.ac.kntu.shift.TimeSpan;

import java.util.ArrayList;

public class Doctor extends Person implements ShiftManagement {

    public enum Option{
        ADD,SEE,DELETE,SHIFTS,ADD_SHIFT,REMOVE_SHIFT,EXIT,UNDEFINED
    }

    private Department department;

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

        doctorPatientList = new ArrayList<>();

        this.maxHrs = maxHrs;
        this.minHrs = minHrs;

        availability = new Schedule();
        shiftsTaken = new Schedule();

        scheduleHolder.add(availability);
        scheduleHolder.add(shiftsTaken);
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addPatient(Patient patient) {
        if (!doctorPatientList.contains(patient)) {
            patient.setDepartment(department);
            doctorPatientList.add(patient);
            System.out.println("patient added to doctor list successfully!");
            return;
        }
        System.out.println("patient already exist!");
    }

    public void removeDoctor(Doctor doctor){
        for (Patient patient : doctorPatientList){
            System.out.println(patient);
            patient.setDoctor(null);
            System.out.println("doctor successfully removed!");
            System.out.println("should add to another doctor.");
        }
        department.getDoctors().remove(doctor);
    }
    @Override
    public boolean addPerson(Person person) {
        if (!department.getDoctors().contains(person)) {
            ((Doctor) person).setDepartment(department);
            department.getDoctors().add((Doctor) person);
            System.out.println("doctor added successfully!");
            return true;
        }
        System.out.println("doctor already exist!");
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Doctor doctor : department.getDoctors()) {
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

    public ArrayList<Patient> getDoctorPatientList() {
        return doctorPatientList;
    }

    public void printDoctors() {
        int i = 0;
        for (Object obj : department.getDoctors()) {
            Doctor emp = (Doctor) obj;
            System.out.print("Doctor #" + i);
            emp.printSchedule();
            i++;
        }
    }
    public void printPatients() {
        int i = 1;
        for (Patient emp : doctorPatientList) {
            System.out.println("Patient #" + i );
            System.out.println(emp.toString());
            i++;
        }
    }
    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                "department=" + department.getName() +
                ", maxHrs=" + maxHrs +
                ", minHrs=" + minHrs +
                ", doctorPatientList=" + doctorPatientList.size() + "patients" +
                "} ";
    }
}
