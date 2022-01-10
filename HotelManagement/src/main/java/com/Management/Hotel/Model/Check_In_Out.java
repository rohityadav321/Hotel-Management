/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

import java.util.Date;

/**
 *
 * @author Rohit Yadav
 */
public class Check_In_Out {

    private Date CheckIn;
    private Date CheckOut;

    public Check_In_Out(Date CheckIn, Date CheckOut) {
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
    }

    public void setCheckIn(Date CheckIn) {
        this.CheckIn = CheckIn;
    }

    public void setCheckOut(Date CheckOut) {
        this.CheckOut = CheckOut;
    }

    public Date getCheckIn() {
        return CheckIn;
    }

    public Date getCheckOut() {
        return CheckOut;
    }



}
