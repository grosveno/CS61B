package lab11.graphs;

import java.util.List;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int[] parent;
    private boolean cycleFound;

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[maze.V()];
    }

    @Override
    public void solve() {
        dfs(0);
        announce();
    }

    private void dfs(int current) {
        marked[current] = true;
        if (cycleFound) {
            return;
        }
        for (int neighbour : maze.adj(current)) {
            if (marked[neighbour] && neighbour != parent[current]) {
                parent[neighbour] = current;
                cycleFound = true;
                drawCycle(current, neighbour);
                return;
            }
            if (!marked[neighbour]) {
                parent[neighbour] = current;
                dfs(neighbour);
            }
        }
    }

    private void drawCycle(int s, int t) {
        int current = s;
        while (current != t) {
            edgeTo[current] = parent[current];
            current = parent[current];
        }
    }
}

