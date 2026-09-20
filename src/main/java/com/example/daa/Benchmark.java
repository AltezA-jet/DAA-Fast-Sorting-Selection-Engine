package main.java.com.example.daa;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {
            1000,
            10000,
            100000,
            1000000
    };

    private static final int RUNS = 5;

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("results.csv");

        writer.write(
                "algorithm,input,n,time_ms,comparisons,max_depth\n"
        );

        System.out.println();
        System.out.println("================================================================================================");
        System.out.printf(
                "| %-12s | %-12s | %10s | %12s | %15s | %10s |%n",
                "Algorithm",
                "Input Case",
                "n",
                "Time (ms)",
                "Comparisons",
                "Max Depth"
        );
        System.out.println("================================================================================================");

        for (int n : SIZES) {

            runSortBenchmark(writer, "MergeSort", "random", n);
            runSortBenchmark(writer, "MergeSort", "sorted", n);
            runSortBenchmark(writer, "MergeSort", "duplicates", n);

            runSortBenchmark(writer, "QuickSort", "random", n);
            runSortBenchmark(writer, "QuickSort", "sorted", n);
            runSortBenchmark(writer, "QuickSort", "duplicates", n);

            runQuickSelectBenchmark(writer, "random", n);
            runQuickSelectBenchmark(writer, "sorted", n);
            runQuickSelectBenchmark(writer, "duplicates", n);
        }

        System.out.println("================================================================================================");

        writer.close();

        System.out.println();
        System.out.println("Benchmark completed.");
        System.out.println("Results saved to results.csv");
    }

    private static void runSortBenchmark(
            FileWriter writer,
            String algorithm,
            String inputType,
            int n
    ) throws IOException {

        long[] times = new long[RUNS];

        long finalComparisons = 0;
        int finalMaxDepth = 0;

        for (int run = 0; run < RUNS; run++) {

            int[] array = createArray(n, inputType);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            if (algorithm.equals("MergeSort")) {
                MergeSort.sort(array, metrics);
            } else {
                QuickSort.sort(array, metrics);
            }

            long end = System.nanoTime();

            times[run] = end - start;

            finalComparisons = metrics.comparisons;
            finalMaxDepth = metrics.maxDepth;
        }

        Arrays.sort(times);

        long medianTime = times[RUNS / 2];

        double timeMs = medianTime / 1_000_000.0;

        writer.write(
                algorithm + ","
                        + inputType + ","
                        + n + ","
                        + timeMs + ","
                        + finalComparisons + ","
                        + finalMaxDepth
                        + "\n"
        );

        System.out.printf(
                "| %-12s | %-12s | %10d | %12.4f | %15d | %10d |%n",
                algorithm,
                inputType,
                n,
                timeMs,
                finalComparisons,
                finalMaxDepth
        );
    }

    private static void runQuickSelectBenchmark(
            FileWriter writer,
            String inputType,
            int n
    ) throws IOException {

        long[] times = new long[RUNS];

        long finalComparisons = 0;
        int finalMaxDepth = 0;

        int k = n / 2;

        for (int run = 0; run < RUNS; run++) {

            int[] array = createArray(n, inputType);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            QuickSelect.select(array, k, metrics);

            long end = System.nanoTime();

            times[run] = end - start;

            finalComparisons = metrics.comparisons;
            finalMaxDepth = metrics.maxDepth;
        }

        Arrays.sort(times);

        long medianTime = times[RUNS / 2];

        double timeMs = medianTime / 1_000_000.0;

        writer.write(
                "QuickSelect" + ","
                        + inputType + ","
                        + n + ","
                        + timeMs + ","
                        + finalComparisons + ","
                        + finalMaxDepth
                        + "\n"
        );

        System.out.printf(
                "| %-12s | %-12s | %10d | %12.4f | %15d | %10d |%n",
                "QuickSelect",
                inputType,
                n,
                timeMs,
                finalComparisons,
                finalMaxDepth
        );
    }

    private static int[] createArray(
            int n,
            String inputType
    ) {

        int[] array = new int[n];

        if (inputType.equals("random")) {

            Random random = new Random(12345);

            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(1_000_000);
            }

        } else if (inputType.equals("sorted")) {

            for (int i = 0; i < n; i++) {
                array[i] = i;
            }

        } else if (inputType.equals("duplicates")) {

            Random random = new Random(12345);

            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(10);
            }
        }

        return array;
    }
}