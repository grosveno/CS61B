package lab11.graphs;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue = new PriorityQueue<>();
        int current = s;
        marked[current] = true;
        announce();
        while (!(current == t)) {
            for (int neighbour : maze.adj(current)) {
                if (!marked[neighbour]) {
                    edgeTo[neighbour] = current;
                    announce();
                    distTo[neighbour] = distTo[current] + 1;
                    queue.add(neighbour);
                }
            }
            current = queue.remove();
            marked[current] = true;
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

