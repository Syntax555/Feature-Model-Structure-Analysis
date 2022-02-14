package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import util.NodeUtils;

public class FractionOfHornClauses implements IFMAnalysis {

    private static final String LABEL = "FractionOfHornClauses";

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
        Node[] clauses = featureModel.getAnalyser().getCnf().getChildren();

        return Double.toString((NodeUtils.getHornClauses(clauses).size() / clauses.length));
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