package analyses;

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
    
}
