package main.java.com.example.daa;


public class MergeSort {

    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        int[] buffer = new int[a.length];

        mergeSort(a, 0, a.length - 1, buffer, metrics);
    }

    private static void mergeSort(int[] a, int left, int right,
                                  int[] buffer, Metrics metrics) {

        metrics.enterRecursion();

        if (right - left + 1 <= CUTOFF) {
            insertionSort(a, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(a, left, middle, buffer, metrics);
        mergeSort(a, middle + 1, right, buffer, metrics);

        merge(a, left, middle, right, buffer, metrics);

        metrics.exitRecursion();
    }

    private static void insertionSort(int[] a, int left, int right,
                                       Metrics metrics) {

        for (int i = left + 1; i <= right; i++) {

            int key = a[i];
            int j = i - 1;

            while (j >= left) {
                metrics.addComparison();

                if (a[j] <= key) {
                    break;
                }

                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int left, int middle, int right,
                              int[] buffer, Metrics metrics) {

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {

            metrics.addComparison();

            if (a[i] <= a[j]) {
                buffer[k] = a[i];
                i++;
            } else {
                buffer[k] = a[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            buffer[k] = a[i];
            i++;
            k++;
        }

        while (j <= right) {
            buffer[k] = a[j];
            j++;
            k++;
        }

        for (int index = left; index <= right; index++) {
            a[index] = buffer[index];
        }
    }
}

