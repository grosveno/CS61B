package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class State implements Comparable<State> {
        int value;
        int estimatedToGoal;

        private State(int v) {
            value = v;
            estimatedToGoal = h(v);
        }

        @Override
        public int compareTo(State o) {
            return estimatedToGoal + value - o.value - o.estimatedToGoal;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        MinPQ<State> queue = new MinPQ<>();
        State current = new State(s);
        marked[s] = true;
        announce();
        while (current.value != t) {
            for (int i : maze.adj(current.value)) {
                State neighbour = new State(i);
                if (!marked[i]) {
                    queue.insert(neighbour);
                    edgeTo[i] = current.value;
                    announce();
                }
            }
            current = queue.delMin();
            marked[current.value] = true;
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

