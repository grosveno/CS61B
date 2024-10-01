package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int times;
    private double[] threshold;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        threshold = new double[N];
        times = T;
        for (int i = 0; i < times; i++) {
            Percolation module = pf.make(N);
            while (!module.percolates()) {
                int random = StdRandom.uniform(N * N);
                int row = random / N;
                int col = random % N;
                while (module.isOpen(row, col)) {
                    random = StdRandom.uniform(N * N);
                    row = random / N;
                    col = random % N;
                }
                module.open(row, col);
            }
            threshold[i] = module.numberOfOpenSites();
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
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
}
