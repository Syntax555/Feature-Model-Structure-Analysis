package analyses;

import java.util.LinkedList;
import java.util.List;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import util.FMUtils;
import util.NodeUtils;
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
        Node cnf = featureModel.getAnalyser().getCnf();
        return StatisticsUtils.toString(NodeUtils.getDegreeStatistics(cnf.getChildren()));
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
