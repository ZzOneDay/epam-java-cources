package com.epam.university.java.core.task020;

import java.util.ArrayList;
import java.util.Collection;

public class Task020Impl implements Task020 {
    @Override
    public int calculate(Collection<String> stones) {
        if (stones == null || stones.size() == 0) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> list = new ArrayList<>(stones);
        int count = 0;
        ArrayList<Character> characters = new ArrayList<>();
        String firstString = list.get(0);

        for (int i = 0; i < firstString.length(); i++) {
            char character = firstString.charAt(i);
            boolean[] result = new boolean[]{false, false, false};
            for (int j = 0; j < list.size(); j++) {
                int st = list.get(j).indexOf(character);
                if (characters.contains(character)) {
                    continue;
                }
                if (st != -1) {
                    result[j] = true;
                }
            }
            if (result[0] && result[1] && result[2]) {
                characters.add(character);
                count++;
            }
        }

        return count;
    }
}
