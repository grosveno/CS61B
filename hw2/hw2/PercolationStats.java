package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int times;
    private double[] threshold;
    private final double coefficient = 1.96;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        threshold = new double[T];
        times = T;
        for (int i = 0; i < times; i++) {
            Percolation module = pf.make(N);
            while (!module.percolates()) {
                int random = StdRandom.uniform(N * N);
                int row = random / N;
                int col = random % N;
                while (module.isOpen(row, col) && module.numberOfOpenSites() < N * N) {
                    random = StdRandom.uniform(N * N);
                    row = random / N;
                    col = random % N;
                }
                module.open(row, col);
            }
            threshold[i] = (double) module.numberOfOpenSites() / (N * N);
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - coefficient * stddev() / Math.sqrt(times);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + coefficient * stddev() / Math.sqrt(times);
    }

}
