package analyses;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class CtcDensity implements IFMAnalysis {

    private static final String LABEL = "CtcDensity";

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
        return Double.toString( (double) featureModel.getConstraintCount() / featureModel.getNumberOfFeatures());
    }
    
}
