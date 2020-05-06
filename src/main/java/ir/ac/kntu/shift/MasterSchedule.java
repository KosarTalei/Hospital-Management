package ir.ac.kntu.shift;

import ir.ac.kntu.Doctor;
import ir.ac.kntu.Nurse;

import java.util.ArrayList;

import static ir.ac.kntu.ScannerWrapper.getInput;

public class MasterSchedule extends Schedule{

    @Override
    public void add(int day, TimeSpan shiftTime) {
        ArrayList<Object> tempList = getDayList(day);
        tempList.add(shiftTime);
        System.out.println("Master added shift");
    }

    public void scheduleDoctor(int day, TimeSpan shiftTime, Doctor doctor) throws Exception {
        doctor.addShift(day, shiftTime, 1);
    }
    public void scheduleNurse(int day, TimeSpan shiftTime, Nurse nurse) throws Exception {
        nurse.addShift(day, shiftTime, 1);
    }

    public void print() {
        for (int i = 0; i < 7; i++) {
            System.out.println("_____________________________");
            System.out.println("On day " + i);
            ArrayList<Object> tempList = getDayList(i);
            for (Object span : tempList) {
                TimeSpan tSpan = (TimeSpan) span;
                System.out.println("------------------");
                System.out.println(tSpan.getTimeIn());
                System.out.println(tSpan.getTimeOut());
            }
        }
    }

    public void generateSchedule(ArrayList empList) {
        emptyScheduledSlots();

        for (int i = 0; i < 7; i++) {
            ArrayList<Object> dayList = getDayList(i);
            boolean scheduleChanged = true;
            while(scheduleChanged) {
                scheduleChanged = false;
                for (Object tempTime : dayList) {
                    TimeSpan span = (TimeSpan)tempTime;
                    for (Object temp : empList) {
                        if (span.isFilled()) {
                            break;
                        }
                        Doctor tempDoctor = (Doctor) temp;
                        if (!tempDoctor.doesShiftExist(i, span, 0) ||
                                tempDoctor.doesShiftExist(i, span, 1)) {
                            continue;
                        }
                        try{
                            scheduleDoctor(i, span, tempDoctor);
                            scheduleChanged = true;
                            span.setFilled();
                        }catch(Exception e) {
                            System.out.println("Schedule Error in Generator.");
                        }

                    }
                }
            }
        }
    }


    private void emptyScheduledSlots() {
        for (int i = 0; i < 7; i++) {
            ArrayList<Object> dayList = getDayList(i);
            for (Object temp : dayList) {
                TimeSpan span = (TimeSpan)temp;
                span.setEmpty();
            }
        }
    }

    public void addMasterShift(MasterSchedule mSch) {
        System.out.println("0 Mon 1 Tue 2 Wed 3 Thr 4 Fri 5 Sat 6 Sun");
        String prompt ="Which day?";
        int day = Integer.parseInt(getInput(prompt));
        prompt= "Start time?";
        float start = Integer.parseInt(getInput(prompt));
        prompt ="End time?";
        float end = Integer.parseInt(getInput(prompt));

        TimeSpan tSpan = new TimeSpan(start, end);
        mSch.add(day, tSpan);
    }
}
