package com.epam.university.java.core.task046;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Task046Impl implements Task046 {
    @Override
    public List<String> assembleMatryoshka(Integer k, Integer n) {
        if (k == null || n == null) {
            throw new IllegalArgumentException();
        }

        List<String> resultList = new ArrayList<>();
        int[] numbers = getNumbersArr(n);
        ArrayList<int[]> variantNumbers = permutations(numbers);
        List<Mat> mats = getTrimmedMat(variantNumbers, k);
        for (Mat mat : mats) {
            if (checkMat(mat, k)) {
                resultList.add(mat.toString());
            }
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    private List<Mat> getTrimmedMat(ArrayList<int[]> fullNumbers, int k) {
        ArrayList<Mat> mats = new ArrayList();
        for (int[] ints : fullNumbers) {
            int[] arr = new int[k];
            System.arraycopy(ints, 0, arr, 0, arr.length);
            Mat mat = new Mat(arr);
            if (!mats.contains(mat)) {
                mats.add(mat);
            }
        }
        return mats;
    }

    private boolean checkMat(Mat mat, int k) {
        int value = mat.numbers[0];
        for (int i = 0; i < k; i++) {
            int otherValue = mat.numbers[i];
            if (value == otherValue) {
                continue;
            }
            if (value < otherValue) {
                value = otherValue;
            } else {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<int[]> permutations(int[] a) {
        ArrayList<int[]> ret = new ArrayList<>();
        permutation(a, 0, ret);
        return ret;
    }

    private static void permutation(int[] arr, int pos, ArrayList<int[]> list) {
        if (arr.length - pos == 1) {
            list.add(arr.clone());
        } else {
            for (int i = pos; i < arr.length; i++) {
                swap(arr, pos, i);
                permutation(arr, pos + 1, list);
                swap(arr, pos, i);
            }
        }
    }

    private static void swap(int[] arr, int pos1, int pos2) {
        int h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }

    private static class Mat {
        int[] numbers;

        Mat(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public boolean equals(Object obj) {
            Mat other = (Mat) obj;
            for (int i = 0; i < numbers.length; i++) {
                if (other.numbers[i] != this.numbers[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return Arrays.toString(numbers).replaceAll("[\\[\\],]", "");
        }
    }

    private int[] getNumbersArr(int n) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i;
        }
        return numbers;
    }
}
