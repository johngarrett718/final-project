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

}
