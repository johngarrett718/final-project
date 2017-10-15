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
    private SalesRepModel firstContact;

    @NotNull
    private SalesRepModel closer;

    @NotNull
    @Size(min=5, max=30)
    private ClientModel client;

    @NotNull
    private double hoursSold;

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

}
