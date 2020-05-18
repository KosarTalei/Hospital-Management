package ir.ac.kntu.manage;

import ir.ac.kntu.department.Department;
import ir.ac.kntu.helper.RandomHelper;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.person.*;
import ir.ac.kntu.shift.MasterSchedule;
import ir.ac.kntu.shift.TimeSpan;
import java.util.ArrayList;

public class PersonnelMng {

    private Hospital hospital;

    public PersonnelMng(){
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Person createPerson(){
        try {
            return makePerson();
        }catch(Exception e){
            System.out.println("Person wasn't properly entered.");
        }
        return null;
    }

    public Person makePerson(){
        String prompt = "Enter fistName:";
        String firstName = ScannerWrapper.getInstance().getInput(prompt);
        prompt="Enter lastName:";
        String lastName = ScannerWrapper.getInstance().getInput(prompt);
        prompt = "Enter position:";
        String position = ScannerWrapper.getInstance().getInput(prompt);
        prompt = "Enter id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);

        return getPerson(firstName, lastName, position, id);
    }

    private Person getPerson(String firstName, String lastName, String position, String id) {
        Department department;
        switch (position) {
            case "patient":
                department = getDepartment();
                return createPatient(firstName, lastName, id,department);
            case "doctor":
                department = getDepartment();
                return createDoctor(firstName, lastName, id,department);
            case "nurse":
                department = getDepartment();
                return createNurse(firstName, lastName, id,department);
            case "security":
                return createSecurity(firstName, lastName, id);
            case "facility":
                return createFacilities(firstName,lastName,id);
            default:
                System.out.println("Wrong position!");
                break;
        }
        return null;
    }

    private Person createFacilities(String firstName, String lastName, String id) {
        String prompt = "Enter min hour work in week:";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "Enter max hour work in week:";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Facilities facilities = new Facilities(id,firstName,lastName,mx,mn);
        facilities.setHospital(hospital);
        if(facilities.addPerson(facilities)){
            signFacilityShift(facilities);
        }
        return facilities;
    }

    private void signFacilityShift(Facilities facility) {
        int tIn = Integer.parseInt(ScannerWrapper.getInstance().getInput("start time?"));
        int tOut = Integer.parseInt(ScannerWrapper.getInstance().getInput("end time?"));
        TimeSpan time = getTimeSpan(tIn, tOut);
        int day = RandomHelper.nextInt(7);
        facility.addShift(day, time, 0);
        try {
            facility.addShift(0, time, 0);
        } catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
        MasterSchedule master = new MasterSchedule();
        master.add(day, time);
        master.generateSchedule(hospital.getFacilities(),"facility");
        //facility.printSchedule();
    }

    private Person createSecurity(String firstName, String lastName, String id) {
        String prompt = "Enter min hour work in week:";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "Enter max hour work in week:";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Security security = new Security(id,firstName,lastName,mx,mn);
        security.setHospital(hospital);
        if(security.addPerson(security)){
            signSecurityShift(security);
        }
        return security;
    }
    private void signSecurityShift(Security security) {
        int tIn = Integer.parseInt(ScannerWrapper.getInstance().getInput("start time?"));
        int tOut = Integer.parseInt(ScannerWrapper.getInstance().getInput("end time?"));
        TimeSpan time = getTimeSpan(tIn, tOut);
        int day = RandomHelper.nextInt(7);
        security.addShift(day, time, 0);
        try {
            security.addShift(0, time, 0);
        } catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
        MasterSchedule master = new MasterSchedule();
        master.add(day, time);
        master.generateSchedule(hospital.getSecurities(),"security");
        //security.printSchedule();
    }

    private Person createNurse(String firstName, String lastName, String id,Department department) {
        String prompt = "Enter min hour work in week:";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "Enter max hour work in week:";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Nurse nurse = new Nurse(id,firstName,lastName,mx,mn);
        nurse.setDepartment(department);
        if(nurse.addPerson(nurse)){
            signNurseShift(nurse,department);
        }
        return nurse;
    }

    private void signNurseShift(Nurse nurse,Department department) {
        int tIn = Integer.parseInt(ScannerWrapper.getInstance().getInput("start time?"));
        int tOut = Integer.parseInt(ScannerWrapper.getInstance().getInput("end time?"));
        TimeSpan time = getTimeSpan(tIn, tOut);
        int day = RandomHelper.nextInt(7);
        nurse.addShift(day, time, 0);
        try {
            nurse.addShift(0, time, 0);
        } catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
        MasterSchedule master = new MasterSchedule();
        master.add(day, time);
        master.generateSchedule(department.getNurses(),"nurse");
        //nurse.printSchedule();
    }

    private TimeSpan getTimeSpan(int mn, int mx) {
        return new TimeSpan(mn, mx);
    }

    private Person createDoctor(String firstName, String lastName, String id,Department department) {
        String prompt = "Enter min hour work in week:";
        int mn = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt = "Enter max hour work in week:";
        int mx = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        Doctor doctor = new Doctor(id,firstName,lastName,mx,mn);
        doctor.setDepartment(department);
        if(doctor.addPerson(doctor)) {
            signDoctorShift(doctor,department);
        }
        return doctor;
    }

    private void signDoctorShift(Doctor doctor,Department department) {
        int tIn = Integer.parseInt(ScannerWrapper.getInstance().getInput("start time?"));
        int tOut = Integer.parseInt(ScannerWrapper.getInstance().getInput("end time?"));
        TimeSpan time = getTimeSpan(tIn, tOut);
        int day = RandomHelper.nextInt(7);
        doctor.addShift(day, time, 0);
        try {
            doctor.addShift(0, time, 0);
        } catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
        MasterSchedule master = new MasterSchedule();
        master.generateSchedule(department.getDoctors(),"doctor");
        //doctor.printSchedule();
    }

    private Person createPatient(String firstName, String lastName, String id,Department department) {
        Patient patient = new Patient(id,firstName,lastName);
        patient.setDepartment(department);
        patient.addPerson(patient);
        return patient;
    }

    private Department getDepartment() {
        return hospital.getDepartment(hospital);
    }

    public void input(String position){
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which personnel?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));
        float start = Float.parseFloat(ScannerWrapper.getInstance().getInput("Start time?"));
        float end = Float.parseFloat(ScannerWrapper.getInstance().getInput("End time?"));

