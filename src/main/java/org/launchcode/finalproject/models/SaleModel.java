package org.launchcode.finalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class SaleModel {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    private SalesRepModel firstContact;

    @NotNull
    @ManyToOne
    private SalesRepModel closer;

    @NotNull
    @ManyToOne
    private ClientModel client;

    @NotNull
    private double hoursSold;

    @NotNull
    private int month;

    @NotNull
    private int day;

    public SaleModel(){

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public SalesRepModel getFirstContact() { return firstContact; }

    public void setFirstContact(SalesRepModel firstContact) { this.firstContact = firstContact; }

    public SalesRepModel getCloser() { return closer; }

    public void setCloser(SalesRepModel closer) { this.closer = closer; }

    public ClientModel getClient() { return client; }

    public void setClient(ClientModel client) { this.client = client; }

    public double getHoursSold() { return hoursSold; }

    public void setHoursSold(double hoursSold) { this.hoursSold = hoursSold; }

    public int getMonth() {return month; }

    public void setMonth(int month) {this.month = month; }

    public int getDay() {return day; }

    public void setDay(int day) {this.day = day; }

}
