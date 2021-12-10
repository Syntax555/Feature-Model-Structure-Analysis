package util;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ovgu.featureide.fm.attributes.base.impl.ExtendedFeatureModelFactory;
import de.ovgu.featureide.fm.attributes.format.XmlExtendedFeatureModelFormat;
import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.formula.FeatureModelFormula;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;
import de.ovgu.featureide.fm.core.base.impl.ConfigFormatManager;
import de.ovgu.featureide.fm.core.base.impl.ConfigurationFactoryManager;
import de.ovgu.featureide.fm.core.base.impl.CoreFactoryWorkspaceLoader;
import de.ovgu.featureide.fm.core.base.impl.DefaultConfigurationFactory;
import de.ovgu.featureide.fm.core.base.impl.DefaultFeatureModelFactory;
import de.ovgu.featureide.fm.core.base.impl.FMFactoryManager;
import de.ovgu.featureide.fm.core.base.impl.FMFormatManager;
import de.ovgu.featureide.fm.core.base.impl.MultiFeatureModelFactory;
import de.ovgu.featureide.fm.core.configuration.DefaultFormat;
import de.ovgu.featureide.fm.core.configuration.EquationFormat;
import de.ovgu.featureide.fm.core.configuration.ExpressionFormat;
import de.ovgu.featureide.fm.core.configuration.FeatureIDEFormat;
import de.ovgu.featureide.fm.core.configuration.XMLConfFormat;
import de.ovgu.featureide.fm.core.io.dimacs.DIMACSFormat;
import de.ovgu.featureide.fm.core.io.dimacs.DimacsWriter;
import de.ovgu.featureide.fm.core.io.manager.FeatureModelManager;
import de.ovgu.featureide.fm.core.io.sxfm.SXFMFormat;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelFormat;
import de.ovgu.featureide.fm.core.job.monitor.NullMonitor;

public class FMUtils {

	private FMUtils(){}
	
	
	public static IFeatureModel readFeatureModel(String path) {
		return FeatureModelManager.load(Paths.get(path));
	}
	
	public static void saveFeatureModelAsDIMACS(IFeatureModel model, String savePath) {
		FeatureModelManager.save(model, Paths.get(savePath), new DIMACSFormat());
	}


	public static CNF getCNF(IFeatureModel model) {
		return new FeatureModelFormula(model).getCNF();
	}

	public static void saveCNF(CNF cnf, String path) {
		// clear if one exists
		final DimacsWriter dWriter = new DimacsWriter(cnf);
		final String dimacsContent = dWriter.write();
		FileUtils.writeContentToFile(path.replaceFirst("[.][^.]+$", "") + ".dimacs", dimacsContent);
	}
	


	
	public static void installLibraries() {
		FMFactoryManager.getInstance().addExtension(DefaultFeatureModelFactory.getInstance());
		FMFactoryManager.getInstance().addExtension(ExtendedFeatureModelFactory.getInstance());
		FMFactoryManager.getInstance().addExtension(MultiFeatureModelFactory.getInstance());
		FMFactoryManager.getInstance().setWorkspaceLoader(new CoreFactoryWorkspaceLoader());

		FMFormatManager.getInstance().addExtension(new XmlFeatureModelFormat());
		FMFormatManager.getInstance().addExtension(new XmlExtendedFeatureModelFormat());
		FMFormatManager.getInstance().addExtension(new SXFMFormat());
		
		ConfigurationFactoryManager.getInstance().addExtension(DefaultConfigurationFactory.getInstance());
		ConfigurationFactoryManager.getInstance().setWorkspaceLoader(new CoreFactoryWorkspaceLoader());

		ConfigFormatManager.getInstance().addExtension(new XMLConfFormat());
		ConfigFormatManager.getInstance().addExtension(new DefaultFormat());
		ConfigFormatManager.getInstance().addExtension(new FeatureIDEFormat());
		ConfigFormatManager.getInstance().addExtension(new EquationFormat());
		ConfigFormatManager.getInstance().addExtension(new ExpressionFormat());
	}
	
	public static Set<String> getCoreFeatureNamesByTree(IFeatureModel model) {
		IFeatureStructure root  = model.getStructure().getRoot();
		return getCoreAncestorsRecursive(root);
	}
	
	
	private static Set<String> getCoreAncestorsRecursive(IFeatureStructure parent) {
		Set<String> coreAncestors = new HashSet<>();
		coreAncestors.add(parent.getFeature().getName());
		for (IFeatureStructure child : parent.getChildren()) {
			if(child.isMandatory()) {
				coreAncestors.addAll(getCoreAncestorsRecursive(child));
			}
		}
		return coreAncestors;
	}
	
	public static Set<IFeature> getCoreFeatures(IFeatureModel model) {
		FeatureModelFormula formula = new FeatureModelFormula(model);
		return new HashSet<>(formula.getAnalyzer().getCoreFeatures(new NullMonitor<>()));
	}
	
	public static Set<IFeature> getDeadFeatures(IFeatureModel model) {
		FeatureModelFormula formula = new FeatureModelFormula(model);
		return new HashSet<>(formula.getAnalyzer().getDeadFeatures(new NullMonitor<>()));
	}
	
	public static Set<IFeature> getFalseOptionalFeatures(IFeatureModel model) {
		FeatureModelFormula formula = new FeatureModelFormula(model);
		return new HashSet<>(formula.getAnalyzer().getFalseOptionalFeatures(new NullMonitor<>()));
	}
	
	public static IFeature getParent(IFeature feat) {
		return feat.getStructure().getParent().getFeature();
	}
	
	public static List<IFeature> getFeaturesInOrder(IFeatureModel model) {
		return getSubtreeOfFeature(model.getStructure().getRoot());
	}
	
	public static List<IFeature> getSubtreeOfFeature(IFeatureStructure struct) {
		List<IFeature> features = new ArrayList<>();
		features.add(struct.getFeature());
		for (IFeatureStructure child : struct.getChildren()) {
			features.addAll(getSubtreeOfFeature(child));
		}
		return features;
		
	}
	
}
