package outlier.detection.spark.input;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InputMessage implements Serializable {
	
	private static final long serialVersionUID = -6110124185309163058L;
	
	private String publisher;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="UTC")
	private Date time;
	
	private List<Double> readings;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((readings == null) ? 0 : readings.hashCode());
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
		InputMessage other = (InputMessage) obj;
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InputMessage [publisher=" + publisher + ", time=" + time
				+ ", readings=" + readings + "]";
	}
	
}
