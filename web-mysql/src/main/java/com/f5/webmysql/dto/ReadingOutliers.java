package com.f5.webmysql.dto;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

//@Entity
public class ReadingOutliers {
    @Id
    private String id = UUID.randomUUID().toString();

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name="outlierMessageId", nullable=false)
    private OutlierMessage outlierMessageId;

    private double read;

    public ReadingOutliers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OutlierMessage getOutputMessage() {
        return outlierMessageId;
    }

    public void setOutputMessage(OutlierMessage outlierMessage) {
        this.outlierMessageId = outlierMessage;
    }

    public double getRead() {
        return read;
    }

    public void setRead(double read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "outlierMessageId:"+ outlierMessageId +":"+read;
    }
}
