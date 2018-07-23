package outlier.detection.dto;

import java.io.Serializable;
import java.util.List;

public class Outlier implements Serializable{
	
	private static final long serialVersionUID = 2115370104120853251L;
	
	private String detectionMethod;
	private List<Double> readings;
	
	public Outlier() {
	}
	
	public Outlier(String detectionMethod, List<Double> readings) {
		this.detectionMethod = detectionMethod;
		this.readings = readings;
	}

	public String getDetectionMethod() {
		return detectionMethod;
	}
	public void setDetectionMethod(String detectionMethod) {
		this.detectionMethod = detectionMethod;
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
				+ ((detectionMethod == null) ? 0 : detectionMethod.hashCode());
		result = prime * result
				+ ((readings == null) ? 0 : readings.hashCode());
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
		Outlier other = (Outlier) obj;
		if (detectionMethod == null) {
			if (other.detectionMethod != null)
				return false;
		} else if (!detectionMethod.equals(other.detectionMethod))
			return false;
		if (readings == null) {
			if (other.readings != null)
				return false;
		} else if (!readings.equals(other.readings))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Outlier [detectionMethod=" + detectionMethod + ", readings="
				+ readings + "]";
	}
		
	
}
