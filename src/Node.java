public class Node {
    private String word;
    private int cost;
    private int heuristic;

    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
    }

    public Node(String word, int cost, int heuristic) {
        this.word = word;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public String getWord() {
        return word;
    }

    public int getCost() {
        return cost;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
