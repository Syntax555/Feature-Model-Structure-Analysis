package formulagraph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String variable;
    private boolean beingVisited;
    private boolean visited;
    private List<String> adjacencyList;

    public Vertex(String variable) {
        this.variable = variable;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbor(String adjacent) {
        if (!this.adjacencyList.contains(adjacent)) {
            this.adjacencyList.add(adjacent);
        }
    }

    public List<String> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNumberOfNeighbors() {
        return adjacencyList.size();
    }
}
