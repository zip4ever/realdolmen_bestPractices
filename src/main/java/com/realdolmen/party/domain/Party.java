package com.realdolmen.party.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by BPTAT47 on 18/09/2014.
 */
@Entity
public class Party {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private double ticketPrice;
    private Date date;
    private String location;
    private String dj;
    private boolean alcoholAllowed;


    public Party(String name, double ticketPrice) {
        this.name = name;
        this.ticketPrice = ticketPrice;
    }

    public Party(String name, double ticketPrice, Date date, String location, String dj, boolean alcoholAllowed) {
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.date = date;
        this.location = location;
        this.dj = dj;
        this.alcoholAllowed = alcoholAllowed;
    }

    public Party() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public boolean isAlcoholAllowed() {
        return alcoholAllowed;
    }

    public void setAlcoholAllowed(boolean alcoholAllowed) {
        this.alcoholAllowed = alcoholAllowed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
