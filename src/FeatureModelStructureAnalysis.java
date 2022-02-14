import java.io.File;
import java.util.List;

import analyses.AnalysisHandler;
import analyses.AverageConstraintSize;
import analyses.AverageNumberOfChilden;
import analyses.ClauseDensity;
import analyses.ConnectivityDensity;
import analyses.CtcDensity;
import analyses.FeaturesInConstraintsDensity;
import analyses.FractionOfBinaryAndTernaryClauses;
import analyses.FractionOfHornClauses;
import analyses.NodesDegreeStatistics;
import analyses.NumberOfAbstractFeatures;
import analyses.NumberOfAtomicSets;
import analyses.NumberOfClauses;
import analyses.NumberOfCompoundFeatures;
import analyses.NumberOfConcreteFeatures;
import analyses.NumberOfConstraints;
import analyses.NumberOfCoreFeatures;
import analyses.NumberOfDeadFeatures;
import analyses.NumberOfFalseOptionalFeatures;
import analyses.NumberOfFeatures;
import analyses.NumberOfFeaturesInConstraints;
import analyses.NumberOfHiddenFeatures;
import analyses.NumberOfLeafFeatures;
import analyses.NumberOfLiterals;
import analyses.NumberOfOccurrencesInAHornClauseForEachVariable;
import analyses.NumberOfTerminalFeatures;
import analyses.NumberOfTopFeatures;
import analyses.NumberOfValidConfigurations;
import analyses.RatioOfClausesDividedByFeatures;
import analyses.RatioOfOptionalFeatures;
import analyses.RatioOfPositiveToNegativeLiteralsInEachClause;
import analyses.RatioOfPositiveToNegativeOccurrencesOfEachVariable;
import analyses.TreeDepth;
import util.FMUtils;
import util.FileUtils;

public class FeatureModelStructureAnalysis {

	AnalysisHandler analysisHandler;
	int timeout;

	public FeatureModelStructureAnalysis() {
		initializeAnalyses();
		timeout = 30;
	}

	public static void main(String[] args) {
		FeatureModelStructureAnalysis analysis = new FeatureModelStructureAnalysis();

		FMUtils.installLibraries();
		if (args.length < 1) {
			System.out.println("Mandatory argument([0]): Input path\n" + "Optional Argument([1]): Output path");
			return;
		}

		List<File> files = FileUtils.getFileListWithExtension(args[0], "xml");

		analysis.handleFiles(files, args[0], args[1]);

	}

	private void handleFiles(List<File> files, String inputPath, String outputfile) {
		String csvContent = analysisHandler.getCsvHeader();
		for (File file : files) {
			csvContent += handleFile(file, inputPath);
		}
		FileUtils.writeContentToFile(outputfile, csvContent);

	}

	private String handleFile(File file, String inputPath) {
		System.out.println("Handling " + file.getName());
		return analysisHandler.evaluateFmFile(file, timeout, inputPath);
	}

	private void initializeAnalyses() {
		analysisHandler = new AnalysisHandler();
		analysisHandler.registerAnalysis(new NumberOfFeatures());
		analysisHandler.registerAnalysis(new NumberOfLeafFeatures());
		analysisHandler.registerAnalysis(new NumberOfTopFeatures());
		analysisHandler.registerAnalysis(new NumberOfConcreteFeatures());
		analysisHandler.registerAnalysis(new NumberOfAbstractFeatures());
		analysisHandler.registerAnalysis(new NumberOfCompoundFeatures());
		analysisHandler.registerAnalysis(new NumberOfTerminalFeatures());
		analysisHandler.registerAnalysis(new NumberOfHiddenFeatures());
		analysisHandler.registerAnalysis(new NumberOfCoreFeatures());
		analysisHandler.registerAnalysis(new NumberOfDeadFeatures());
		analysisHandler.registerAnalysis(new NumberOfFalseOptionalFeatures());
		analysisHandler.registerAnalysis(new RatioOfOptionalFeatures());
		// analysisHandler.registerAnalysis(new NumberOfAtomicSets());

		analysisHandler.registerAnalysis(new NumberOfClauses());
		analysisHandler.registerAnalysis(new FractionOfBinaryAndTernaryClauses());
		analysisHandler.registerAnalysis(new FractionOfHornClauses());
		analysisHandler.registerAnalysis(new NumberOfOccurrencesInAHornClauseForEachVariable());
		analysisHandler.registerAnalysis(new ClauseDensity());
		analysisHandler.registerAnalysis(new RatioOfClausesDividedByFeatures());

		analysisHandler.registerAnalysis(new NodesDegreeStatistics());

		analysisHandler.registerAnalysis(new NumberOfLiterals());
		analysisHandler.registerAnalysis(new RatioOfPositiveToNegativeLiteralsInEachClause());
		analysisHandler.registerAnalysis(new RatioOfPositiveToNegativeOccurrencesOfEachVariable());

		analysisHandler.registerAnalysis(new NumberOfConstraints());
		analysisHandler.registerAnalysis(new NumberOfFeaturesInConstraints());
		analysisHandler.registerAnalysis(new FeaturesInConstraintsDensity());

		analysisHandler.registerAnalysis(new AverageConstraintSize());
		analysisHandler.registerAnalysis(new TreeDepth());
		analysisHandler.registerAnalysis(new AverageNumberOfChilden());
		analysisHandler.registerAnalysis(new CtcDensity());

		analysisHandler.registerAnalysis(new NumberOfValidConfigurations());
		analysisHandler.registerAnalysis(new ConnectivityDensity());
	}

}
