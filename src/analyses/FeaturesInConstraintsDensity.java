package analyses;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ovgu.featureide.fm.core.base.IConstraint;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class FeaturesInConstraintsDensity implements IFMAnalysis {

    private static final String LABEL = "FeaturesInConstraintsDensity";

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
		int numberOfFeatures = featureModel.getNumberOfFeatures();
		
		List<IConstraint> constraints = featureModel.getConstraints();
		
		Set<IFeature> appearingFeatures = new HashSet<>();
		for (IConstraint constraint : constraints) {
			appearingFeatures.addAll(constraint.getContainedFeatures());
		}
		
		int numberOfFeaturesAppearingInAConstraint = appearingFeatures.size();
		
		return Double.toString((double)numberOfFeaturesAppearingInAConstraint / numberOfFeatures);
    }
    
}
