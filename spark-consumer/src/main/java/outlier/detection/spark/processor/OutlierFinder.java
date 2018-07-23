package outlier.detection.spark.processor;

import outlier.detection.dto.Outlier;


public interface OutlierFinder {
	Outlier findIn(double[] points);
}
