package ir.ac.kntu.menu;

import ir.ac.kntu.department.*;
import ir.ac.kntu.helper.*;
import ir.ac.kntu.logic.*;
import ir.ac.kntu.manage.*;
import ir.ac.kntu.person.*;
import ir.ac.kntu.shift.TimeSpan;
import ir.ac.kntu.user.*;

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
                Department department =getDepartment(hospital);
                invoicePatient(hospital,getPatient(department));
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Hospital handleNurseOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = getPersonnelMng(hospital);
        Nurse nurse = new Nurse();
        Department department;
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                try {
                    department = getDepartment(hospital);
                    seeNurse(nurse,department);
                }catch (Exception e){
                    System.out.println("Nurse doesnt exist!");
                }
                break;
            case DELETE:
                department = getDepartment(hospital);
                nurse.setDepartment(department);
                Nurse nurse1 = getNurse(department);
                nurse1.removeNurse(nurse1);
                break;
            case SHIFTS:
                department = getDepartment(hospital);
                nurse.setDepartment(department);
                Nurse nurse2 = getNurse(department);
                nurse2.printSchedule();
                break;
            case ADD_SHIFT:
                department = getDepartment(hospital);
                nurse.setDepartment(department);
                nurse.printNurses();
                personnelMng.input("nurse");
                break;
            case REMOVE_SHIFT:
                department = getDepartment(hospital);
                nurse.setDepartment(department);
                nurse.printNurses();
                personnelMng.removeFromNurseSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private void seeNurse(Nurse nurse, Department department) {
        nurse.setDepartment(department);
        Nurse nurse3 = getNurse(department);
        System.out.println(nurse3);
        nurse3.printPatients();
    }

    private Nurse getNurse(Department department) {
        String prompt ="Enter nurse id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Nurse nurse = new Nurse();
        nurse.setDepartment(department);
        nurse = (Nurse) nurse.getPerson(id);
        return nurse;
    }

    public Hospital handleDoctorOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = getPersonnelMng(hospital);
        Doctor doctor = new Doctor();
        Department department;
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                try {
                    department = getDepartment(hospital);
                    seeDoctor(department);
                }catch (Exception e){
                    System.out.println("Doctor doesnt exist!");
                }
                break;
            case DELETE:
                department = getDepartment(hospital);
                Doctor doctor1 = getDoctor(department);
                doctor1.removeDoctor(doctor1);
                break;
            case SHIFTS:
                department = getDepartment(hospital);
                Doctor doctor2 = getDoctor(department);
                doctor2.setDepartment(department);
                doctor2.printSchedule();
                break;
            case ADD_SHIFT:
                department = getDepartment(hospital);
                doctor.setDepartment(department);
                doctor.printDoctors();
                personnelMng.input("doctor");
                break;
            case REMOVE_SHIFT:
                department = getDepartment(hospital);
                doctor.setDepartment(department);
                doctor.printDoctors();
                personnelMng.removeFromDocSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private void seeDoctor(Department department) {
        Doctor doc = getDoctor(department);
        System.out.println(doc);
        doc.printPatients();
    }

    private Doctor getDoctor(Department department) {
        String prompt ="Enter doctor id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Doctor doctor = new Doctor();
        doctor.setDepartment(department);
        doctor = (Doctor)doctor.getPerson(id);
        return doctor;
    }

    public Hospital handleFacilityOption(Doctor.Option option,Hospital hospital){
        PersonnelMng personnelMng = getPersonnelMng(hospital);
        Facilities facilities = new Facilities();
        facilities.setHospital(hospital);
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
                facilities.printFacility();
                personnelMng.input("facility");
                break;
            case REMOVE_SHIFT:
                facilities.printFacility();
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
        PatientMng patientMng = getPatientMng(hospital);
        Department department;
        switch (option){
            case NEW:
                PersonnelMng personnelMng = getPersonnelMng(hospital);
                personnelMng.createPerson();
                break;
            case SEE:
                department = getDepartment(hospital);
                Patient patient = getPatient(department);
                if (patient != null) {
                    System.out.println(patient.toString());
                }else {
                    System.out.println("This patient doesnt exist!");
                }
                break;
            case HOSPITALISATION:
                department = getDepartment(hospital);
                Patient patient1 = getPatient(department);
                patientMng.hospitalisation(patient1,department);
                break;
            case CHANG:
                patientMng.changeOption(hospital);
                break;
            case INVOICE:
                department = getDepartment(hospital);
                invoicePatient(hospital,getPatient(department));
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private void invoicePatient(Hospital hospital,Patient patient) {
        Payment payment = new Payment(patient);
        payment.setPerBed();
        payment.pay(patient,hospital);
        System.out.println(payment);
    }

    private PatientMng getPatientMng(Hospital hospital) {
        PatientMng patientMng = new PatientMng();
        patientMng.setHospital(hospital);
        return patientMng;
    }

    public Patient getPatient(Department department) {
        String prompt ="Enter patient id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);
        Patient patient = new Patient();
        patient.setDepartment(department);
        patient = (Patient) patient.getPerson(id);
        return patient;
    }

    public Hospital handleSecurityUserOption(SecurityUser.Option option, Hospital hospital) {
        Department department = getDepartment(hospital);
        switch (option){
            case PATIENT:
                System.out.println(getPatient(department));
                break;
            case DOCTOR:
                System.out.println(getDoctor(department));
                break;
            case NURSE:
                System.out.println(getNurse(department));
                break;
            case PERSONNEL:
                System.out.println(getFacility(hospital));
                break;
            case ROOM:
                department = getDepartment(hospital);
                Room room = department.checkRoom(department);
                System.out.println(room);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public void failItem(Room room){
        Item printItem = new Item();
        printItem.printItem(room);
        int empChoice = Integer.parseInt(ScannerWrapper.getInstance().getInput(" Which item?"));
        Item item = room.getItems().get(empChoice);
        item.setHealthy(false);
    }

    public void failureReport(Hospital hospital,Department department){
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput("now day?"));
        Room room = department.checkRoom(department);
        failItem(room);
        float start = Float.parseFloat(ScannerWrapper.getInstance().getInput("Enter report time:"));
        float end = start + 1;
        TimeSpan tSpan = new TimeSpan(start, end);
        for(Facilities facility : hospital.getFacilities()) {
            if(facility.sayFacility(facility,tSpan,room,day)){
                facility.checkFacility(facility);
                return;
            }
        }
        System.out.println("No facilities are currently available at the hospital.");
    }

    public Department getDepartment(Hospital hospital){
        return hospital.getDepartment(hospital);
    }

    public Hospital handleSecurityOption(Doctor.Option option,Hospital hospital) {
        PersonnelMng personnelMng = getPersonnelMng(hospital);
        Security security = new Security();
        security.setHospital(hospital);
        switch (option){
            case ADD:
                personnelMng.createPerson();
                break;
            case SEE:
                seeSecurity(hospital);
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
                security.printSecurities();
                personnelMng.input("security");
                break;
            case REMOVE_SHIFT:
                security.printSecurities();
                personnelMng.removeFromSecSchedule();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    private PersonnelMng getPersonnelMng(Hospital hospital) {
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        return personnelMng;
    }

    private void seeSecurity(Hospital hospital) {
        try {
            System.out.println(getSecurity(hospital));
        }catch (Exception e){
            System.out.println("Security doesnt exist!");
        }
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
                    hospitalProgram.signSecurityToUser(hospital);
                }else {
                    System.out.println("this security doesnt exist!");
                }
                break;
            case SIGN_PATIENT:
                hospitalProgram.signPatient(getDepartment(hospital));
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
            case ITEM_MENU:
                hospitalProgram.itemOption(hospital);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

    public Hospital handleItemOption(Item.Option option, Hospital hospital) {
        Department department = getDepartment(hospital);
        switch (option){
            case FAIL:
                failureReport(hospital,department);
                break;
            case SEE_ROOM:
                Room room = department.checkRoom(department);
                room.printItems();
                break;
            case SEE_CHECKUP:
                Item itemCheck = department.getItem(department);
                System.out.println("Last checkup: "+itemCheck.getCheckUp());
                break;
            case HEALTH:
                Item itemHealth = department.getItem(department);
                System.out.println("Item is healthy? "+itemHealth.getHealthy());
                break;
            case ADD:
                department.addItem(department);
                break;
            case REMOVE:
                department.removeItem(department);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        return hospital;
    }

}