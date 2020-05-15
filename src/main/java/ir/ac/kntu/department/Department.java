package ir.ac.kntu.department;

import ir.ac.kntu.*;

import java.util.ArrayList;

public abstract class Department{

    private String name;
    private ArrayList<Nurse> nurses;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Room> rooms;

    private ArrayList<Person> persons;

    public Department(String name) {
        this.name = name;
        doctors = new ArrayList<Doctor>();
        nurses = new ArrayList<Nurse>();
        patients = new ArrayList<Patient>();
        rooms = new ArrayList<Room>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    abstract boolean addPerson(Person person);
    abstract Person getPerson(String personId);//remove

    abstract boolean addPatient(Patient patient);
    abstract Patient getPatient(String patientId);

    abstract boolean addNurse(Nurse nurse);
    abstract Nurse getNurse(String nurseId);

    abstract boolean addDoctor(Doctor doctor);
    abstract Doctor getDoctor(String doctorId);

    abstract boolean addRoom(Room room);
    abstract Room getRoom(String roomId);
    abstract boolean removeRoom(String roomId);

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((rooms == null) ? 0 : rooms.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
		}
        if (obj == null){
            return false;
		}
        if (getClass() != obj.getClass()){
            return false;
		}
        Department other = (Department) obj;
        if (name == null) {
            if (other.name != null){
                return false;
			}
        } else if (!name.equals(other.name)){
            return false;
		}
        if (nurses == null) {
            if (other.nurses != null){
                return false;
			}
        } else if (!nurses.equals(other.nurses)){
            return false;
		}
        return true;
    }

    @Override
    public String toString() {
        return "Department: " + name + "\r\n";
    }
}
