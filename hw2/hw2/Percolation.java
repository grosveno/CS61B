package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private int numOfOpen;
    private int topSite;
    private int buttonSite;
    private WeightedQuickUnionUF site;
    private WeightedQuickUnionUF siteWithoutButton;
    private boolean[][] grid;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        this.N = N;
        numOfOpen = 0;
        topSite = N * N;
        buttonSite = N * N + 1;
        site = new WeightedQuickUnionUF(N * N + 2);
        siteWithoutButton = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        for (int i = 0; i < N; i++) {
            site.union(topSite, computePoint(0, i));
            siteWithoutButton.union(topSite, computePoint(0, i));
        }
        for (int i = 0; i < N; i++) {
            site.union(buttonSite, computePoint(N - 1, i));
        }
    }

    private void throwException(int row, int col) {
        if (row >= N || col >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (row < 0 || col < 0) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    private boolean isInGrid(int row, int col) {
        return row >= 0 && row < N
               && col >= 0 && col < N;
    }

    private int computePoint(int row, int col) {
        return row * N + col;
    }

    private void connectOneNeighbour(int posY, int posX, int row, int col) {
        if (isInGrid(posY, posX) && grid[posY][posX]) {
            site.union(computePoint(posY, posX), computePoint(row, col));
            siteWithoutButton.union(computePoint(posY, posX), computePoint(row, col));
        }
    }

    private void connectAllNeighbour(int row, int col) {
        connectOneNeighbour(row + 1, col, row, col);
        connectOneNeighbour(row, col - 1, row, col);
        connectOneNeighbour(row, col + 1, row, col);
        connectOneNeighbour(row - 1, col, row, col);
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        // throw exception
        throwException(row, col);
        // set ture
        grid[row][col] = true;
        numOfOpen += 1;
        // connect neighbour point which is true
        connectAllNeighbour(row, col);
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        throwException(row, col);
        return grid[row][col];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        throwException(row, col);
        return siteWithoutButton.connected(topSite, computePoint(row, col))
                && grid[row][col];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return site.connected(topSite, buttonSite);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);
        p.open(2, 1);
        p.open(3, 1);
        p.open(2, 3);
        System.out.println(p.isFull(1, 3));
        System.out.println(p.percolates());
    }
}
