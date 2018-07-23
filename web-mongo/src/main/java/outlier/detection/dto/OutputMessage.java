package outlier.detection.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection ="output")
public class OutputMessage {
	@Id private String id;
	
	private String publisher;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="UTC")
	private Date time;
	
	private List<Double> readings;
	
	private List<Outlier> outliers;
	
	private Stats stats;
	
	public OutputMessage() {
	}
	
	//for testing
	public String exposeId() {
		return this.id;
	}
	public void assignId(String id) {
		this.id = id;
	}
	
//	public OutputMessage(String publisher, Date time, List<Double> readings,
//			List<Outlier> outliers, Stats stats) {
//		super();
//		this.publisher = publisher;
//		this.time = time;
//		this.readings = readings;
//		this.outliers = outliers;
//		this.stats = stats;
//	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
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

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((outliers == null) ? 0 : outliers.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((readings == null) ? 0 : readings.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputMessage other = (OutputMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (outliers == null) {
			if (other.outliers != null)
				return false;
		} else if (!outliers.equals(other.outliers))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (readings == null) {
			if (other.readings != null)
				return false;
		} else if (!readings.equals(other.readings))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OutputMessage [id=" + id + ", publisher=" + publisher
				+ ", time=" + time + ", readings=" + readings + ", outliers="
				+ outliers + ", stats=" + stats + "]";
	}
		
}
