package com.epam.university.java.core.task061;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Codes {
    LinkedList<Code> codesList;

    private Codes(LinkedList<Code> codesList) {
        this.codesList = codesList;
    }

    static Codes loadCodes() {
        LinkedList<Code> codesList = new LinkedList<>();
        codesList.add(new Code("I", 1));
        codesList.add(new Code("IV", 4));
        codesList.add(new Code("V", 5));
        codesList.add(new Code("IX", 9));
        codesList.add(new Code("X", 10));
        codesList.add(new Code("XL", 40));
        codesList.add(new Code("L", 50));
        codesList.add(new Code("XC", 90));
        codesList.add(new Code("C", 100));
        codesList.add(new Code("CD", 400));
        codesList.add(new Code("D", 500));
        codesList.add(new Code("CM", 900));
        codesList.add(new Code("M", 1000));

        Collections.sort(codesList, new Comparator<Code>() {
            @Override
            public int compare(Code o1, Code o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        return new Codes(codesList);
    }

    public List<Code> getCodesList() {
        return codesList;
    }
}
