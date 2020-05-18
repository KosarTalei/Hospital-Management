package ir.ac.kntu.manage;

import ir.ac.kntu.department.*;
import ir.ac.kntu.helper.*;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.menu.PatientMenu;
import ir.ac.kntu.person.Patient;
import ir.ac.kntu.person.Patient.Disease;

public class PatientMng {

    private Hospital hospital;

    public PatientMng(){

    }
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void hospitalisation(Patient patient){
        String nationalNum = getString("Enter Patient National num:");
        patient.setNationalNum(nationalNum);

        int age = Integer.parseInt(getString("Enter Patient Age:"));
        patient.setAge(age);

        String gender = getString("Enter Patient gender:");
        patient.setGender(gender);

        scanIllness(patient);

        String dpName = getString("Enter the department:\\n" + "EMG \n" + "Burn \n" + "ICU \n" + "Main \n");

        Department department =  hospital.getDepartment(dpName,hospital);
        patient.setDepartment(department);

        scanDate(patient);
        asinRoom(patient, department);

        PersonnelMng personnelMng = newPersonnelMng();

        if(!dpName.equals("EMG")) {
            personnelMng.asinDoctor(patient, department);
            personnelMng.asinNurse(patient, department);
        }else {
            System.out.println("you are in EMG department.");
            System.out.println("you dont have specific doctor and nurses!");
        }
    }

    private PersonnelMng newPersonnelMng() {
        PersonnelMng personnelMng = new PersonnelMng();
        personnelMng.setHospital(hospital);
        return personnelMng;
    }

    private void scanDate(Patient patient) {
        Date entryDate = getDate("entry");
        patient.setJoinDate(entryDate);
    }

    private void scanIllness(Patient patient) {
        String prompt;
        String insurance = getString("Enter patient insurance: TAMIN - MOSALAH - DARMANI");
        patient.setInsurance(insurance);
        prompt ="Enter Patient illness from down:\\n"
                +" Burn \n"+" Strike \n"+" Accident \n"+" Else \n";
        Disease disease = Disease.valueOf(ScannerWrapper.getInstance().getInput(prompt));
        patient.setDisease(disease);
    }

    private String getString(String s) {
        String prompt;
        prompt = s;
        return ScannerWrapper.getInstance().getInput(prompt);
    }

    private void asinRoom(Patient patient, Department department) {
        Room room;
        if (icuRoom(patient, department)){
            return;
        }
        if(department.getRooms().size() == 0) {
            String roomNum = String.valueOf(RandomHelper.nextInt(100-1) + 1);
            room = new Room(roomNum, RandomHelper.nextInt(6) + 1, department);
        }else {
            room = randomRoom(department);
        }
        room.setRoomClass("Normal");
        randomItem(room,patient);
        addRoom(patient, room);
        if(department.addRoom(room)){
            System.out.println("patient room number is:"+room.getRoomNum());
        }else {
            System.out.println("this room already exist!");
        }
    }

    private void addRoom(Patient patient, Room room) {
        room.addOccupant(patient);
        patient.setRoom(room);
    }

    private boolean icuRoom(Patient patient, Department department) {
        if (department.getName().equals("ICU")){
            String roomNum = String.valueOf(RandomHelper.nextInt(100-1) + 1);
            System.out.println("your room number: "+roomNum);
            Room room = new Room(roomNum,1,department);
            room.setRoomClass("VIP");
            addRoom(patient, room);
            return true;
        }
        return false;
    }

    public Room randomRoom(Department department){
        int bound = department.getRooms().size();
        Room room = department.getRooms().get(RandomHelper.nextInt(bound));
        if(room.getOccupants().size() < room.getBedsNum()){
            return room;
        }
        return randomRoom(department);
    }

    private void randomItem(Room room,Patient patient){
        String id = String.valueOf(RandomHelper.nextInt());
        if(RandomHelper.nextBoolean()){
            id = "002".concat(id);
            newItem(room, id,patient);
            room.setTv(true);
        }
        if (RandomHelper.nextBoolean()){
            id = "003".concat(id);
            newItem(room, id,patient);
            room.setAirConditioner(true);
        }
        if (RandomHelper.nextBoolean()){
            id = "001".concat(id);
            newItem(room, id,patient);
            room.setRefrigerator(true);
        }
    }

    private void newItem(Room room, String id,Patient patient) {
        Item item = new Item(id);
        item.setHealthy(true);
        item.setCheckUp(patient.getJoinDate());
        room.setItems(item);
    }

    public Date getDate(String dateName) {
        String prompt="Enter the " + dateName + " year: ";
        int year = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter the " + dateName + " month: ";
        int month = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter the " + dateName + " day: ";
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter the " + dateName + " hour: ";
        int hour = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        return new Date(year, month, day,hour);
    }


    public void changeOption(Hospital hospital) {
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
        if ("patient".equals(position)) {
            updatePatientDetails(option,hospital);
        }else {
            System.out.println("Wrong position!");
        }
        return hospital;
    }

    public Hospital updatePatientDetails(Patient.ChangeOption option, Hospital hospital) {
        String prompt = "Enter patient id:";
        String id = ScannerWrapper.getInstance().getInput(prompt);

        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient = (Patient)patient.getPerson(id);
        switch (option) {
            case FIRST_NAME:case LAST_NAME:
                String firstName = getString("Enter new patient's firstName:");
                patient.setFirstName(firstName);
                String lastName = getString("Enter new patient's lastName:");
                patient.setLastName(lastName);
                System.out.println("Patient updated !");
                break;
            case INSURANCE:
                try {
                    String insurance = getString("Enter new patient insurance:");
                    patient.setInsurance(insurance);
                    System.out.println("Patient updated !");
                }catch (Exception e){
                    System.out.println("Entered wrong insurance!");
                }
                break;
            case AGE:
                int age = Integer.parseInt(getString("Enter Patient Age:"));
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
}
