package analyses;

import java.util.LinkedList;
import java.util.List;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class ClauseNodesDegreeStatistics implements IFMAnalysis {

    private static final String LABEL = "ClauseNodesDegreeStatistics";

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
