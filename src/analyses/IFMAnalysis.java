package analyses;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public interface IFMAnalysis {
    /**
     * Provide label that is used as CSV column header
     * @return
     */
    public String getLabel();

    /**
     * Provide a description to improve understandability when using the -h option
     * @return description
     */
    public String getDescription();

    /**
     * Computes result of the analyis for a given feature model
     * @param featureModel
     * @return String to be saved in the csv for that model
     */
    public String getResult(IFeatureModel featureModel);

}
