package ir.ac.kntu.shift;

import java.util.ArrayList;

public interface ShiftManagement {

    abstract void addShift(int day, TimeSpan shiftTime, int scheduleNumber) ;
    abstract void removeShift(int day, TimeSpan shiftTime, int scheduleNumber);
    abstract void clearSchedule(int scheduleNumber);
    abstract boolean doesShiftExist(int day, TimeSpan shiftTime, int scheduleNumber);
    abstract ArrayList<Object> getDaySchedule(int day, int scheduleNumber);
    abstract void printSchedule();
}
