package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class NumberOfClauses implements IFMAnalysis {

    private static final String LABEL = "NumberOfClauses";


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
        Node cnf = featureModel.getAnalyser().getCnf();
        return Integer.toString(cnf.getChildren().length);
    }

    public String getResult(Node node) {
        return Integer.toString(node.getChildren().length);
    }

    @Override
    public boolean supportsFormat(Format format) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
