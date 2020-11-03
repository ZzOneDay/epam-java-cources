package com.epam.university.java.core.task047;

public class Task047Impl implements Task047 {
    @Override
    public long calculateInversions(int n, int[] a) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j && (a[i] > a[j])) {
                    count++;
                }
            }
        }
        return count;
    }
}
