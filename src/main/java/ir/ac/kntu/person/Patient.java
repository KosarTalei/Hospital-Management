package ir.ac.kntu.person;

import ir.ac.kntu.department.*;
import ir.ac.kntu.helper.Date;

public class Patient extends Person {

    public enum Option {
        NEW,HOSPITALISATION, SEE, CHANG, INVOICE, EXIT, UNDEFINED
    }

    public enum ChangeOption {
        FIRST_NAME, LAST_NAME, AGE, INSURANCE, ILLNESS, EXIT, UNDEFINED
    }
    public enum Disease{
        Burn,Strike, Accident,Else;
    }

    private Date joinDate;
    private Disease disease;
    private Department department;
    private Room room;
    private int age;
    private String gender;
    private Doctor doctor;
    private Nurse nurse;
    private String nationalNum;
    private String insurance;


    public Patient() {
    }

    public Patient(String id, String firstName, String lastName) {

        super(id, firstName, lastName, "patient");
    }


    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setNationalNum(String nationalNum) {
        this.nationalNum = nationalNum;
    }

    public String getNationalNum() {
        return nationalNum;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Department getDepartment() {
        return department;
    }

    public Room getRoom() {
        return room;
    }

    public String getInsurance() {
        return insurance;
    }

    @Override
    public boolean addPerson(Person person) {
        if (!department.getPatients().contains(person)) {
            department.getPatients().add((Patient) person);
            System.out.println("patient added successfully!");
            return true;
        }
        System.out.println("patient already exist!");
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Patient patient : department.getPatients()) {
            if (patient.getId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", nationalNum='" + nationalNum + '\'' +
                ", insurance='" + insurance + '\'' +
                ", disease=" + disease +
                ", room=" + room +
                ", doctor=" + doctor.getFirstName() + doctor.getLastName() +
                ", nurse=" + nurse.getFirstName() + nurse.getLastName();
    }
}
