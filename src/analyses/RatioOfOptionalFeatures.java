package analyses;

import de.ovgu.featureide.fm.core.FeatureModelAnalyzer;
import de.ovgu.featureide.fm.core.analysis.cnf.formula.FeatureModelFormula;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.job.monitor.NullMonitor;

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
        FeatureModelAnalyzer analyzer = new FeatureModelAnalyzer(new FeatureModelFormula(featureModel));
        int numberOfCoreFeatures = analyzer.getCoreFeatures(new NullMonitor<>()).size();
        int numberOfDeadFeatures = analyzer.getDeadFeatures(new NullMonitor<>()).size();
        return Integer.toString((numberOfFeatures - numberOfCoreFeatures - numberOfDeadFeatures) / numberOfFeatures);
    }
    
}
