package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private final WorldState word;
        private final int moveSteps;
        private final SearchNode preNode;
        public SearchNode(WorldState a, int b, SearchNode c) {
            word = a;
            moveSteps = b;
            preNode = c;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (!cache.containsKey(word)) {
                cache.put(word, word.estimatedDistanceToGoal());
            }
            if (!cache.containsKey(o.word)) {
                cache.put(o.word, o.word.estimatedDistanceToGoal());
            }
            int estimatedOfSelf = cache.get(word);
            int estimatedOfO = cache.get(o.word);
            return moveSteps + estimatedOfSelf
                    - o.moveSteps - estimatedOfO;
        }
    }

    private List<WorldState> moveSequence;
    private final Map<WorldState, Integer> cache;

    public Solver(WorldState initial) {
        cache = new HashMap<>();
        MinPQ<SearchNode> moveSequences = new MinPQ<>();
        SearchNode currentNode = new SearchNode(initial, 0, null);
        while (!currentNode.word.isGoal()) {
            for (WorldState child : currentNode.word.neighbors()) {
                int moveStep = currentNode.moveSteps + 1;
                SearchNode childNode = new SearchNode(child, moveStep, currentNode);
                if (currentNode.preNode == null || !child.equals(currentNode.preNode.word)) {
                    moveSequences.insert(childNode);
                }
            }
            currentNode = moveSequences.delMin(); // 如果有了直接返回。
        }

        moveSequence = new ArrayList<>();
        moveSequence.add(initial);
        while (currentNode.preNode != null) {
            moveSequence.add(1, currentNode.word);
            currentNode = currentNode.preNode;
        }
    }

    public int moves() {
        return moveSequence.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return moveSequence;
    }
}
