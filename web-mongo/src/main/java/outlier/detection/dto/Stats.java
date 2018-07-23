package outlier.detection.dto;

public class Stats {
	
	private Double median;
	private Double mean;
	private Double sd;
	
	public Double getMedian() {
		return median;
	}
	public void setMedian(Double median) {
		this.median = median;
	}
	public Double getSd() {
		return sd;
	}
	public void setSd(Double sd) {
		this.sd = sd;
	}
	public Double getMean() {
		return mean;
	}
	public void setMean(Double mean) {
		this.mean = mean;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mean == null) ? 0 : mean.hashCode());
		result = prime * result + ((median == null) ? 0 : median.hashCode());
		result = prime * result + ((sd == null) ? 0 : sd.hashCode());
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
		Stats other = (Stats) obj;
		if (mean == null) {
			if (other.mean != null)
				return false;
		} else if (!mean.equals(other.mean))
			return false;
		if (median == null) {
			if (other.median != null)
				return false;
		} else if (!median.equals(other.median))
			return false;
		if (sd == null) {
			if (other.sd != null)
				return false;
		} else if (!sd.equals(other.sd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Stats [median=" + median + ", mean=" + mean + ", sd=" + sd
				+ "]";
	}
	
}
