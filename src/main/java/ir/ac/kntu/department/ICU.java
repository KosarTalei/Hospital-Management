package ir.ac.kntu.department;

import ir.ac.kntu.Doctor;
import ir.ac.kntu.Nurse;
import ir.ac.kntu.Patient;
import ir.ac.kntu.Person;

public class ICU extends Department {
    public ICU() {
        super("ICU");
    }
    @Override
    boolean addPerson(Person person) {
        if (!getPersons().contains(person)) {
            getPersons().add(person);
            return true;
        }
        return false;
    }

    @Override
    Person getPerson(String personId) {
        for (Person person : getPersons()) {
            if (person.getId().equals(personId)) {
                return person;
            }
        }
        return null;
    }

    @Override
    boolean addPatient(Patient patient) {
        if (!getPatients().contains(patient)) {
            getPatients().add(patient);
            return true;
        }
        return false;
    }

    @Override
    Patient getPatient(String patientId) {
        for (Patient patient : getPatients()) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    boolean addNurse(Nurse nurse) {
        if (!getNurses().contains(nurse)) {
            getNurses().add(nurse);
            return true;
        }
        return false;
    }

    @Override
    Nurse getNurse(String nurseId) {
        for (Nurse nurse: getNurses()) {
            if (nurse.getId().equals(nurseId)) {
                return nurse;
            }
        }
        return null;
    }

    @Override
    boolean addDoctor(Doctor doctor) {
        if (!getDoctors().contains(doctor)) {
            getDoctors().add(doctor);
            return true;
        }
        return false;
    }

    @Override
    Doctor getDoctor(String doctorId) {
        for (Doctor doctor : getDoctors()) {
            if (doctor.getId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    boolean addRoom(Room room) {
        if (!getRooms().contains(room)) {
            getRooms().add(room);
            return true;
        }
        return false;
    }

    @Override
    Room getRoom(String roomId) {
        for (Room room : getRooms()) {
            if (room.getRoomNum().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    @Override
    boolean removeRoom(String roomId) {
        return false;
    }
}