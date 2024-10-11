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

    private boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        dfs(0);
    }

    private void dfs(int cur) {
        if (cycleFound) {
            return;
        }
        marked[cur] = true;
        announce();
        for (int adj : maze.adj(cur)) {
            if (!marked[adj]) {
                edgeTo[adj] = cur;
                dfs(adj);
            } else if (edgeTo[cur] != adj) {
                cycleFound = true;
                return;
            }
        }
    }
}

