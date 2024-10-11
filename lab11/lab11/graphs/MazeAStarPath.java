package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class Node implements Comparable<Node> {
        int value;
        int priority;

        private Node(int v, int dis) {
            value = v;
            priority = h(v) + dis;
        }

        @Override
        public int compareTo(Node o) {
            return priority - o.priority;
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
        MinPQ<Node> qu = new MinPQ<>();
        Node cur = new Node(s, distTo[s]);
        qu.insert(cur);
        while (!qu.isEmpty()) {
            cur = qu.delMin();
            if (marked[cur.value]) {
                continue;
            }
            marked[cur.value] = true;
            announce();
            if (cur.value == t) {
                return;
            }
            for (int adj : maze.adj(cur.value)) {
                if (!marked[adj] && distTo[cur.value] + 1 < distTo[adj]) {
                    distTo[adj] = distTo[cur.value] + 1;
                    edgeTo[adj] = cur.value;
                    qu.insert(new Node(adj, distTo[adj]));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}