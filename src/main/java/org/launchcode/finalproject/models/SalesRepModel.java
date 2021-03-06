package org.launchcode.finalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SalesRepModel {

    public SalesRepModel() {
        salesRepHours = 0;
    }

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 15)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 15)
    private String lastName;


    @NotNull
    private double salesRepHours;

    //TODO #1 Create a Nested Hashmap Keys for outer is months, key for inner is days
    public void addSale(String month, String day, double hours){
        //TODO #2
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalesRepHours() {return salesRepHours; }

    public void setSalesRepHours(double salesRepHours) {this.salesRepHours = salesRepHours; }

}
