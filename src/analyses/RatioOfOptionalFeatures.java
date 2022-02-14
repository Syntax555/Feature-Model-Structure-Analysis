package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.FeatureModelAnalyzer;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class RatioOfOptionalFeatures implements IFMAnalysis{

    private static final String LABEL = "RatioOptionalFeatures";

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
        FeatureModelAnalyzer analyzer = new FeatureModelAnalyzer(featureModel);
        int numberOfCoreFeatures = analyzer.getCoreFeatures().size();
        int numberOfDeadFeatures = analyzer.getDeadFeatures().size();
        return Double.toString((double)(numberOfFeatures - numberOfCoreFeatures - numberOfDeadFeatures) / numberOfFeatures);
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
