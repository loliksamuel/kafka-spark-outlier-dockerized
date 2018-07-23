package com.f5.webmysql.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.UUID;

;

//@Entity
public class OutputMessage {

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String publisher;

	//2018-04-09 16:30:38.007
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone="UTC")
	private Date time;

	@OneToMany( mappedBy="outputMessageId" )
	private List<Reading> readings;


	
	//private Stats stats;
	
	public OutputMessage() {
	}
	
	//for testing
	public String geteId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
//	public OutputMessage(String publisher, Date time, List<Double> readings,
//			List<OutlierMessage> outliers, Stats stats) {
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
	public List<Reading> getReadings() {
		return readings;
	}
	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

//	public Stats getStats() {
//		return stats;
//	}
//	public void setStats(Stats stats) {
//		this.stats = stats;
//	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

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
		OutputMessage other = (OutputMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
				+ ", time=" + time + ", readings=" + readings +  "]";
	}
		
}
