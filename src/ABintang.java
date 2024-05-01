import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ABintang {
    // Method to find word ladder using A* algorithm
    public static List<String> findWordLadderAStar(String startWord, String endWord, Set<String> wordList) {
        // Implement A* algorithm here
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.getHeuristic() + n1.getCost(), n2.getHeuristic() + n2.getCost()));
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        Node startNode = new Node(startWord, calculateHeuristic(startWord, endWord), 0);
        queue.offer(startNode);
        visited.add(startWord);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.getWord();

            if (currentWord.equals(endWord)) {
                // Found the end word, construct the ladder
                List<String> ladder = new ArrayList<>();
                ladder.add(endWord);
                String parent = parentMap.get(endWord);
                while (parent != null) {
                    ladder.add(0, parent);
                    parent = parentMap.get(parent);
                }
                return ladder;
            }

            // Generate all possible next words
            for (int i = 0; i < currentWord.length(); i++) {
                char[] wordArray = currentWord.toCharArray();
                for (char c = 'A'; c <= 'Z'; c++) {
                    wordArray[i] = c;
                    String nextWord = new String(wordArray);

                    if (wordList.contains(nextWord) && !visited.contains(nextWord)) {
                        visited.add(nextWord);
                        Node nextNode = new Node(nextWord, calculateHeuristic(nextWord, endWord), currentNode.getCost() + 1);
                        queue.offer(nextNode);
                        parentMap.put(nextWord, currentWord);
                    }
                }
            }
        }

        // No ladder found
        return new ArrayList<>();
    }

    private static int calculateHeuristic(String word1, String word2) {
        // Implement the heuristic calculation here
        int heuristic = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }
}
