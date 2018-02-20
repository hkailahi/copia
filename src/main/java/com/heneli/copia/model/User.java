package com.heneli.copia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    protected String firstName;
    protected String lastName;
    protected String street;
    protected String city;
    protected String state;
    protected int postal;
    protected String country;
    protected String email;
    protected String phone;
    protected double latitude;
    protected double longitude;
}
