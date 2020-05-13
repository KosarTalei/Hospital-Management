package ir.ac.kntu.department;

import ir.ac.kntu.Date;
import ir.ac.kntu.Patient;
import ir.ac.kntu.ScannerWrapper;

import java.util.ArrayList;

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
    private ArrayList<ExtraOrders> orders;
    private double perBed;
    private double dailyBill;

    private double totalBill;

    public void setPerBed() {
        String prompt="Enter hospital cost per bed:";
        this.perBed = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
    }

    public Payment(Booking booking) {
        this.booking = booking;
    }

    public int getDaysStayed() {
        return Date.findDays(booking.getCheckInDateTime(),booking.getCheckOutDateTime());
    }

    public double calculateTotalBill(double moneyPerBed) {
        double orderTotal = 0;
        for(ExtraOrders order: orders) {
            orderTotal += order.getItem().getPrice(moneyPerBed);
        }
        return orderTotal;
    }

    public double pay(Patient patient) {
        Room room= patient.getRoom();
        int bedNum = room.getBedsNum();

        double moneyPerBed = perBed - ((perBed * bedNum * 10) / 100);
        this.dailyBill = calculateTotalBill(moneyPerBed) + moneyPerBed;
        int days = getDaysStayed();
        double sum = dailyBill * days;

        Insurance insurance = Insurance.valueOf(booking.getCustomer().getInsurance());
        this.totalBill = insurance.sale(sum);

        //patient.remove(patient);
        room.getOccupants().remove(patient);

        return totalBill;
    }
}
