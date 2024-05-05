import java.util.*;

public class GBFS {
    public static List<Object> findWordLadderGreedy(String startWord, String endWord, Set<String> wordList) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.getHeuristic(), n2.getHeuristic()));
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();
        int visitedNodes = 0; // Variable buat menampung jumlah node yang dikunjungi

        Node startNode = new Node(startWord, calculateHeuristic(startWord, endWord));
        queue.offer(startNode);
        visited.add(startWord);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.getWord();
            visitedNodes++;

            if (currentWord.equals(endWord)) {
                // kalo endWord udh ketemu, buat laddernya
                List<String> ladder = new ArrayList<>();
                ladder.add(endWord);
                String parent = parentMap.get(endWord);
                while (parent != null) {
                    ladder.add(0, parent);
                    parent = parentMap.get(parent);
                }
                return Arrays.asList(ladder, visitedNodes); // Return ladder sama visited nodes
            }

            // Generate semua kemungkinan kata selanjutnya
            for (int i = 0; i < currentWord.length(); i++) {
                char[] wordArray = currentWord.toCharArray();
                for (char c = 'A'; c <= 'Z'; c++) {
                    wordArray[i] = c;
                    String nextWord = new String(wordArray);

                    if (wordList.contains(nextWord) && !visited.contains(nextWord)) {
                        visited.add(nextWord);
                        Node nextNode = new Node(nextWord, calculateHeuristic(nextWord, endWord));
                        queue.offer(nextNode);
                        parentMap.put(nextWord, currentWord);
                    }
                }
            }
        }

        // damn, ga ketemu
        return Arrays.asList(new ArrayList<>(), visitedNodes); // Return empty ladder sama visited nodes
    }

    private static int calculateHeuristic(String word1, String word2) {
        int heuristic = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }
}
