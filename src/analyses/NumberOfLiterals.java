package analyses;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.LiteralSet;
import de.ovgu.featureide.fm.core.analysis.cnf.formula.FeatureModelFormula;
import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class NumberOfLiterals implements IFMAnalysis {

    private static final String LABEL = "NumberOfLiterals";


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
        int numberOfLiterals = 0;
        for (LiteralSet clause : cnf.getClauses()) {
            numberOfLiterals += clause.getLiterals().length;
        }
        return Integer.toString(numberOfLiterals);
    }
    
}
