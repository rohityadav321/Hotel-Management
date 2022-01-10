/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rohit Yadav
 */
//@Component
public class AddressModel {

    private String Address1;
    private String City;
    private String State;
    private String Country;

    public AddressModel(String Address1, String City, String State, String Country) {
        this.Address1 = Address1;
        this.City = City;
        this.State = State;
        this.Country = Country;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

}
