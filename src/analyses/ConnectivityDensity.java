package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import formulagraph.ConnectivityGraph;

public class ConnectivityDensity implements IFMAnalysis {

    private static final String LABEL = "ConnectivityDensity";

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getResult(IFeatureModel featureModel) {
        ConnectivityGraph graph = new ConnectivityGraph(featureModel);
        int numberOfEdges = graph.getNumberOfEdges();
        return Integer.toString(numberOfEdges);
    }

    @Override
    public String getResult(Node node) {
        return null;
    }

    @Override
    public boolean supportsFormat(Format format) {
        return false;
    }
    
}
