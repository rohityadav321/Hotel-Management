/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rohit Yadav
 */
public class Payment implements Serializable{

    private String paymentID;
    private String ReservID;
    private String guestID;
    private String firstName;
    private String lastName;
    private Boolean paid;

    public Payment(String paymentID, String ReservID, String guestID, String firstName, String lastName, Boolean paid) {
        this.paymentID = paymentID;
        this.ReservID = ReservID;
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.paid = paid;
    }


    public String getPaymentID() {
        return paymentID;
    }

    public String getReservID() {
        return ReservID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setReservID(String ReservID) {
        this.ReservID = ReservID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getPaid() {
        return paid;
    }

}
