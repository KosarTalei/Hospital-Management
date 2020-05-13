package ir.ac.kntu;

import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.menu.Admin;
import ir.ac.kntu.menu.PatientMenu;

import java.util.ArrayList;

public class PersonMng {

    private Hospital hospital;

    public PersonMng(Hospital hospital){
        this.hospital = hospital;
    }

    public void createPerson(){
        try {
            makePerson();
        }catch(Exception e){
            System.out.println("Person wasn't properly entered.");
        }
    }

    public Person makePerson(){
        String prompt=("Enter fistName:");
        String firstName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter lastName:";
        String lastName = ScannerWrapper.getInstance().getInput(prompt);
        prompt = "Enter position:";
        String position = ScannerWrapper.getInstance().getInput(prompt);
        prompt = "Enter id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);

        switch (position) {
            case "patient":
                return createPatient(firstName, lastName, id);
            case "doctor":
                return createDoctor(firstName, lastName, id);
            case "nurse":
                return createNurse(firstName, lastName, id);
            case "security":
                return createSecurity(firstName, lastName, id);
            case "":
                return createFacilities(firstName,lastName,id);
            default:
                System.out.println("Wrong position!");
                break;
        }
        return null;
    }

    private Person createFacilities(String firstName, String lastName, String id) {
        String prompt;
        prompt = "What is the facility's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the facility's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Facilities facilities = new Facilities(id,firstName,lastName,mx,mn);
        facilities.addPerson(facilities);
        return facilities;
    }

    private Person createSecurity(String firstName, String lastName, String id) {
        String prompt;
        prompt = "What is the security's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the security's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Security security = new Security(id,firstName,lastName,mx,mn);
        security.addPerson(security);
        return security;
    }

    private Person createNurse(String firstName, String lastName, String id) {
        String prompt;
        prompt = "What is the nurse's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the nurse's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Nurse nurse = new Nurse(id,firstName,lastName,mx,mn);
        nurse.addPerson(nurse);
        return nurse;
    }

    private Person createDoctor(String firstName, String lastName, String id) {
        String prompt;
        prompt = "What is the doctor's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the doctor's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Doctor doctor = new Doctor(id,firstName,lastName,mx,mn);
        doctor.addPerson(doctor);
        return doctor;
    }

    private Person createPatient(String firstName, String lastName, String id) {
        Patient patient = new Patient(id,firstName,lastName);
        patient.addPerson(patient);
        return patient;
    }


    /*private void changeOption(Hospital hospital) {
        PatientMenu.getInstance().printChangeMenu();
        Patient.changeOption option= PatientMenu.getInstance().getChangeOption();
        while (option != Patient.changeOption.EXIT) {
            hospital = handleChangeOption(option,hospital);
            PatientMenu.getInstance().printChangeMenu();
            option = PatientMenu.getInstance().getChangeOption();
        }
    }

    public Hospital handleChangeOption(Patient.changeOption option, Hospital hospital) {
        String prompt = "Enter person position:";
        String position = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter person ID you want to update:";
        String idNum = ScannerWrapper.getInstance().getInput(prompt);

        switch (position) {
            case "patient":
                Patient patient = new Patient();
                patient = (Patient)patient.getPerson(idNum);
            case "doctor":
                return createDoctor(firstName, lastName, id);
            case "nurse":
                return createNurse(firstName, lastName, id);
            case "security":
                return createSecurity(firstName, lastName, id);
            case "":
                return createFacilities(firstName,lastName,id);
            default:
                System.out.println("Wrong position!");
                break;
        }
    }*/
}
