package com.epam.university.java.core.task030;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Task030Impl implements Task030 {
    @Override
    public int daysBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart == null || dateEnd == null) {
            throw new IllegalArgumentException();
        }
        return (int) Duration.between(dateStart.atStartOfDay(),
                dateEnd.atStartOfDay()).toDays();
    }

    @Override
    public LocalDate adjustDays(LocalDate dateStart, int daysToAdd) {
        return dateStart.plusDays(daysToAdd);
    }

    @Override
    public long distanceBetween(Instant instantStart, Instant instantEnd) {
        long number1 = instantStart.getEpochSecond();
        long number2 = instantEnd.getEpochSecond();
        return number2 - number1;
    }


    @Override
    public DayOfWeek getDayOfWeek(LocalDate localDate) {
        return localDate.getDayOfWeek();
    }

    @Override
    public LocalDate getNextWeekend(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    @Override
    public LocalTime getLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        return LocalTime.parse(timeString, formatter);
    }
}
