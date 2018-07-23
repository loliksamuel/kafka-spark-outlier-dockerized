package outlier.detection.dto;

import java.util.List;

import org.joda.time.DateTime;

import outlier.detection.spark.processor.IsoDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class OutputMessage {
	private String publisher;
	
	@JsonSerialize(using = IsoDateSerializer.class)
	private DateTime time;
	
	private List<Double> readings;
	
	private List<Outlier> outliers;
	
	private Stats stats;
	
	public OutputMessage() {
	}
	
	public OutputMessage(String publisher, DateTime time, List<Double> readings,
			List<Outlier> outliers, Stats stats) {
		super();
		this.publisher = publisher;
		this.time = time;
		this.readings = readings;
		this.outliers = outliers;
		this.stats = stats;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public List<Double> getReadings() {
		return readings;
	}
	public void setReadings(List<Double> readings) {
		this.readings = readings;
	}
	public List<Outlier> getOutliers() {
		return outliers;
	}
	public void setOutliers(List<Outlier> outliers) {
		this.outliers = outliers;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
		
}
