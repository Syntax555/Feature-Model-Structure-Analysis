package formulagraph;

import java.util.HashMap;
import java.util.Map;

import org.prop4j.Node;
import org.prop4j.Or;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class ConnectivityGraph {

    private Map<String, Vertex> vertices;

    public ConnectivityGraph(IFeatureModel featureModel) {
        this.vertices = new HashMap<>();
        initializeGraph(featureModel);
    }

    public Vertex getVertex(String variable) {
        return vertices.get(variable);
    }

    public void addVertex(String variable) {
        vertices.put(variable, new Vertex(variable));
    }

    private void initializeGraph(IFeatureModel featureModel) {
        Node cnf = featureModel.getAnalyser().getCnf();
        for (Node clause : cnf.getChildren()) {
            handleClause(clause);
        }
    }
    
    private void handleClause(Node set) {
        if (set.getChildren().length < 2) {
            return;
        }
        if (!(set instanceof Or)) {
            System.out.println("Not CNF");
        }
        for (int i = 0; i < set.getChildren().length - 1; i++) {
            for (int j = i+1; j < set.getChildren().length; j++) {
                handlePair((String) set.getChildren()[i].getLiterals().get(0).var, (String) set.getChildren()[j].getLiterals().get(0).var);
            }
        }
    }
    
    private void handlePair(String a, String b) {
        if (getVertex(a) == null) {
            addVertex(a);
        }
        if (getVertex(b) == null) {
            addVertex(b);
        }
        getVertex(a).addNeighbor(b);
        getVertex(b).addNeighbor(a);
    }

    public int getNumberOfEdges() {
        int edges = 0;
        for (Vertex vertex : vertices.values()) {
            edges += vertex.getNumberOfNeighbors();
        }

        return edges / 2;
    }
}