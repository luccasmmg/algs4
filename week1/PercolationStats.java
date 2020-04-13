/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private static final double constant96 = 1.96;
    private final double[] fractions;
    private final int numberOfTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        fractions = new double[trials];
        numberOfTrials = trials;
        for (int i = 0; i < numberOfTrials; i++) {
            Percolation currentPercolation = new Percolation(n);
            while (!currentPercolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                currentPercolation.open(row, col);
            }
            double fraction = (double) currentPercolation.numberOfOpenSites() / (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (constant96 * this.stddev() / Math.sqrt(numberOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (constant96 * this.stddev() / Math.sqrt(numberOfTrials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats testPercolationStats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + testPercolationStats.mean());
        StdOut.println("stddev                  = " + testPercolationStats.stddev());
        StdOut.println("95% confidence interval = " + testPercolationStats.confidenceLo() + " " + testPercolationStats.confidenceHi());
    }
}
