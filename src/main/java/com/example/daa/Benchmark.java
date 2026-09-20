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

        for (int n : SIZES) {

            runBenchmark(writer, "MergeSort", "random", n);
            runBenchmark(writer, "MergeSort", "sorted", n);
            runBenchmark(writer, "MergeSort", "duplicates", n);

            runBenchmark(writer, "QuickSort", "random", n);
            runBenchmark(writer, "QuickSort", "sorted", n);
            runBenchmark(writer, "QuickSort", "duplicates", n);
        }

        writer.close();

        System.out.println("Benchmark completed.");
        System.out.println("Results saved to results.csv");
    }

    private static void runBenchmark(
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

        System.out.println(
                algorithm
                        + " | "
                        + inputType
                        + " | n="
                        + n
                        + " | time="
                        + timeMs
                        + " ms"
                        + " | comparisons="
                        + finalComparisons
                        + " | depth="
                        + finalMaxDepth
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