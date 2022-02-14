package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class NumberOfTopFeatures implements IFMAnalysis {

    private static final String LABEL = "NumberOfTopFeatures";

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
        return Integer.toString(featureModel.getStructure().getRoot().getChildrenCount());
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
