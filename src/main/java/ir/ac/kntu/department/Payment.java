package ir.ac.kntu.department;

import ir.ac.kntu.helper.Date;
import ir.ac.kntu.person.Patient;
import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.logic.Hospital;

public class Payment {

    enum Insurance{
        TAMIN(10),MOSALAH(50),DARMANI(25);

        private int sale;
        Insurance(int sale){
            this.sale =sale;
        }

        public int getSale() {
            return sale;
        }

        double sale(double price){
            price = price - ((price * this.getSale()) / 100);
            return price;
        }
    }

    private Booking booking;
    private double perBed;
    private double dailyBill;

    private double totalBill;

    public void setPerBed() {
        String prompt="Enter hospital cost per bed:";
        this.perBed = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
    }

    public Payment(Patient patient) {
        this.booking = new Booking(patient);
    }

    public int getDaysStayed() {
        return Date.findDays(booking.getCheckInDateTime(),booking.getCheckOutDateTime());
    }

    public double calculateTotalBill(double moneyPerBed,Patient patient) {
        double orderTotal = 0;
        for(Item item : patient.getRoom().getItems()) {
            orderTotal += item.getPrice(moneyPerBed);
        }
        return orderTotal;
    }

    public double pay(Patient patient, Hospital hospital) {
        Room room= patient.getRoom();
        int bedNum = room.getBedsNum();

        double moneyPerBed = perBed - ((perBed * bedNum * 10) / 100);
        this.dailyBill = calculateTotalBill(moneyPerBed,patient) + moneyPerBed;
        int days = getDaysStayed();
        double sum = dailyBill * days;

        Insurance insurance = Insurance.valueOf(booking.getCustomer().getInsurance());
        this.totalBill = insurance.sale(sum);

        hospital.getPatients().remove(patient);
        room.getOccupants().remove(patient);

        return totalBill;
    }

    @Override
    public String toString() {
        return
                "patient=" + booking.getCustomer().toString() +
                "Payment{" +
                ", Days stayed=" + getDaysStayed() +
                ", Bill for one bed=" + perBed +
                ", dailyBill=" + dailyBill +
                ", totalBill=" + totalBill +
                '}';
    }
}
