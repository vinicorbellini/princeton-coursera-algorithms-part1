/**
 *
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] sites;
    private final int n, bottom, top;
    private int count;
    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("size of grid must be positive");
        this.n = n;
        sites = new boolean[n + 2][n + 2];
        bottom = n * n + 2;
        top = 0;
        uf = new WeightedQuickUnionUF(bottom);
    }

    private int xyTo2D(int row, int col) {
        return (sites.length - 2) * (row - 1) + col;
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n) throw new IllegalArgumentException("row index out of bounds");
        if (col <= 0 || col > n) throw new IllegalArgumentException("col index out of bounds");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!sites[row][col]) {
            count++;
            sites[row][col] = true;
            if (row == 1) uf.union(top, xyTo2D(row, col));
            else if (uf.find(xyTo2D(row, col)) != uf.find(xyTo2D(row - 1, col)) && sites[row
                    - 1][col]) {
                uf.union(xyTo2D(row, col), xyTo2D(row - 1, col));
            }
            if (uf.find(xyTo2D(row, col)) != uf.find(xyTo2D(row, col - 1)) && sites[row][col - 1]) {
                uf.union(xyTo2D(row, col), xyTo2D(row, col - 1));
            }
            if (uf.find(xyTo2D(row, col)) != uf.find(xyTo2D(row, col + 1)) && sites[row][col + 1]) {
                uf.union(xyTo2D(row, col), xyTo2D(row, col + 1));
            }
            if (row == n) uf.union(bottom - 1, xyTo2D(row, col));
            else if (uf.find(xyTo2D(row, col)) != uf.find(xyTo2D(row + 1, col)) && sites[row
                    + 1][col]) {
                uf.union(xyTo2D(row, col), xyTo2D(row + 1, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(top) == uf.find(xyTo2D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5;
        Percolation perc = new Percolation(n);
        perc.open(1, 1);
        perc.open(1, 2);
        StdOut.println(perc.percolates());
    }
}
