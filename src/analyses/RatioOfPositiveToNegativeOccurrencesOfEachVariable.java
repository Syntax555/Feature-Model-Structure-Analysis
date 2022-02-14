package analyses;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.prop4j.Node;

import util.StatisticsUtils;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class RatioOfPositiveToNegativeOccurrencesOfEachVariable implements IFMAnalysis {

    private static final String LABEL = "RatioOfPositiveToNegativeOccurrencesOfEachVariable(mean,variationCoefficient,min,max,entropy)";

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

        List<Double> ratio = getRatioOfPositiveToNegativeOccurrencesOfEachVariable(clauses, cnf.getVariables());

        return StatisticsUtils.toString(ratio);
    }

    private List<Double> getRatioOfPositiveToNegativeOccurrencesOfEachVariable(Node[] clauses, List<Object> variables) {
        List<Double> ratio = new LinkedList<>();


        for (Object variable : variables) {
            double count = 0;
            double positiv = 0.0;
            for (Node clause : clauses) {
                count += Collections.frequency(clause.getVariables(), variable);
            }
            
            ratio.add(positiv / count);
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