package formulagraph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int variable;
    private boolean beingVisited;
    private boolean visited;
    private List<Integer> adjacencyList;

    public Vertex(int variable) {
        this.variable = variable;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbor(int adjacent) {
        if (!this.adjacencyList.contains(adjacent)) {
            this.adjacencyList.add(adjacent);
        }
    }

    public List<Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNumberOfNeighbors() {
        return adjacencyList.size();
    }
}
