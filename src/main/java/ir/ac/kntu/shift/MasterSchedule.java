package ir.ac.kntu.shift;

import ir.ac.kntu.helper.ScannerWrapper;
import ir.ac.kntu.person.*;

import java.util.ArrayList;

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
    public void scheduleSecurity(int day, TimeSpan shiftTime, Security security) throws Exception {
        security.addShift(day, shiftTime, 1);
    }
    public void scheduleFacility(int day, TimeSpan shiftTime, Facilities facilities) throws Exception {
        facilities.addShift(day, shiftTime, 1);
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

    public void generateSchedule(ArrayList empList,String position) {
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
                        Person tempPerson = null;
                        tempPerson = getPerson(position, i, span, (Person) temp, tempPerson);
                        scheduleChanged = isScheduleChanged(i, scheduleChanged, span, tempPerson,position);
                    }
                }
            }
        }
    }

    private Person getPerson(String position, int i, TimeSpan span, Person temp, Person tempPerson) {
        switch (position) {
            case "doctor":
                tempPerson = getDoctor(i, span, (Doctor) temp);
                break;
            case "nurse":
                tempPerson = getNurse(i, span, (Nurse) temp);
                break;
            case "security":
                tempPerson = getSecurity(i, span, (Security) temp);
                break;
            case "facility":
                tempPerson = getFacility(i, span, (Facilities) temp);
                break;
            default:
                System.out.println("Wrong position!");
                break;				
        }
        return tempPerson;
    }

    private boolean isScheduleChanged(int i, boolean scheduleChanged,
	    TimeSpan span, Person tempPerson,String position) {
        if (tempPerson == null){
			return scheduleChanged;
		}
        try{
            cast(i, span, tempPerson, position);
            scheduleChanged = true;
            span.setFilled();
        }catch(Exception e) {
            System.out.println("Schedule Error in Generator.");
        }
        return scheduleChanged;
    }

    private void cast(int i, TimeSpan span, Person tempPerson, String position) throws Exception {
        switch (position) {
            case "doctor":
                scheduleDoctor(i, span, (Doctor) tempPerson);
                break;
            case "nurse":
                scheduleNurse(i, span, (Nurse) tempPerson);
                break;
            case "security":
                scheduleSecurity(i, span, (Security) tempPerson);
                break;
            case "facility":
                scheduleFacility(i, span, (Facilities) tempPerson);
                break;
            default:
                System.out.println("Wrong position!");
                break;			
        }
    }

    private Facilities getFacility(int i, TimeSpan span, Facilities temp) {
        if (!temp.doesShiftExist(i, span, 0) ||
                temp.doesShiftExist(i, span, 1)) {
            return null;
        }
        return temp;
    }

    private Security getSecurity(int i, TimeSpan span, Security temp) {
        if (!temp.doesShiftExist(i, span, 0) ||
                temp.doesShiftExist(i, span, 1)) {
            return null;
        }
        return temp;
    }
    private Nurse getNurse(int i, TimeSpan span, Nurse temp) {
        if (!temp.doesShiftExist(i, span, 0) ||
                temp.doesShiftExist(i, span, 1)) {
            return null;
        }
        return temp;
    }

    private Doctor getDoctor(int i, TimeSpan span, Doctor temp) {
        if (!temp.doesShiftExist(i, span, 0) ||
                temp.doesShiftExist(i, span, 1)) {
            return null;
        }
        return temp;
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
        int day = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt= "Start time?";
        float start = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));
        prompt ="End time?";
        float end = Integer.parseInt(ScannerWrapper.getInstance().getInput(prompt));

        TimeSpan tSpan = new TimeSpan(start, end);
        mSch.add(day, tSpan);
    }
}
