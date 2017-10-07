package org.launchcode.finalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SaleModel {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max=30)
    private String firstContact;

    @NotNull
    @Size(min=5, max=30)
    private String close;

    @NotNull
    @Size(min=5, max=30)
    private String clientName;

    @NotNull
    private double hoursSold;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstContact() { return firstContact; }

    public void setFirstContact(String firstContact) { this.firstContact = firstContact; }

    public String getClose() { return close; }

    public void setClose(String close) { this.close = close; }

    public String getClientName() { return clientName; }

    public void setClientName(String clientName) { this.clientName = clientName; }

    public double getHoursSold() { return hoursSold; }

    public void setHoursSold(double hoursSold) { this.hoursSold = hoursSold; }

}
