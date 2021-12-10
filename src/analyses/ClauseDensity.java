package analyses;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.formula.FeatureModelFormula;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class ClauseDensity implements IFMAnalysis {

    private static final String LABEL = "ClauseDensity";

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
        FeatureModelFormula formula = new FeatureModelFormula(featureModel);
		CNF cnf = formula.getCNF();
		return Double.toString((double) cnf.getClauses().size() / cnf.getVariables().size());
    }
    
}
