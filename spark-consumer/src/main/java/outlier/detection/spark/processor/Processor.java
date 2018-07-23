package outlier.detection.spark.processor;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import outlier.detection.dto.Outlier;
import outlier.detection.dto.OutputMessage;
import outlier.detection.dto.Stats;
import outlier.detection.spark.input.InputMessage;

import java.util.LinkedList;
import java.util.List;

public class Processor {
	
	private List<OutlierFinder> finders;
	
	public Processor() {
		List<OutlierFinder> l =new LinkedList<>();
		l.add(new OutlierFinderMedAbsDev());
		finders = l;
	}
	
	public OutputMessage process (InputMessage input) {
		OutputMessage out = new OutputMessage();
		out.setPublisher(input.getPublisher());
		out.setReadings(input.getReadings());
		out.setTime(new DateTime(input.getTime(), DateTimeZone.UTC));
		
		double[] points = toPrimitive(input.getReadings());
		Stats stats = buildStats(points);
		out.setStats(stats);
	
		List<Outlier> outliers = buildOutliers(points);
		out.setOutliers(outliers);
		return out;
	}

	private List<Outlier> buildOutliers(double[] points) {
		List<Outlier> outliers = new LinkedList<>();
		for (OutlierFinder finder : finders) {
			Outlier o = finder.findIn(points);
			outliers.add(o);
		}
		return outliers;
	}

	private Stats buildStats(double[] values) {
		
		Stats stats = new Stats();
		
		double mean = new Mean().evaluate(values);
		double median = new Median().evaluate(values);
		double sd = new StandardDeviation().evaluate(values);
		
		stats.setMean(mean);
		stats.setMedian(median);
		stats.setSd(sd);
		
		return stats;
	}
	
	private double[] toPrimitive(List<Double> list) {
		double[] res = new double[list.size()];
		int idx = 0;
		for (Double r : list) {
			res[idx] = r;
			idx++;
		}
		return res;
	}
}
