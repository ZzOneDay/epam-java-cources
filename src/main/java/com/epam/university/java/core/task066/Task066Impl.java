package com.epam.university.java.core.task066;

public class Task066Impl implements Task066 {
    @Override
    public long repeatString(String infiniteString, long limiter) {
        if (limiter % infiniteString.length() == 0) {
            return hardCount(infiniteString, limiter);
        } else {
            return simpleCount(infiniteString, limiter);
        }
    }


    private int replayInString(String string) {
        char[] chars = string.toCharArray();
        int value = 0;
        for (char ch : chars) {
            if (ch == 'a') {
                value++;
            }
        }
        return value;
    }

    private long hardCount(String infiniteString, long limiter) {
        int replayInString = replayInString(infiniteString);
        int stringLength = infiniteString.length();
        long reductionCount = limiter / stringLength;
        return reductionCount * replayInString;
    }

    private long simpleCount(String infiniteString, long limiter) {
        char[] chars = infiniteString.toCharArray();
        long value = 0;

        int index = 0;
        for (long i = 0; i < limiter; i++) {
            char ch = chars[index];
            if (ch == 'a') {
                value++;
            }
            index++;
            if (index == chars.length) {
                index = 0;
            }
        }
        return value;
    }
}
