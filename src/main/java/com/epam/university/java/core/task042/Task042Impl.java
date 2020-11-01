package com.epam.university.java.core.task042;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Task042Impl implements Task042 {
    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }

        List<MyTime> times = getBusyTimes(schedule);
        LocalTime needTime = MyTime.getTimeOfString(currentTime);

        return checkTimeForMeeting(times, needTime);
    }


    private List<MyTime> getBusyTimes(List<String> schedule) {
        List<MyTime> times = new LinkedList<>();
        for (String string : schedule) {
            times.add(MyTime.getBusyTime(string));
        }
        return times;
    }

    private BookingResponse checkTimeForMeeting(List<MyTime> times, LocalTime currentTime) {
        LocalTime startWorkDay = LocalTime.of(9, 0);

        boolean isBusy = false;
        if (currentTime.isBefore(startWorkDay)) {
            isBusy = true;
            currentTime = startWorkDay;
            currentTime = currentTime.plusMinutes(1);
        }

        for (MyTime time : times) {
            LocalTime startFreeTime = time.getStartTime();
            LocalTime finishFreeTime = time.getEndTime();
            if (currentTime.isAfter(startFreeTime) && currentTime.isBefore(finishFreeTime)) {
                isBusy = true;
                currentTime = finishFreeTime;
                currentTime = currentTime.plusMinutes(1);
            }
        }

        List<MyTime> freeTimes = getFreeTimes(times);
        for (MyTime time : freeTimes) {
            LocalTime startFreeTime = time.getStartTime();
            LocalTime finishFreeTime = time.getEndTime();
            if (currentTime.isAfter(startFreeTime) && currentTime.isBefore(finishFreeTime)) {
                if (isBusy) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formattedString = startFreeTime.format(formatter);
                    TimeProposalResponse timeProposalResponse = new TimeProposalResponse();
                    timeProposalResponse.setProposedTime(formattedString);
                    return timeProposalResponse;

                }
                return new AvailableResponse();
            }
        }
        return new BusyResponse();
    }


    @SuppressWarnings("unchecked")
    private List<MyTime> getFreeTimes(List<MyTime> busyTimes) {
        LocalTime startWorkDay = LocalTime.of(9, 0);
        LocalTime finishWorkDay = LocalTime.of(18, 0);
        List<MyTime> freeTimes = new ArrayList<>();

        if (busyTimes.size() == 0) {
            freeTimes.add(new MyTime(startWorkDay, finishWorkDay));
            return freeTimes;
        }

        for (int i = 1; i < busyTimes.size(); i++) {
            freeTimes.add(new MyTime(busyTimes.get(i - 1).getEndTime(),
                    busyTimes.get(i).getStartTime()));
        }

        if (startWorkDay.isBefore(busyTimes.get(0).getStartTime())) {
            freeTimes.add(new MyTime(startWorkDay, busyTimes.get(0).getStartTime()));
        }

        int indexLastElement = busyTimes.size() - 1;
        if (finishWorkDay.isAfter(busyTimes.get(indexLastElement).getStartTime())) {
            freeTimes.add(new MyTime(busyTimes.get(indexLastElement).getEndTime(), finishWorkDay));
        }

        Collections.sort(freeTimes);
        return freeTimes;
    }


}
