package main.java.com.example.daa;

public class Metrics {

    public long comparisons = 0;

    public int currentDepth = 0;

    public int maxDepth = 0;

    public void addComparison() {
        comparisons++;
    }

    public void enterRecursion() {
        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }
}
