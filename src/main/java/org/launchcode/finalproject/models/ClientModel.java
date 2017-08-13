package org.launchcode.finalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ClientModel{

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max=30)
    private String name;

    @NotNull
    @Size(min=5, max=30)
    private String city;

    @NotNull
    private String state;

    @NotNull
    @Size(min=10, max=10)
    private String phoneNumber;

    @NotNull
    private double hours;

}
