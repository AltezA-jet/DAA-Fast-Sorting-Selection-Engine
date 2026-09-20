package main.java.com.example.daa;

import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        quickSort(a, 0, a.length - 1, metrics);
    }

    private static void quickSort(int[] a, int left, int right,
                                  Metrics metrics) {

        while (left < right) {

            metrics.enterRecursion();

            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            int pivot = a[pivotIndex];

            int[] bounds = partition(a, left, right, pivot, metrics);

            int lessLeft = left;
            int lessRight = bounds[0];

            int greaterLeft = bounds[1];
            int greaterRight = right;

            int leftSize = lessRight - lessLeft + 1;
            int rightSize = greaterRight - greaterLeft + 1;

            if (leftSize < rightSize) {
                if (left < lessRight) {
                    quickSort(a, lessLeft, lessRight, metrics);
                }

                left = greaterLeft;
            } else {
                if (greaterLeft < right) {
                    quickSort(a, greaterLeft, greaterRight, metrics);
                }

                right = lessRight;
            }

            metrics.exitRecursion();
        }
    }

    private static int[] partition(int[] a, int left, int right,
                                   int pivot, Metrics metrics) {

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {

            metrics.addComparison();

            if (a[current] < pivot) {
                swap(a, less, current);
                less++;
                current++;

            } else {
                metrics.addComparison();

                if (a[current] > pivot) {
                    swap(a, current, greater);
                    greater--;

                } else {
                    current++;
                }
            }
        }

        return new int[]{less - 1, greater + 1};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}