package analyses;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import util.NodeUtils;
import util.StatisticsUtils;

public class NumberOfOccurrencesInAHornClauseForEachVariable implements IFMAnalysis {
    private static final String LABEL = "NumberOfOccurrencesInAHornClauseForEachVariable";

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

        List<Node> hornClauses = NodeUtils.getHornClauses(clauses);
        List<Double> ratio = getNumberOfOccurrencesInAHornClauseForEachVariable(hornClauses, cnf.getVariables(), clauses);
        return StatisticsUtils.toString(ratio);
    }

    private List<Double> getNumberOfOccurrencesInAHornClauseForEachVariable(List<Node> hornClauses, List<Object> variables, Node[] clauses) {
        List<Double> ratio = new LinkedList<>();

        for (Object variable : variables) {
            double count = 0;
            for (Node clause : hornClauses)
                count += Collections.frequency(clause.getVariables(), variable);
            
            ratio.add(count / NodeUtils.getVariableCount(variable, clauses));
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