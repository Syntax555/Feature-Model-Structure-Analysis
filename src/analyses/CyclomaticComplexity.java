package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import formulagraph.ConnectivityGraph;

public class CyclomaticComplexity implements IFMAnalysis {

    private static final String LABEL = "CyclomaticComplexity";

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
        //TODO implement this
        return null;
    }

    @Override
    public String getResult(Node node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supportsFormat(Format format) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
