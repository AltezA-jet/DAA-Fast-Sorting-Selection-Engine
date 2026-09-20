package main.java.com.example.daa;

public class Main {

    public static void main(String[] args) {

        int[] array = {7, 2, 9, 1, 5, 3, 8};

        int k = 3;

        Metrics metrics = new Metrics();

        int result = QuickSelect.select(array, k, metrics);

        System.out.println("Array:");
        for (int number : array) {
            System.out.print(number + " ");
        }

        System.out.println();

        System.out.println("k = " + k);
        System.out.println("k-th smallest element = " + result);
        System.out.println("Comparisons = " + metrics.comparisons);
        System.out.println("Max depth = " + metrics.maxDepth);
    }
}