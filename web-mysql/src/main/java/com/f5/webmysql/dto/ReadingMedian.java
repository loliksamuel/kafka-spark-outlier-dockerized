package com.f5.webmysql.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class ReadingMedian {
    @Id
    private String id  = UUID.randomUUID().toString();;

    private String publisherId;

    private Date date;

    private double read;

    public ReadingMedian() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id; //publisherId+date.toString();
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String id) {
        this.publisherId = id;
    }

    public double getRead() {
        return read;
    }

    public void setRead(double read) {
        this.read = read;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "ReadingMedian:"+ id +":"+read;
    }
}
