package ir.ac.kntu.department;

import ir.ac.kntu.person.Doctor;
import ir.ac.kntu.person.Nurse;
import ir.ac.kntu.person.Patient;

public class Main extends Department {

    public Main() {

        super("Main");
    }

    @Override
    public boolean addPatient(Patient patient) {
        if (!getPatients().contains(patient)) {
            getPatients().add(patient);
            return true;
        }
        return false;
    }

    @Override
    public Patient getPatient(String patientId) {
        for (Patient patient : getPatients()) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public boolean addNurse(Nurse nurse) {
        if (!getNurses().contains(nurse)) {
            getNurses().add(nurse);
            return true;
        }
        return false;
    }

    @Override
    public Nurse getNurse(String nurseId) {
        for (Nurse nurse: getNurses()) {
            if (nurse.getId().equals(nurseId)) {
                return nurse;
            }
        }
        return null;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        if (!getDoctors().contains(doctor)) {
            getDoctors().add(doctor);
            return true;
        }
        return false;
    }

    @Override
    public Doctor getDoctor(String doctorId) {
        for (Doctor doctor : getDoctors()) {
            if (doctor.getId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean addRoom(Room room) {
        if (!getRooms().contains(room)) {
            getRooms().add(room);
            return true;
        }
        return false;
    }

    @Override
    public Room getRoom(String roomId) {
        for (Room room : getRooms()) {
            if (room.getRoomNum().equals(roomId)) {
                return room;
            }
        }
        return null;
    }
    @Override
    public boolean removeRoom(String roomId){
        Room room = getRoom(roomId);
        if (!getRooms().contains(room)) {
            getRooms().remove(room);
            return true;
        }
        return false;
    }
}
