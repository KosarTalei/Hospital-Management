package ir.ac.kntu;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.department.Room;
import ir.ac.kntu.menu.Security;
import ir.ac.kntu.menu.User;

import static ir.ac.kntu.ScannerWrapper.getInput;

enum Disease{
    BURN,STRIKE, ACCIDENT,ELSE;
}

public class Patient extends Person {

    private User user;

    private Date joinDate;
    private Disease disease ;
    private Department department;
    private Room room;
    private int age;
    private String  gender;
    private Doctor doctor;
    private String id;
    private String nationalNum;
    private String insurance;

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

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public void setNationalNum(String nationalNum) {
        this.nationalNum = nationalNum;
    }

    @Override
    public String getId() {
        return id;
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
    boolean addPerson(Person person) {
        if (!getPatients().contains(person)) {
            getPatients().add((Patient)person);//?
            return true;
        }
        return false;
    }

    @Override
    Person getPerson(String personId) {
        for (Patient patient : getPatients()) {
            if (patient.getId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }
}
