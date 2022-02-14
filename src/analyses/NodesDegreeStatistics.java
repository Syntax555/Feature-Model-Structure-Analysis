package analyses;

import java.util.LinkedList;
import java.util.List;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import util.StatisticsUtils;

public class NodesDegreeStatistics implements IFMAnalysis{

    private static final String LABEL = "NodesDegreeStatistics";

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
        return StatisticsUtils.toString(getDegreeStatistics(featureModel));
    }

    private List<Double> getDegreeStatistics(IFeatureModel featureModel) {
        List<Double> ratio = new LinkedList<>();

        for (IFeature feature : featureModel.getFeatures()) {
            Double outDegree = (double) feature.getStructure().getChildrenCount();
            Double sumDegree = outDegree + 1;

            ratio.add(outDegree / sumDegree);
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
