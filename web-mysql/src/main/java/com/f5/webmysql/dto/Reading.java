package com.f5.webmysql.dto;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

//@Entity
public class Reading {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="outputMessageId", nullable=false)
    private OutputMessage outputMessageId;

    private double read;

    public Reading() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OutputMessage getOutputMessageId() {
        return outputMessageId;
    }

    public void setOutputMessageId(OutputMessage outputMessageId) {
        this.outputMessageId = outputMessageId;
    }

    public double getRead() {
        return read;
    }

    public void setRead(double read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "outputMessageId:"+ outputMessageId +":"+read;
    }
}
