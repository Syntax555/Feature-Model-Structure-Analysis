package formulagraph;

import java.util.HashMap;
import java.util.Map;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.LiteralSet;
import de.ovgu.featureide.fm.core.analysis.cnf.formula.FeatureModelFormula;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class ConnectivityGraph {

    private Map<Integer, Vertex> vertices;

    public ConnectivityGraph(IFeatureModel featureModel) {
        this.vertices = new HashMap<>();
        initializeGraph(featureModel);
    }

    public Vertex getVertex(int variable) {
        return vertices.get(variable);
    }

    public void addVertex(int variable) {
        vertices.put(variable, new Vertex(variable));
    }

    
    private void initializeGraph(IFeatureModel featureModel) {
        CNF cnf = new FeatureModelFormula(featureModel).getCNF();
        int i = 0;
        for (LiteralSet clause : cnf.getClauses()) {
            handleClause(clause);
        }
    }
    
    private void handleClause(LiteralSet set) {
        if (set.size() < 2) {
            return;
        }
        for (int i = 0; i < set.getLiterals().length - 1; i++) {
            for (int j = i+1; j < set.getLiterals().length; j++) {
                handlePair(Math.abs(set.getLiterals()[i]), Math.abs(set.getLiterals()[j]));
            }
        }
    }
    
    private void handlePair(int a, int b) {
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