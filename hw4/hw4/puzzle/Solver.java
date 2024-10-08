package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    public class SearchNode implements Comparable<SearchNode> {
        private WorldState word;
        private int moveSteps;
        private SearchNode preNode;
        public SearchNode(WorldState a, int b, SearchNode c) {
            word = a;
            moveSteps = b;
            preNode = c;
        }

        @Override
        public int compareTo(SearchNode o) {
            return moveSteps + word.estimatedDistanceToGoal()
                    - o.moveSteps - o.word.estimatedDistanceToGoal();
        }
    }

    private List<WorldState> moveSequence;
    private int moveSteps;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> moveSequences = new MinPQ<>();
        SearchNode currentNode = new SearchNode(initial, 0, null);
        moveSequences.insert(currentNode);
        while (!currentNode.word.isGoal()) {
            currentNode = moveSequences.delMin();
            for (WorldState child : currentNode.word.neighbors()) {
                SearchNode childNode = new SearchNode(child, currentNode.moveSteps + 1, currentNode);
                if (currentNode.preNode == null || !child.equals(currentNode.preNode.word)) {
                    moveSequences.insert(childNode);
                }
            }
        }

        moveSteps = 0;
        moveSequence = new ArrayList<>();
        while (currentNode.preNode != null) {
            moveSequence.add(0, currentNode.word);
            currentNode = currentNode.preNode;
            moveSteps += 1;
        }
    }

    public int moves() {
        return moveSteps;
    }

    public Iterable<WorldState> solution() {
        return moveSequence;
    }
}
