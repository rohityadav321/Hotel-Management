/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

/**
 *
 * @author Rohit Yadav
 */
public class Reservation {

    private String ReservId;
    private String Roomtype;
    private String ReservDate;
    private int Days;
//    @Autowired
    private Guest guest;

    public Reservation(String ReservId, String Roomtype, int Days, String ReservDate, Guest guest) {
        this.ReservId = ReservId;
        this.Roomtype = Roomtype;
        this.Days = Days;
        this.ReservDate = ReservDate;
        this.guest = guest;

    }

    public int getDays() {
        return Days;
    }

    public void setDays(int Days) {
        this.Days = Days;
    }

    public String getReservId() {
        return ReservId;
    }

    public void setReservId(String ReservId) {
        this.ReservId = ReservId;
    }

    public void setRoomtype(String Roomtype) {
        this.Roomtype = Roomtype;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getRoomtype() {
        return Roomtype;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getReservDate() {
        return ReservDate;
    }

    public void setReservDate(String ReservDate) {
        this.ReservDate = ReservDate;
    }

}
