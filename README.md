# DAA-Fast-Sorting-Selection-Engine
# DAA Fast Sorting and Selection Engine

## Project Description

This project implements and analyzes three algorithms for processing large integer arrays:

* MergeSort
* QuickSort
* QuickSelect

The project focuses on Divide and Conquer techniques and asymptotic analysis. The algorithms are tested on arrays of different sizes and input types.

## Implemented Algorithms

### MergeSort

MergeSort is a divide-and-conquer sorting algorithm.

The array is divided into two halves, each half is sorted recursively, and then the two sorted halves are merged.

The implementation uses:

* One reusable auxiliary buffer
* Insertion Sort for small subarrays
* Linear-time merging

### QuickSort

QuickSort is a divide-and-conquer sorting algorithm based on partitioning the array around a pivot.

The implementation uses:

* Random pivot selection
* Three-way partitioning
* Tail-recursion elimination
* Recursion into the smaller partition first

These techniques help reduce recursion depth and improve performance on arrays with many duplicate values.

### QuickSelect

QuickSelect finds the k-th smallest element without fully sorting the array.

The implementation uses:

* Random pivot selection
* The same three-way partitioning method as QuickSort
* Processing only the partition that contains the required index

## Metrics

The following metrics are collected for each algorithm:

* Execution time in milliseconds
* Number of element comparisons
* Maximum recursion depth

The execution time is measured using `System.nanoTime()`.

Five runs are performed for each experiment, and the median execution time is recorded.

## Input Cases

Each algorithm is tested using four array sizes:

```text
1,000
10,000
100,000
1,000,000
```

Three types of input are used:

* Random — randomly generated integer values
* Sorted — already sorted values
* Duplicates — values from 0 to 9, creating many duplicate elements

## Project Structure

```text
DAA-Fast-Sorting-Selection-Engine
│
├── pom.xml
├── README.md
├── results.csv
│
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── example
    │               └── daa
    │                   ├── Metrics.java
    │                   ├── MergeSort.java
    │                   ├── QuickSort.java
    │                   ├── QuickSelect.java
    │                   └── Benchmark.java
    │
    └── test
        └── java
            └── com
                └── example
                    └── daa
                        └── SortingTest.java
```

## Requirements

* Java 17 or higher
* Apache Maven

## Build the Project

Open a terminal in the project directory and run:

```bash
mvn clean compile
```

## Run Tests

To run all JUnit tests:

```bash
mvn test
```

The tests verify:

* Correctness of MergeSort
* Correctness of QuickSort
* Correctness of QuickSelect
* Random input arrays
* Empty arrays
* One-element arrays
* Arrays containing equal elements
* Invalid QuickSelect indices
* QuickSort recursion depth

## Run Benchmark

First compile the project:

```bash
mvn clean compile
```

Then run:

```bash
java -cp target/classes main.java.com.example.daa.Benchmark
```

The benchmark prints the results to the console and creates:

```text
results.csv
```

## Results

The generated `results.csv` contains the following columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

Example:

```text
MergeSort,random,1000,0.0989,9558,8
QuickSort,random,1000,0.1877,16761,6
QuickSelect,random,1000,0.0107,3458,1
```

The complete experimental results are stored in `results.csv`.

## Testing

JUnit 5 is used for automated testing.

The current test suite checks the correctness of all three algorithms on multiple random arrays and edge cases.

All implemented tests pass successfully.

## Conclusion

The project demonstrates the practical application of divide-and-conquer algorithms to large integer arrays. MergeSort, QuickSort, and QuickSelect were implemented and evaluated using execution time, comparisons, and maximum recursion depth.

The experimental results can be used to compare the practical behavior of the algorithms for random, sorted, and duplicate-heavy input data.
