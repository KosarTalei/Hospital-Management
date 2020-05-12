package ir.ac.kntu.shift;

import ir.ac.kntu.Date;
import ir.ac.kntu.Doctor;
import ir.ac.kntu.Patient;
import ir.ac.kntu.department.*;
import ir.ac.kntu.logic.Hospital;
import ir.ac.kntu.logic.HospitalProgram;
import ir.ac.kntu.logic.Pay;
import ir.ac.kntu.menu.Admin;

import java.util.ArrayList;

public class Test {
    public static void main (String[] args) {
        /*HospitalProgram hospitalProgram = new HospitalProgram();
        Hospital hospital = hospitalProgram.defineHospital();
        Admin admin = new Admin();
        Admin admin1 = admin.signAdmin();
        admin1.setHospital(hospital);
        admin1.addUser(admin);*/

        Patient patient = new Patient("123","KOSAR","TALEI");
        Department main = new Main();
        ((Main) main).addPatient(patient);
        Room room = new Room("1234",3,main);
        ((Main) main).addRoom(room);
        Date date = new Date(1399,2,2,13);
        patient.setJoinDate(date);

        patient.setInsurance("TAMIN");
        patient.setRoom(room);
        patient.setDepartment(main);

        Booking booking = new Booking();
        Payment payment = new Payment(booking);
        System.out.println(payment.pay(patient));
        //hospitalProgram.main(args);
        /*Pay pay = new Pay();
        pay.findPay(patient);*/
        /*ArrayList<Object> doctorsList = new ArrayList<Object>();
        Doctor doc1 = new Doctor("123", "Mohammad", "Rami", 30, 40);
        Doctor doc2 = new Doctor("456", "Karim", "Rami", 30, 40);
        doctorsList.add(doc1);
        doctorsList.add(doc2);
        TimeSpan time = new TimeSpan(8.00, 20.60);
        TimeSpan time1 = new TimeSpan(9.00, 11.00);
        TimeSpan time2 = new TimeSpan(11.00, 13.00);
        TimeSpan time3 = new TimeSpan(15.00, 19.00);
        doc1.addShift(0, time, 0);
        doc2.addShift(0, time, 0);

        try {
            doc1.addShift(0, time, 0);
            doc2.addShift(0, time, 0);
        } catch (Exception e) {
            System.out.println("EXCEPTION1!");
        }

        MasterSchedule master = new MasterSchedule();
        master.add(0, time2);
        master.add(0, time3);
        master.add(0, time1);
        master.generateSchedule(doctorsList);
        doc1.printSchedule();
        doc2.printSchedule();*/
    }
}
