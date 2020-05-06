package ir.ac.kntu;

public class Patient extends Person {

    Patient(String id, String firstName, String lastName) {

        super(id, firstName, lastName, "patient");
    }

    @Override
    boolean addPerson(Person person) {
        if (!getPatients().contains(person)) {
            getPatients().add((Patient)person);//?
            return true;
        }
        return false;
    }

    @Override
    Person getPerson(String personId) {
        for (Patient patient : getPatients()) {
            if (patient.getId().equals(personId)) {
                return patient;
            }
        }
        return null;
    }
}
