package ir.ac.kntu.logic;

import ir.ac.kntu.Date;
import ir.ac.kntu.Patient;
import ir.ac.kntu.Person;
import ir.ac.kntu.department.Department;
import ir.ac.kntu.department.Room;

import static ir.ac.kntu.ScannerWrapper.getInput;

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

class FindPay {

    private double perBed;
    private double moneyPerBed;
    private int days;
    private double sum;
    private double finalSum;

    FindPay() {
        this.perBed = perBed;
    }

    public void setPerBed(double perBed) {
        this.perBed = perBed;
    }

    public double getMoneyPerBed() {
        return moneyPerBed;
    }

    double pay(Patient patient, int bedNum, double perBed, Date leaveDate) {
        this.moneyPerBed = perBed - ((perBed * bedNum * 10) / 100);
        this.days = Date.findDays(patient.getJoinDate(),leaveDate);
        this.sum=this.moneyPerBed * this.days;

        Insurance insurance = Insurance.valueOf(patient.getInsurance());
        this.finalSum = insurance.sale(this.sum);
        return finalSum;
    }
}

public class Pay{

    public void findPay(Patient patient) {

        String prompt ="Enter realise year: ";
        int year = Integer.parseInt(getInput(prompt));
        prompt="Enter realise month: ";
        int month = Integer.parseInt(getInput(prompt));
        prompt="Enter realise day: ";
        int day = Integer.parseInt(getInput(prompt));
        prompt="Enter realise hour: ";
        int hour = Integer.parseInt(getInput(prompt));

        Date leaveDate = new Date(year, month, day,hour);

        Department department = patient.getDepartment();
        Room room= patient.getRoom();
        int bedNum = room.getBedsNum();

        prompt="Enter hospital cost per bed:";
        int perBed = Integer.parseInt(getInput(prompt));

        FindPay pay = new FindPay();
        pay.setPerBed(perBed);
        System.out.println(pay.pay(patient, bedNum, perBed, leaveDate));

        patient.remove(patient);
        room.getOccupants().remove(patient);
    }
}