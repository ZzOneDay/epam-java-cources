package com.epam.university.java.core.task037;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;

public class Task037Impl implements Task037 {
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    public Collection<String> switcher(Callable<String> ticker, Callable<String> tacker) {
        if (ticker == null || tacker == null) {
            throw new IllegalArgumentException();
        }

        try {
            for (int i = 0; i < 5; i++) {
                new Watch(strings, ticker).call();
                new Watch(strings, tacker).call();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(strings);
        return strings;
    }

    static class Watch implements Callable {
        private ArrayList<String> strings;
        private Callable<String> message;

        private Watch(ArrayList<String> strings, Callable<String> message) {
            this.strings = strings;
            this.message = message;
        }

        @Override
        public Object call() throws Exception {
            strings.add(message.call());
            return strings;
        }
    }
}
