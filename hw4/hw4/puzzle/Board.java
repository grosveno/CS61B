package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private final int[][] tiles;
    private final int N;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = tiles[i].clone();
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            if (tiles[row][col] != BLANK && tiles[row][col] != i + 1) {
                cnt += 1;
            }
        }
        return cnt;
    }

    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            int rowGoal = (tiles[row][col] - 1) / N;
            int colGoal = (tiles[row][col] - 1) % N;
            if (tiles[row][col] != BLANK) {
                cnt += Math.abs(row - rowGoal) + Math.abs(col - colGoal);
            }
        }
        return cnt;
    }

    public boolean equals(Object y) {
        if (y.getClass() != this.getClass()) {
            return false;
        }
        return Arrays.deepEquals(tiles, ((Board) y).tiles);
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
