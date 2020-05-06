package ir.ac.kntu.shift;

import ir.ac.kntu.Doctor;

import java.util.ArrayList;

public class Test {
    public static void main (String[] args) {

        ArrayList<Object> doctorsList = new ArrayList<Object>();

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
        doc2.printSchedule();

    }
}
