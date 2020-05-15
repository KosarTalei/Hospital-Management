package ir.ac.kntu;

import ir.ac.kntu.department.*;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.menu.PatientMenu;

public class PatientMng {

    private Hospital hospital;

    public PatientMng(){

    }
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void hospitalisation(Patient patient){

        String prompt ="Enter Patient National num:";
        String num= ScannerWrapper.getInstance().getInput(prompt);
        patient.setNationalNum(num);

        prompt="Enter Patient Age:";
        int age = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        patient.setAge(age);

        prompt="Enter Patient gender:";
        String gender = ScannerWrapper.getInstance().getInput(prompt);
        patient.setGender(gender);

        prompt= "Enter patient insurance: TAMIN - MOSALAH - DARMANI";
        String insurance = ScannerWrapper.getInstance().getInput(prompt);
        patient.setInsurance(insurance);

        prompt ="Enter Patient illness from down:\\n"
                +" Burn \n"+" Strike \n"+" Accident \n"+" Else \n";
        Disease disease = Disease.valueOf(ScannerWrapper.getInstance().getInput(prompt));
        patient.setDisease(disease);

        prompt="Enter the department:\\n"+
                "EMG \n" + "Burn \n" + "ICU \n" + "Main \n";
        String dpName = ScannerWrapper.getInstance().getInput(prompt);
        Department department = assignDepartment(dpName);
        patient.setDepartment(department);

        asinRoom(patient, department);

        Date entryDate = getDate("entry");
        patient.setJoinDate(entryDate);

        asinDoctor(patient,department);
        asinNurse(patient,department);

    }

    public void asinNurse(Patient patient, Department department){
        if(department.getNurses().size() == 0){
            System.out.println("you should define nurse first !");
            PersonnelMng personnelMng = new PersonnelMng();
            personnelMng.setHospital(hospital);
            Nurse nurse = (Nurse) personnelMng.createPerson();
            nurse.getNursePatientList().add(patient);
            patient.setNurse(nurse);
        }else {
            Nurse nurse = randomNurse(department);
            nurse.getNursePatientList().add(patient);
            patient.setNurse(nurse);
        }
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
            PersonnelMng personnelMng = new PersonnelMng();
            personnelMng.setHospital(hospital);
            Doctor doctor = (Doctor)personnelMng.createPerson();
            doctor.getDoctorPatientList().add(patient);
            patient.setDoctor(doctor);
        }else {
            Doctor doctor = randomDoctor(department);
            doctor.getDoctorPatientList().add(patient);
            patient.setDoctor(doctor);
        }
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

    private void asinRoom(Patient patient, Department department) {
        Room room;
        if (icuRoom(patient, department)){
            return;
        }
        if(department.getRooms().size() == 0) {
            String roomNum = String.valueOf(RandomHelper.nextInt(100-1) + 1);
            System.out.println("your room number: "+roomNum);
            room = new Room(roomNum, RandomHelper.nextInt(6) + 1, department);
        }else {
            room = randomRoom(department);
        }
        room.setRoomClass("Normal");
        addRoom(patient, room);
        randomItem(room);
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

    private void randomItem(Room room){
        if(RandomHelper.nextBoolean()){
            Item item = new Item("002","hasTV");
            room.setItems(item);
            room.setHasTV(true);
        }
        if (RandomHelper.nextBoolean()){
            Item item = new Item("003","hasAirConditioner");
            room.setItems(item);
            room.setHasAirConditioner(true);
        }
        if (RandomHelper.nextBoolean()){
            Item item = new Item("001","hasRefrigerator");
            room.setHasRefrigerator(true);
        }
    }

    private Department assignDepartment(String dpName) {
        switch (dpName) {
            case "EMG":
                return new Emergency();
            case "Burn":
                return new Burn();
            case "ICU":
                return new ICU();
            case "Main":
                return new Main();
            default:
                System.out.println("Wrong choice! ");
        }
        return null;
    }

    public static Date getDate(String dateName) {
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
        prompt ="Enter person ID you want to update:";
        String idNum = ScannerWrapper.getInstance().getInput(prompt);

        if ("patient".equals(position)) {
            Patient patient = new Patient();
            patient = (Patient) patient.getPerson(idNum);
            changeOption(hospital);
        }
        System.out.println("Wrong position!");
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
}
