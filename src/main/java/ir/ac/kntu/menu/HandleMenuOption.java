package ir.ac.kntu.menu;

import ir.ac.kntu.department.*;
import ir.ac.kntu.helper.Date;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.logic.HospitalProgram;
import ir.ac.kntu.manage.PatientMng;
import ir.ac.kntu.manage.PersonnelMng;
import ir.ac.kntu.person.*;
import ir.ac.kntu.shift.TimeSpan;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.PatientUser;
import ir.ac.kntu.user.SecurityUser;
import ir.ac.kntu.user.User;

import java.util.ArrayList;

public class HandleMenuOption {
    private HospitalProgram hospitalProgram;
    public HandleMenuOption(){
        this.hospitalProgram= new  HospitalProgram();
    }

    public Hospital handleTheOption(Hospital hospital, HospitalProgram.Option option){
        switch (option) {
            case DEFINE_HOSPITAL:
                hospital = hospitalProgram.defineHospital();
                break;
            case SIGN_ADMIN:
                hospitalProgram.signAdmin(hospital);
                break;
            case LOGIN_ADMIN:
                hospitalProgram.loginAdmin(hospital);
                break;
            case LOGIN_SECURITY:
                hospitalProgram.loginSecurity(hospital);
                break;
            case LOGIN_PATIENT:
                hospitalProgram.loginPatient(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Hospital handlePatientUserOption(PatientUser.Option option, Hospital hospital) {
        Patient patient = hospital.getCurrentPatient();
        switch (option){
            case DATA:
                System.out.println(patient);
                break;
            case SHIFT:
                Doctor doctor = patient.getDoctor();
                doctor.printSchedule();
                break;
            case INVOICE:
                Payment payment = new Payment(patient);
                payment.setPerBed();
                payment.pay(patient,hospital);
                System.out.println(payment);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Hospital handleNurseOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                Nurse nurse = getNurse(hospital);
                System.out.println(nurse);
                break;
            case DELETE:
                Nurse nurse1 = getNurse(hospital);
                hospital.getNurses().remove(nurse1);
                break;
            case SHIFTS:
                Nurse nurse2 = getNurse(hospital);
                nurse2.printSchedule();
                break;
            case ADD_SHIFT:
                personnelMng.printNurses();
                personnelMng.input("nurse");
                break;
            case REMOVE_SHIFT:
                personnelMng.printNurses();
                personnelMng.removeFromNurseSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }
    private Nurse getNurse(Hospital hospital) {
        String prompt ="Enter nurse id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Nurse nurse = new Nurse();
        nurse.setHospital(hospital);
        nurse = (Nurse) nurse.getPerson(id);
        return nurse;
    }

    public Hospital handleDoctorOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                Doctor doctor = getDoctor(hospital);
                try {
                    System.out.println(doctor);
                }catch (Exception e){
                    System.out.println("Doctor doesnt exist!");
                }
                break;
            case DELETE:
                Doctor doctor1 = getDoctor(hospital);
                hospital.getDoctors().remove(doctor1);
                break;
            case SHIFTS:
                Doctor doctor2 = getDoctor(hospital);
                doctor2.setHospital(hospital);
                doctor2.printSchedule();
                break;
            case ADD_SHIFT:
                personnelMng.printDoctors();
                personnelMng.input("doctor");
                break;
            case REMOVE_SHIFT:
                personnelMng.printDoctors();
                personnelMng.removeFromDocSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private Doctor getDoctor(Hospital hospital) {
        String prompt ="Enter doctor id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Doctor doctor = new Doctor();
        doctor.setHospital(hospital);
        doctor = (Doctor)doctor.getPerson(id);
        return doctor;
    }

    public Hospital handleFacilityOption(Doctor.Option option,Hospital hospital){
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                Facilities facility = getFacility(hospital);
                try {
                    System.out.println(facility);
                }catch (Exception e){
                    System.out.println("facility doesnt exist!");
                }
                break;
            case DELETE:
                Facilities facility1 = getFacility(hospital);
                hospital.getFacilities().remove(facility1);
                break;
            case SHIFTS:
                Facilities facility2 = getFacility(hospital);
                facility2.setHospital(hospital);
                facility2.printSchedule();
                break;
            case ADD_SHIFT:
                personnelMng.printDoctors();
                personnelMng.input("facility");
                break;
            case REMOVE_SHIFT:
                personnelMng.printFacility();
                personnelMng.removeFromFacSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private Facilities getFacility(Hospital hospital) {
        String prompt ="Enter Facility id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Facilities facilities = new Facilities();
        facilities.setHospital(hospital);
        facilities = (Facilities)facilities.getPerson(id);
        return facilities;
    }

    public Hospital handlePatientOption(Patient.Option option,Hospital hospital) {
        PatientMng patientMng = new PatientMng();
        patientMng.setHospital(hospital);
        switch (option){
            case NEW:
                PersonnelMng personnelMng = new PersonnelMng();
                personnelMng.setHospital(hospital);
                personnelMng.createPerson();
                break;
            case SEE:
                Patient patient = getPatient(hospital);
                if (patient != null) {
                    System.out.println(patient.toString());
                }else {
                    System.out.println("This patient doesnt exist!");
                }
                break;
            case HOSPITALISATION:
                Patient patient1 = getPatient(hospital);
                patientMng.hospitalisation(patient1);
                break;
            case CHANG:
                patientMng.changeOption(hospital);
                break;
            case INVOICE:
                Patient patient2 = getPatient(hospital);
                Payment payment = new Payment(patient2);
                payment.setPerBed();
                payment.pay(patient2,hospital);
                System.out.println(payment);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Patient getPatient(Hospital hospital) {
        String prompt ="Enter patient id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient = (Patient) patient.getPerson(id);
        return patient;
    }

    public Hospital handleSecurityUserOption(SecurityUser.Option option, Hospital hospital) {
        switch (option){
            case PATIENT:
                Patient patient = getPatient(hospital);
                System.out.println(patient);
                break;
            case DOCTOR:
                System.out.println(getDoctor(hospital));
                break;
            case NURSE:
                System.out.println(getNurse(hospital));
                //case PERSONNEL:
                break;
            case ROOM:
                Room room = checkRoom();
                System.out.println(room);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public void failItem(Room room){
        printItem(room);
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput("Which item?"));
        Item item = room.getItems().get(empChoice);
        item.setHealthy(false);
    }

    private void printItem(Room room) {
        int i = 0;
        System.out.println(":"+room);
        System.out.println("::"+room.getItems());
        for (Item item : room.getItems()) {
            System.out.print("Item #" + i+" "+item.getItemName());
            i++;
        }
    }

    public void failureReport(Hospital hospital){

        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("now day?"));
        Room room = checkRoom();
        System.out.println("_"+room.getRoomNum());
        failItem(room);
        float start = Float.parseFloat(ScannerWrapper.getInstance().getInput("Enter report time:"));
        float end = start;
        TimeSpan tSpan = new TimeSpan(start, end);
        for(Facilities facility : hospital.getFacilities()) {
            if(sayFacility(facility,tSpan,room,day)){
                checkFacility(facility);
                return;
            }
        }
        System.out.println("No facilities are currently available at the hospital.");
    }
    private void checkFacility(Facilities facility){
        Room room = facility.getRoom();
        for(Item item : room.getItems()){
            if(!item.getHealthy()){
                item.setHealthy(true);
                facility.setCheck(true);
                PatientMng patientMng = new PatientMng();
                Date date = patientMng.getDate("check up");
                item.setCheckUp(date);
                System.out.println("facility"+facility+"checked"+item.getItemName()+"of this room");
            }
        }
    }

    private boolean sayFacility(Facilities facility,TimeSpan time,Room room,int day) {
        ArrayList<Object> list = facility.getDaySchedule(day, 1);
        int i = 0;
        for (Object obj : list) {
            TimeSpan tSpan = (TimeSpan) obj;
            if( tSpan.getTimeIn() <= time.getTimeIn() && time.getTimeIn()<= time.getTimeOut() ){
                facility.setCheck(false);
                facility.setRoom(room);
                System.out.println(facility+"The facility was selected");
                return true;
            }
            System.out.print(tSpan.getTimeIn());
            System.out.println(tSpan.getTimeOut());
            i++;
        }
        return false;
    }

    public Room checkRoom(){
        String prompt="Enter the room's department:";
        String dp = ScannerWrapper.getInstance().getInput(prompt);
        prompt ="Enter the room number:";
        String number = ScannerWrapper.getInstance().getInput(prompt);
        Department department = getDepartment(dp);
        System.out.println("+"+department.getName());
        //assert department != null;
        Room room = getRoom(number, department);/////////////////////////////////////////////
        System.out.println("++"+room);
        System.out.println("+++"+room.getRoomNum());
        return room;
    }

    private Room getRoom(String number, Department department) {
        for (Room room : department.getRooms()){
            if (room.getRoomNum().equals(number)){
                return room;
            }
        }
        return null;
    }

    private Department getDepartment(String dp){
        switch (dp) {
            case "Main":
                return new Main();
            case "ICU":
                return new ICU();
            case "Burn":
                return new Burn();
            case "Emergency":
                return new Emergency();
            default:
                System.out.println("Wrong department!");
                break;				
        }
        return null;
    }

    public Hospital handleSecurityOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                Security security = getSecurity(hospital);
                try {
                    System.out.println(security);
                }catch (Exception e){
                    System.out.println("Security doesnt exist!");
                }
                break;
            case DELETE:
                Security security1 = getSecurity(hospital);
                hospital.getSecurities().remove(security1);
                break;
            case SHIFTS:
                Security security2 = getSecurity(hospital);
                security2.setHospital(hospital);
                security2.printSchedule();
                break;
            case ADD_SHIFT:
                personnelMng.printSecurities();
                personnelMng.input("security");
                break;
            case REMOVE_SHIFT:
                personnelMng.printSecurities();
                personnelMng.removeFromSecSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Security getSecurity(Hospital hospital) {
        String prompt ="Enter security id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Security security = new Security();
        security.setHospital(hospital);
        security = (Security) security.getPerson(id);
        return security;
    }

    public Hospital handleAdminOption(Admin.Option option, Hospital hospital) {
        switch (option){
            case SIGN_ADMIN:
                hospitalProgram.signAdmin(hospital);
                break;
            case SIGN_SECURITY:
                Security security = getSecurity(hospital);
                if(security != null) {
                    hospitalProgram.signSecurity(hospital);
                }else {
                    System.out.println("this security doesnt exist!");
                }
                break;
            case SIGN_PATIENT:
                Patient patient = getPatient(hospital);
                if(patient!=null) {
                    User user = new User(patient);
                    PatientUser patientUser = new PatientUser(patient);
                    patientUser.setUser(user);
                }
                break;
            case PATIENT_MENU:
                hospitalProgram.patientOption(hospital);
                break;
            case DOCTOR_MENU:
                hospitalProgram.doctorOption(hospital);
                break;
            case NURSE_MENU:
                hospitalProgram.nurseOption(hospital);
                break;
            case SECURITY_MENU:
                hospitalProgram.securityOption(hospital);
                break;
            case FACILITY_MENU:
                hospitalProgram.facilityOption(hospital);
                break;
            case REPORT:
                failureReport(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

}