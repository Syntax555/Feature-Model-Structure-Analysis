package util;

import java.util.Collections;
import java.util.List;

public class StatisticsUtils {

    private StatisticsUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static double getMax(List<Double> values) {
        return Collections.max(values);
    }

    public static double getMin(List<Double> values) {
        return Collections.min(values);
    }

    public static double getMean(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum() / values.size();
    }

    public static double getStandardDeviation(List<Double> values, double mean) {
        double result = 0.0;

        for (Double value : values)
            result += Math.pow(value - mean, 2);

        return Math.sqrt((result / values.size()));
    }

    public static double getVariationCoefficient(double standardDeviation, double mean) {
        return (standardDeviation / mean);          
    }

    public static double getEntropy(double mean) {
        double complement = 1 - mean;
        return -mean * log2(mean) - complement * log2(complement);
    }

    public static double log2(double d) {
        return Math.log(d) / Math.log(2.0);
    }

    public static String toString(List<Double> values) {
        double mean = getMean(values);
        double sd = getStandardDeviation(values, mean);
        double vc = getVariationCoefficient(sd, mean);
        double min = getMin(values);
        double max = getMax(values);
        double entropy = getEntropy(mean);

        return Double.toString(mean) + ", " +
                Double.toString(vc) + ", " +
                Double.toString(min) + ", " +
                Double.toString(max) + ", " +
                Double.toString(entropy);
    }
}
