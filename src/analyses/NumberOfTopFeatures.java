package analyses;

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
    
}
