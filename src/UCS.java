import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class UCS {
    // Method to find word ladder using UCS algorithm
    public static List<String> findWordLadderUCS(String startWord, String endWord, Set<String> wordList) {
        // Implement UCS algorithm here
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        Node startNode = new Node(startWord, 0);
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
                Node nextNode = new Node(nextWord, currentNode.getCost() + 1);
                queue.offer(nextNode);
                parentMap.put(nextWord, currentWord);
                }
            }
            }
        }

        // No ladder found
        return new ArrayList<>();
    }
}
