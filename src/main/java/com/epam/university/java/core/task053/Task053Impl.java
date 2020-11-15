package com.epam.university.java.core.task053;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task053Impl implements Task053 {
    @Override
    public double calculate(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (input.contains("%#?!")) {
            throw new IllegalArgumentException();
        }

        if (input.replaceAll("\\s", "").length() != input.length()) {
            throw new IllegalArgumentException();
        }

        String test = (input.charAt(input.length() - 1) + "");
        if (test.contains("+")
                || test.contains("-")
                || test.contains("^")
                || test.contains("*")
                || test.contains("/")) {
            throw new IllegalArgumentException();
        }

        int firstBraces = 0;
        int secondBraces = 0;
        for (Character character : input.toCharArray()) {
            if (character == '(') {
                firstBraces++;
            }
            if (character == ')') {
                secondBraces++;
            }
        }
        if (firstBraces != secondBraces) {
            throw new IllegalArgumentException();
        }



        input = stringWithOut(input);

        return Double.parseDouble(input);
    }


    private String stringWithOut(String s) {
        while (s.contains("(") || s.contains(")")) {
            Pattern pattern = Pattern.compile("\\([0-9\\-+*^/.]*\\)");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                int start = matcher.start();
                int finish = matcher.end();
                String result = s.substring(start, finish).replaceAll("[)(]", "");
                String s1 = simplify(result);
                s = s.substring(0, start) + s1 + s.substring(finish);
            }
        }
        s = simplify(s);
        return s;
    }

    private String simplify(String s) {
        if (s.contains("+-")) {
            s = s.replaceAll("\\+-", "-");
        }

        if (s.charAt(0) == '-') {
            return s;
        }

        String test = s.replaceAll("[0-9.]*", "");
        if (test.length() == 0) {
            return s;
        }


        String[] strings = s.split("[^0-9.]*");
        int countSpace = 0;
        for (String numbers : strings) {
            if (numbers.length() == 0) {
                countSpace++;
            }
        }
        if (countSpace > 1) {
            if (s.contains("+")) {
                String[] others = s.split("\\+");
                for (int i = 0; i < others.length; i++) {
                    others[i] = simplify(others[i]);
                }
                double value = 0;
                for (String number : others) {
                    value += Double.parseDouble(number);
                }
                return value + "";
            }

            if (s.contains("-")) {
                String[] others = s.split("-");
                for (int i = 0; i < others.length; i++) {
                    others[i] = simplify(others[i]);
                }
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one - two;
                return value + "";
            }

            if (s.contains("*")) {
                String[] others = s.split("\\*");
                for (int i = 0; i < others.length; i++) {
                    others[i] = simplify(others[i]);
                }
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one * two;
                return value + "";
            }

            if (s.contains("/")) {
                String[] others = s.split("/");
                for (int i = 0; i < others.length; i++) {
                    others[i] = simplify(others[i]);
                }
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one / two;
                return value + "";
            }


        } else {
            if (s.contains("*")) {
                String[] others = s.split("\\*");
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one * two;
                return value + "";
            }


            if (s.contains("^")) {
                String[] others = s.split("\\^");
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = Math.pow(one, two);
                return value + "";
            }

            if (s.contains("/")) {
                String[] others = s.split("/");
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one / two;
                return value + "";
            }

            if (s.contains("+")) {
                String[] others = s.split("\\+");
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one + two;
                return value + "";
            }

            if (s.contains("-")) {
                String[] others = s.split("-");
                double one = Double.parseDouble(others[0]);
                double two = Double.parseDouble(others[1]);
                double value = one - two;
                return value + "";
            }

        }
        return s;
    }

}
