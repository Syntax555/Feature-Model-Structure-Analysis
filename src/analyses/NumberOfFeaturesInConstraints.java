package analyses;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IConstraint;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class NumberOfFeaturesInConstraints implements IFMAnalysis{

    private static final String LABEL = "NumberOfFeaturesInConstraints";

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
        List<IConstraint> constraints = featureModel.getConstraints();

        Set<IFeature> appearingFeatures = new HashSet<>();
		for (IConstraint constraint : constraints) {
			appearingFeatures.addAll(constraint.getContainedFeatures());
		}

        return Integer.toString(appearingFeatures.size());
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
