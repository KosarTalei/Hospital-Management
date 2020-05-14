package ir.ac.kntu;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.department.Room;
import ir.ac.kntu.logic.Hospital;
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
    private String nationalNum;
    private String insurance;


    public Patient() {
    }

    public Patient(String id, String firstName, String lastName) {

        super(id, firstName, lastName, "patient");
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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

    public void changeOption(Hospital hospital) {
        PatientMenu.getInstance().printChangeMenu();
        Patient.ChangeOption option = PatientMenu.getInstance().getChangeOption();
        while (option != Patient.ChangeOption.EXIT) {
            hospital = updatePatientDetails(option, hospital);
            PatientMenu.getInstance().printChangeMenu();
            option = PatientMenu.getInstance().getChangeOption();
        }
    }

    public Hospital updatePatientDetails(Patient.ChangeOption option, Hospital hospital) {
        String prompt = "Enter patient id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient = (Patient)patient.getPerson(id);
        switch (option) {
            case FIRST_NAME:case LAST_NAME:
                prompt = "Enter new patient's firstName:";
                String firstName = ScannerWrapper.getInstance().getInput(prompt);
                patient.setFirstName(firstName);
                prompt = "Enter new patient's lastName:";
                String lastName = ScannerWrapper.getInstance().getInput(prompt);
                patient.setLastName(lastName);
                System.out.println("Patient updated !");
                break;
            case INSURANCE:
                prompt = "Enter new patient insurance:";
                String insurance = ScannerWrapper.getInstance().getInput(prompt);
                patient.setInsurance(insurance);
                System.out.println("Patient updated !");
                break;
            case AGE:
                prompt = "Enter new Patient age:";
                int age = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
                patient.setAge(age);
                System.out.println("Patient updated !");
                break;
            case ILLNESS:
                prompt = "Enter new Patient illness:";
                Disease disease = Disease.valueOf(ScannerWrapper.getInstance().getInput(prompt));
                patient.setDisease(disease);
                System.out.println("Patient updated !");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        return hospital;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "hospital=" + hospital.getName() +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", nationalNum='" + nationalNum + '\'' +
                ", insurance='" + insurance + '\'' +
                ", joinDate=" + joinDate +
                ", disease=" + disease +
                ", department=" + department +
                ", room=" + room +
                ", doctor=" + doctor ;
    }
}
