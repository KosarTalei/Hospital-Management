package ir.ac.kntu;

import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.menu.PatientMenu;
import ir.ac.kntu.shift.TimeSpan;

import java.util.ArrayList;

public class PersonMng {

    private Hospital hospital;

    public PersonMng(){
    }
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    public void createPerson(){
        makePerson();
        /*try {
            makePerson();
        }catch(Exception e){
            System.out.println("Person wasn't properly entered.");
        }*/
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
        String prompt = "What is the facility's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the facility's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Facilities facilities = new Facilities(id,firstName,lastName,mx,mn);
        facilities.addPerson(facilities);
        return facilities;
    }

    private Person createSecurity(String firstName, String lastName, String id) {
        String prompt = "What is the security's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the security's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Security security = new Security(id,firstName,lastName,mx,mn);
        security.addPerson(security);
        return security;
    }

    private Person createNurse(String firstName, String lastName, String id) {
        String prompt = "What is the nurse's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the nurse's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Nurse nurse = new Nurse(id,firstName,lastName,mx,mn);
        nurse.addPerson(nurse);
        return nurse;
    }

    private Person createDoctor(String firstName, String lastName, String id) {
        String prompt = "What is the doctor's Min Hrs?";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "What is the doctor's Max Hrs?";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Doctor doctor = new Doctor(id,firstName,lastName,mx,mn);
        doctor.addPerson(doctor);//add shift?
        return doctor;
    }

    private Person createPatient(String firstName, String lastName, String id) {
        Patient patient = new Patient(id,firstName,lastName);
        patient.setHospital(hospital);
        patient.addPerson(patient);
        return patient;
    }


    private void changeOption(Hospital hospital) {
        PatientMenu.getInstance().printChangeMenu();
        Patient.ChangeOption option= PatientMenu.getInstance().getChangeOption();
        while (option != Patient.ChangeOption.EXIT) {
            hospital = handleChangeOption(option,hospital);
            PatientMenu.getInstance().printChangeMenu();
            option = PatientMenu.getInstance().getChangeOption();
        }
    }

    public Hospital handleChangeOption(Patient.ChangeOption option, Hospital hospital) {
        String prompt = "Enter person position:";
        String position = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter person ID you want to update:";
        String idNum = ScannerWrapper.getInstance().getInput(prompt);

        if ("patient".equals(position)) {
            Patient patient = new Patient();
            patient = (Patient) patient.getPerson(idNum);
            patient.changeOption(hospital);
        }
        System.out.println("Wrong position!");
        return hospital;
    }

    public void printDoctors() {
        int i = 0;
        for (Object obj : hospital.getDoctors()) {
            Doctor emp = (Doctor) obj;
            System.out.print("Doctor #" + i);
            emp.printSchedule();
            i++;
        }
    }
    public void input(String position){
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which personnel?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));
        float start = Float.parseFloat(ScannerWrapper.getInstance().getInput("Start time?"));
        float end = Float.parseFloat(ScannerWrapper.getInstance().getInput("End time?"));
        if(position.equals("doctor")){
            addToDocSchedule(empChoice,schChoice,day,start,end);
        }else if(position.equals("nurse")){
            addToNurseSchedule(empChoice,schChoice,day,start,end);
        }
    }
    public void addToDocSchedule(int empChoice,int schChoice,int day,float start , float end) {
        TimeSpan tSpan = new TimeSpan(start, end);
        Doctor emp = hospital.getDoctors().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromDocSchedule() {
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which doctor?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Doctor emp = hospital.getDoctors().get(empChoice);
        ArrayList<Object> list = emp.getDaySchedule(day, schChoice);
        int i = 0;
        for (Object obj : list) {
            TimeSpan tSpan = (TimeSpan) obj;
            System.out.println("+++++++++++++++++++++");
            System.out.print("Shift " + i + "    ");
            System.out.print(tSpan.getTimeIn() + "    ");
            System.out.println(tSpan.getTimeOut());
            i++;
        }
        int shiftSelection = Integer.parseInt(ScannerWrapper.getInstance().getInput("Remove which shift?"));
        TimeSpan span = (TimeSpan)list.get(shiftSelection);
        emp.removeShift(day, span, schChoice);
    }

    public void printNurses() {
        int i = 0;
        for (Object obj : hospital.getNurses()) {
            Nurse emp = (Nurse) obj;
            System.out.print("Nurse #" + i);
            emp.printSchedule();
            i++;
        }
    }
    public void addToNurseSchedule(int empChoice,int schChoice,int day,float start , float end) {
        TimeSpan tSpan = new TimeSpan(start, end);
        Nurse emp = hospital.getNurses().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromNurseSchedule() {
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which nurse?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Nurse emp = hospital.getNurses().get(empChoice);
        ArrayList<Object> list = emp.getDaySchedule(day, schChoice);
        int i = 0;
        for (Object obj : list) {
            TimeSpan tSpan = (TimeSpan) obj;
            System.out.println("+++++++++++++++++++++");
            System.out.print("Shift " + i + "    ");
            System.out.print(tSpan.getTimeIn() + "    ");
            System.out.println(tSpan.getTimeOut());
            i++;
        }
        int shiftSelection = Integer.parseInt(ScannerWrapper.getInstance().getInput("Remove which shift?"));
        TimeSpan span = (TimeSpan)list.get(shiftSelection);
        emp.removeShift(day, span, schChoice);
    }
}