        getPosition(position, empChoice, schChoice, day, start, end);
    }

    private void getPosition(String position, int empChoice, int schChoice, int day, float start, float end) {
        switch (position) {
            case "doctor":
                addToDocSchedule(empChoice, schChoice, day, start, end);
                break;
            case "nurse":
                addToNurseSchedule(empChoice, schChoice, day, start, end);
                break;
            case "facility":
                addToFacSchedule(empChoice, schChoice, day, start, end);
                break;
            case "security":
                addToSecSchedule(empChoice, schChoice, day, start, end);
                break;
            default:
                System.out.println("Wrong position!");
                break;
        }
    }

    public void addToDocSchedule(int empChoice,int schChoice,int day,float start , float end) {
        Department department = getDepartment();
        TimeSpan tSpan = new TimeSpan(start, end);
        Doctor emp = department.getDoctors().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromDocSchedule() {
        Department department = getDepartment();//////////
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which doctor?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Doctor emp = department.getDoctors().get(empChoice);
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

    public void addToNurseSchedule(int empChoice,int schChoice,int day,float start , float end) {
        Department department = getDepartment();
        TimeSpan tSpan = new TimeSpan(start, end);
        Nurse emp = department.getNurses().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromNurseSchedule() {
        Department department = getDepartment();//////
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which nurse?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Nurse emp = department.getNurses().get(empChoice);
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


    public void asinNurse(Patient patient, Department department){
        if(department.getNurses().size() == 0){
            System.out.println("you should define nurse first !");
            Nurse nurse = (Nurse)createPerson();
            nurse.setDepartment(department);
            nurse.getNursePatientList().add(patient);
            patient.setNurse(nurse);
        }else {
            Nurse nurse = randomNurse(department);
            addNurse(patient, nurse,department);
        }
    }

    private void addNurse(Patient patient, Nurse nurse,Department department) {
        nurse.setDepartment(department);
        patient.setNurse(nurse);
        nurse.addPatient(patient);
    }

    private Nurse randomNurse(Department department){
        int bound = department.getDoctors().size();
        Nurse nurse = department.getNurses().get(RandomHelper.nextInt(bound));
        if(nurse.getNursePatientList().size() < 6){
            return nurse;
        }
        return randomNurse(department);
    }

    public void  asinDoctor(Patient patient, Department department){
        if(department.getDoctors().size() == 0){
            System.out.println("you should define doctor first !");
            Doctor doctor = (Doctor)createPerson();
            addDoctor(patient, doctor,department);
        }else {
            Doctor doctor = randomDoctor(department);
            addDoctor(patient, doctor,department);
        }
    }

    private void addDoctor(Patient patient, Doctor doctor,Department department) {
        doctor.setDepartment(department);
        patient.setDoctor(doctor);
        doctor.addPatient(patient);
    }

    private Doctor randomDoctor(Department department){
        int bound = department.getDoctors().size();
        Doctor doctor = department.getDoctors().get(RandomHelper.nextInt(bound));
        if(!department.getName().equals("ICU")) {
            if (doctor.getDoctorPatientList().size() < 6) {
                return doctor;
            }
        }else {
            if (doctor.getDoctorPatientList().size() < 3) {
                return doctor;
            }
        }
        return randomDoctor(department);
    }


    public void addToFacSchedule(int empChoice,int schChoice,int day,float start , float end) {
        TimeSpan tSpan = new TimeSpan(start, end);
        Facilities emp = hospital.getFacilities().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromSecSchedule() {
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which security?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Security emp = hospital.getSecurities().get(empChoice);
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

    public void addToSecSchedule(int empChoice,int schChoice,int day,float start , float end) {
        TimeSpan tSpan = new TimeSpan(start, end);
        Security emp = hospital.getSecurities().get(empChoice);
        try{
            emp.addShift(day, tSpan, schChoice);
        }catch(Exception e){
            System.out.println("Schedule Error in addToEmpSchedule.");
        }
    }

    public void removeFromFacSchedule() {
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which facility?"));
        System.out.println("0 Availability 1 Shift 2 Request");
        int schChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("What kind of shift?"));
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which day?"));

        Facilities emp = hospital.getFacilities().get(empChoice);
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