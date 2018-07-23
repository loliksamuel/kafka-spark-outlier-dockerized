package outlier.detection.spark.processor;

import org.apache.commons.math3.stat.descriptive.rank.Median;
import outlier.detection.dto.Outlier;

import java.util.LinkedList;
import java.util.List;

public class OutlierFinderMedAbsDev implements OutlierFinder {

	@Override
	public Outlier findIn(double[]  points) {
		List<Double> outliers = new LinkedList<>();
		double median_x = new Median().evaluate(points);
		double medAbsDev = medianAbsDeviation(points);
		for (int i = 0; i < points.length; i++) {
			double x = Math.abs(points[i]-median_x)/medAbsDev;
			if (x > 2) {
				outliers.add(points[i]);
			}
		}
		return new Outlier("local_MedAbsDev", outliers);
	}

	private double medianAbsDeviation(double[] points) {
		//median(abs(x-median(x)))
		
		double median_x = new Median().evaluate(points);
		double[] abs = new double[points.length];
		for (int i = 0; i < points.length; i++) {
			abs[i] = Math.abs(points[i]-median_x);
		}
		double medAbsDev = new Median().evaluate(abs);
		return medAbsDev;
	}
}
