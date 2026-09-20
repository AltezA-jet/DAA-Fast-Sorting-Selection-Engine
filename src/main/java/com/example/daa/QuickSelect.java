package main.java.com.example.daa;

import java.util.Random;

public class QuickSelect {

    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {

        if (a == null || a.length == 0) {
            throw new IllegalArgumentException(
                    "Array must not be null or empty"
            );
        }

        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException(
                    "Index k is out of range"
            );
        }

        int left = 0;
        int right = a.length - 1;

        while (left <= right) {

            metrics.enterRecursion();

            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            int pivot = a[pivotIndex];

            int[] bounds = QuickSort.partition(
                    a, left, right, pivot, metrics
            );

            int lessRight = bounds[0];
            int greaterLeft = bounds[1];

            if (k <= lessRight) {

                right = lessRight;

            } else if (k >= greaterLeft) {

                left = greaterLeft;

            } else {

                metrics.exitRecursion();
                return a[k];
            }

            metrics.exitRecursion();
        }

        throw new IllegalStateException(
                "QuickSelect failed to find the element"
        );
    }
}