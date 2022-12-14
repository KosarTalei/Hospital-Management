package ir.ac.kntu.department;

import ir.ac.kntu.helper.Date;
import ir.ac.kntu.person.Patient;
import ir.ac.kntu.helper.ScannerWrapper;

public class Booking {

    private Patient customer;
    private Date checkInDateTime;
    private Date checkOutDateTime;

    public Booking(Patient patient){
        this.customer = patient;
        setCheckOutDateTime(patient);
        setCheckInDateTime(patient);
    }
    public Patient getCustomer() {
        return customer;
    }

    public void setCustomer(Patient customer) {
        this.customer = customer;
    }

    public void setCheckInDateTime(Patient patient) {
        this.checkInDateTime = patient.getJoinDate();
    }

    public void setCheckOutDateTime(Patient patient) {
        String prompt ="Enter realise year: ";
        int year = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter realise month: ";
        int month = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter realise day: ";
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt="Enter realise hour: ";
        int hour = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));

        Date leaveDate = new Date(year, month, day,hour);
        this.checkOutDateTime = leaveDate;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public Date getCheckOutDateTime() {
        return checkOutDateTime;
    }
}