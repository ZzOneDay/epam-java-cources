package com.epam.university.java.core.task061;

import java.util.List;

public class Task061Impl implements Task061 {
    @Override
    public String convertToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException("incorrect number");
        }

        Codes codes = Codes.loadCodes();
        List<Code> codesList = codes.getCodesList();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < codesList.size())) {
            Code code = codesList.get(i);
            if (code.getValue() <= number) {
                sb.append(code.getCode());
                number -= code.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    @Override
    public int convertToArabic(String number) {
        if (number.contains(" ")) {
            throw new IllegalArgumentException();
        }
        String symbols = number.toUpperCase();
        int result = 0;

        Codes codes = Codes.loadCodes();
        List<Code> codesList = codes.getCodesList();

        int i = 0;

        while ((symbols.length() > 0) && (i < codesList.size())) {
            Code code = codesList.get(i);
            if (symbols.startsWith(code.getCode())) {
                result += code.getValue();
                symbols = symbols.substring(code.getCode().length());
            } else {
                i++;
            }
        }

        return result;
    }
}
