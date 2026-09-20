package test.java.com.example.daa;

import main.java.com.example.daa.Metrics;
import main.java.com.example.daa.MergeSort;
import main.java.com.example.daa.QuickSort;
import main.java.com.example.daa.QuickSelect;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class SortingTest {

    @Test
    void mergeSortBasicTest() {
        int[] array = {5, 2, 8, 1, 3};

        Metrics metrics = new Metrics();

        MergeSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{1, 2, 3, 5, 8},
                array
        );
    }

    @Test
    void quickSortBasicTest() {
        int[] array = {5, 2, 8, 1, 3};

        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{1, 2, 3, 5, 8},
                array
        );
    }

    @Test
    void quickSelectBasicTest() {
        int[] array = {7, 2, 9, 1, 5};

        Metrics metrics = new Metrics();

        int result = QuickSelect.select(array, 2, metrics);

        assertEquals(5, result);
    }

    @Test
    void mergeSortRandomArraysTest() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {

            int[] array = new int[100];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] expected = array.clone();

            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            MergeSort.sort(array, metrics);

            assertArrayEquals(expected, array);
        }
    }

    @Test
    void quickSortRandomArraysTest() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {

            int[] array = new int[100];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] expected = array.clone();

            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            QuickSort.sort(array, metrics);

            assertArrayEquals(expected, array);
        }
    }

    @Test
    void quickSelectRandomArraysTest() {
        Random random = new Random();

        for (int test = 0; test < 100; test++) {

            int[] array = new int[100];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] expected = array.clone();

            Arrays.sort(expected);

            int k = random.nextInt(array.length);

            Metrics metrics = new Metrics();

            int result = QuickSelect.select(array, k, metrics);

            assertEquals(expected[k], result);
        }
    }

    @Test
    void emptyArrayTest() {
        int[] array = {};

        Metrics metrics = new Metrics();

        MergeSort.sort(array, metrics);
        QuickSort.sort(array, metrics);

        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void oneElementTest() {
        int[] array = {42};

        Metrics metrics = new Metrics();

        MergeSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{42},
                array
        );

        array = new int[]{42};

        metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{42},
                array
        );
    }

    @Test
    void allEqualElementsTest() {
        int[] array = {
                5, 5, 5, 5, 5,
                5, 5, 5, 5, 5
        };

        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(
                new int[]{
                        5, 5, 5, 5, 5,
                        5, 5, 5, 5, 5
                },
                array
        );
    }

    @Test
    void invalidQuickSelectIndexTest() {
        int[] array = {1, 2, 3, 4, 5};

        Metrics metrics = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, -1, metrics)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(array, 5, metrics)
        );
    }

    @Test
    void quickSortSorted100kDepthTest() {
        int[] array = new int[100000];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        for (int i = 0; i < array.length; i++) {
            assertEquals(i, array[i]);
        }

        double log2 = Math.log(array.length) / Math.log(2);
        double maxAllowedDepth = 2 * log2;

        assertEquals(
                true,
                metrics.maxDepth <= maxAllowedDepth
        );
    }
}