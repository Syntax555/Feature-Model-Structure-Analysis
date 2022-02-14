package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class RatioOfClausesDividedByFeatures implements IFMAnalysis {

    private static final String LABEL = "RatioOfClausesDividedByFeatures";

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

        double numberOfFeatures = featureModel.getNumberOfFeatures();
        double numberOfClauses = cnf.getChildren().length;

        return Double.toString(numberOfClauses / numberOfFeatures);
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