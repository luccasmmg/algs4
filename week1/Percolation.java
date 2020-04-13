/* *****************************************************************************
 *  Name:              Luccas Mateus de Medeiros Gomes
 *  Coursera User ID:  Luccasmmg
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final WeightedQuickUnionUF grid;
    private boolean[] openedSites;
    private boolean percolated;
    private int numberOfOpenedSites;
    private final int dimensions;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N should not be negative");
        }
        dimensions = n;
        bottom = n * n + 1;
        grid = new WeightedQuickUnionUF(n*n + 2);
        openedSites = new boolean[n*n];
    }

    // check valid position
    private boolean isInvalid(int row, int col) {
        if (row < 1 || row > this.dimensions || col < 1 || col > this.dimensions) {
            return true;
        }
        return false;
    }

    private int getArrayIndex(int row, int col) {
        return (row - 1) * dimensions + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("Position outside the array");
        }
        if (!isOpen(row, col)) {
            this.openedSites[getArrayIndex(row, col) - 1] = true;
            this.numberOfOpenedSites += 1;
            if (row == 1) {
                this.grid.union(getArrayIndex(row, col), TOP);
            }
            if (row == dimensions) {
                this.grid.union(getArrayIndex(row, col), bottom);
            }
            if (!isInvalid(row -1, col) && isOpen(row -1, col)) {
                this.grid.union(getArrayIndex(row, col), getArrayIndex(row -1, col));
            }
            if (!isInvalid(row +1, col) && isOpen(row +1, col)) {
                this.grid.union(getArrayIndex(row, col), getArrayIndex(row +1, col));
            }
            if (!isInvalid(row, col -1) && isOpen(row, col -1)) {
                this.grid.union(getArrayIndex(row, col), getArrayIndex(row, col - 1));
            }
            if (!isInvalid(row, col + 1) && isOpen(row, col +1)) {
                this.grid.union(getArrayIndex(row, col), getArrayIndex(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("Position outside the array");
        }
        return this.openedSites[getArrayIndex(row, col) - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("Position outside the array");
        }
        return (this.grid.find(getArrayIndex(row, col)) == this.grid.find(TOP));
    }

    // is the site (row, col) connected to bottom?
    private boolean isBottom(int row, int col) {
        if (isInvalid(row, col)) {
            throw new IllegalArgumentException("Position outside the array");
        }
        return (this.grid.find(getArrayIndex(row, col)) == this.grid.find(bottom));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.grid.find(dimensions) == this.grid.find(TOP));
    }
}
