package ir.ac.kntu;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.department.Room;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.menu.Admin;
import ir.ac.kntu.menu.AdminMenu;
import ir.ac.kntu.menu.PatientMenu;
import ir.ac.kntu.menu.User;

enum Disease{
    Burn,Strike, Accident,Else;
}
public class Patient extends Person {

    public enum Option {
        NEW, SEE, CHANG, INVOICE, EXIT, UNDEFINED
    }

    public enum ChangeOption {
        FIRST_NAME, LAST_NAME, AGE, INSURANCE, ILLNESS, EXIT, UNDEFINED
    }

    private User user;
    private Hospital hospital;

    private Date joinDate;
    private Disease disease;
    private Department department;
    private Room room;
    private int age;
    private String gender;
    private Doctor doctor;
    private String id;
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
    public boolean addPerson(Person person) {
        if (!hospital.getPatients().contains(person)) {
            hospital.getPatients().add((Patient) person);
            System.out.println("patient added successfully!");
            //do else
            return true;
        }
        System.out.println("patient already exist!");
        return false;
    }

    @Override
    public Person getPerson(String personId) {
        for (Patient patient : hospital.getPatients()) {
            if (patient.getId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }

    /*public void updatePatientDetails(Patient patient) {

        switch(ch) {
            case 1:
                prompt="Enter new patient's firstName:";
                String firstName = getInput(prompt);
                patient.setFirstName(firstName);
                prompt="Enter new patient's lastName:";
                String lastName = getInput(prompt);
                patient.setLastName(lastName);
                System.out.println("Patient updated !");
                break;
            case 2:
                prompt="Enter new patient insurance:";
                String insurance = getInput(prompt);
                patient.setInsurance(insurance);
                System.out.println("Patient updated !");
                break;
            case 3:
                prompt="Enter new Patient age:";
                int age = Integer.parseInt(getInput(prompt));
                patient.setAge(age);
                System.out.println("Patient updated !");
                break;
            case 4:
                prompt="Enter new Patient illness:";
                Disease disease = Disease.valueOf(getInput(prompt));
                patient.setDisease(disease);
                System.out.println("Patient updated !");
                break;
            default:
                System.out.println("Invalid choice.");
                break;

        }*/
}
