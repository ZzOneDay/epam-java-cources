package com.epam.university.java.core.task017;

import java.text.SimpleDateFormat;

public class Task017Impl implements Task017 {

    @Override
    public String formatString(Object... args) {
        if (!isCorrectArgs(args)) {
            throw new IllegalArgumentException();
        }
        return "You know " + args[0] + ", " + args[1] + "!";
    }

    @Override
    public String formatNumbers(Object... args) {
        if (!isCorrectArgs(args)) {
            throw new IllegalArgumentException();
        }
        Double value = Double.valueOf(String.valueOf(args[0]));
        String string1 = String.format("%.1f", value).replaceAll(",", ".");
        String string2 = String.format("%.2f", value).replaceAll(",", ".");
        String string3 = String.format("%+.2f", value).replaceAll(",", ".");
        String string4 = Double.toHexString(value);
        return string1 + ", " + string2 + ", " + string3 + ", " + string4;
    }

    @Override
    public String formatDates(Object... args) {
        //"yyyy.dd.MM"
        if (!isCorrectArgs(args)) {
            throw new IllegalArgumentException();
        }
        String pattern = "yyyy.dd.MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(args[0]);
    }

    private boolean isCorrectArgs(Object... args) {
        if (args.length == 0) {
            return false;
        }

        for (Object arg : args) {
            if (arg == null) {
                return false;
            }
        }
        return true;
    }
}
