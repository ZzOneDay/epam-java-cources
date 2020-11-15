package com.epam.university.java.core.task058;

public class Task058Impl implements Task058 {
    @Override
    public int[][] fillSpiral(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        int[][] arr = new int[n][n];
        int index = 1;
        int finish = (int) Math.pow(n, 2);
        while (index <= finish) {
            index = fillRight(index, arr);
            index = fillDown(index, arr);
            index = fillLeft(index, arr);
            index = fillUp(index, arr);
        }
        return arr;
    }


    private int fillRight(int index, int[][] arr) {
        boolean worked = false;
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr.length; x++) {
                if (arr[y][x] == 0) {
                    worked = true;
                    arr[y][x] = index;
                    index++;
                }
            }
            if (worked) {
                break;
            }
        }
        return index;
    }

    private int fillDown(int index, int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = arr.length - 1; x > 0; x--) {
                if (arr[y][x] == 0) {
                    arr[y][x] = index;
                    index++;
                    break;
                }
            }
        }
        return index;
    }

    private int fillLeft(int index, int[][] arr) {
        boolean worked = false;
        for (int y = arr.length - 1; y > 0; y--) {
            for (int x = arr.length - 1; x >= 0; x--) {
                if (arr[y][x] == 0) {
                    worked = true;
                    arr[y][x] = index;
                    index++;
                }
            }
            if (worked) {
                break;
            }
        }
        return index;
    }


    private int fillUp(int index, int[][] arr) {
        for (int y = arr.length - 1; y > 0; y--) {
            for (int x = 0; x < arr.length; x++) {
                if (arr[y][x] == 0) {
                    arr[y][x] = index;
                    index++;
                    break;
                }
            }
        }
        return index;
    }


    private void print(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr.length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println("");
        }
    }
}
