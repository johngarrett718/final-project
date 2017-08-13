package org.launchcode.finalproject.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SalesRepModel {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 15)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 15)
    private String lastName;

}
