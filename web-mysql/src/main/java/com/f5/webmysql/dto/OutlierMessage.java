package com.f5.webmysql.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;

  //@Entity
public class OutlierMessage {



	@Id
	private String id = UUID.randomUUID().toString();

	private int n;

	//2018-04-09 16:30:38.007
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="UTC")
	private Date time;

	@OneToMany( mappedBy="outlierMessageId" )
	private List<ReadingOutliers> outliers;

//	@ManyToOne
//	@JoinColumn(name="OutputMessage_id", nullable=false)
//	private OutputMessage outputMessage;

	public OutlierMessage() {
	}
	public OutlierMessage(String detectionMethod, List<ReadingOutliers> outliers) {

		this.outliers = outliers;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<ReadingOutliers> getOutliers() {
		return outliers;
	}

	public void setOutliers(List<ReadingOutliers> outliers) {
		this.outliers = outliers;
	}
//	public OutputMessage getOutputMessageId() {
//		return outputMessage;
//	}
//
//	public void setOutputMessageId(OutputMessage outputMessage) {
//		this.outputMessage = outputMessage;
//	}




	public List<ReadingOutliers> getReadings() {
		return outliers;
	}
	public void setReadings(List<ReadingOutliers> outliers) {
		this.outliers = outliers;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((outliers == null) ? 0 : outliers.hashCode());
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
		OutlierMessage other = (OutlierMessage) obj;

		if (outliers == null) {
			if (other.outliers != null)
				return false;
		} else if (!outliers.equals(other.outliers))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OutlierMessage [outliers=" + outliers + ", n=" + n
				+ ", time=" + time + "]";
	}
		
	
}
