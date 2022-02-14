package analyses;

import java.util.LinkedList;
import java.util.List;

import org.prop4j.Literal;
import org.prop4j.Node;

import util.NodeUtils;
import util.StatisticsUtils;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class RatioOfPositiveToNegativeLiteralsInEachClause implements IFMAnalysis {

    private static final String LABEL = "RatioOfPositiveToNegativeLiteralsInEachClause(mean,variationCoefficient,min,max,entropy)";

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

        Node[] clauses = cnf.getChildren();

        return StatisticsUtils.toString(getRatioOfPositiveLiteralsInClauses(clauses));
    }

    private List<Double> getRatioOfPositiveLiteralsInClauses(Node[] clauses) {
        List<Double> ratio = new LinkedList<>();

        for (Node clause : clauses) {
            List<Literal> literals = clause.getLiterals();

            ratio.add(NodeUtils.getCountOfPostiveLiterals(literals) / literals.size());
        }

        return ratio;
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