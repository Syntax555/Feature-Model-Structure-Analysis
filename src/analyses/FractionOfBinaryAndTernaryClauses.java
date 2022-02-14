package analyses;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class FractionOfBinaryAndTernaryClauses implements IFMAnalysis {

    private static final String LABEL = "FractionOfBinaryAndTernaryClauses";

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
        Node cnf = featureModel.getAnalyser().getCnf();

        Node[] clauses = cnf.getChildren();

        double countTernary = 0;
        double countBinary = 0;

        for (Node clause : clauses) {
            int clauseSize = clause.getLiterals().size();
            if (clauseSize == 2)
                countBinary++;
            else if (clauseSize == 3)
                countTernary++;
        }

        return Double.toString((countBinary / clauses.length)) + ", " +
                Double.toString((countTernary / clauses.length));
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