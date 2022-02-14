package analyses;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.io.dimacs.DimacsWriter;
import util.BinaryRunner;
import util.FileUtils;
import util.BinaryRunner.BinaryResult;
import util.BinaryRunner.Status;

public class NumberOfValidConfigurations implements IFMAnalysis {


    private static final String LABEL = "NumberOfValidConfigurations";


    private static final String TEMPORARY_DIMACS_PATH = "temp.dimacs";


    private static final String UNSAT_FLAG = "s UNSATISFIABLE";


    private final static String BINARY_PATH = "solvers" + File.separator + "countAntom";

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
		try {
			createTemporaryDimacs(featureModel.getAnalyser().getCnf());
			BinaryResult result = null;
			result = executeSolver(TEMPORARY_DIMACS_PATH, 1);
			if (result.status == Status.TIMEOUT) {
				return "-1";
			}
			if (result.status == Status.SOLVED) {
				return parseResult(result.stdout);
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
		return "-2";
    }
	
	public static void createTemporaryDimacs(Node cnf) {
		final DimacsWriter dWriter = new DimacsWriter();
		final String dimacsContent = dWriter.write(cnf);
		FileUtils.writeContentToFile(TEMPORARY_DIMACS_PATH, dimacsContent);
    }
    
    public BinaryResult executeSolver(String dimacsPath, long timeout) {
		String command = buildCommand(dimacsPath);
		return BinaryRunner.runBinaryStatic(command, timeout);
	}
    
    private String buildCommand(String dimacsPath) {
		return BINARY_PATH + " --memSize=" + 8000 + " --noThreads=" + 4 + " " + dimacsPath;
    }
    

    public String parseResult(String output) {
		if (isUNSAT(output)) {
			return "0";
		}
		final Pattern pattern = Pattern.compile("model count.*\\d*");
		final Matcher matcher = pattern.matcher(output);
		String result = "";
		if (matcher.find()) {
			result = matcher.group();
		} else {
			return "-2";
		}
		final String[] split = result.split(" ");
		return split[split.length - 1];
    }
    

    private boolean isUNSAT(String output) {
		return output.contains(UNSAT_FLAG);
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
