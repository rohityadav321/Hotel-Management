/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.GuestRepository;
import com.Management.Hotel.Model.Guest;
import com.Management.Hotel.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


/**
 *
 * @author Rohit Yadav
 */
@Service
public class CheckInService {

    @Autowired
    JdbcTemplate db;
    @Autowired
    GuestRepository gr;

    public ResponseEntity<?> checkin(Reservation reserve) throws Exception {

        String sDate1 = reserve.getReservDate();
//        java.sql.Date sqlDate = java.sql.Date.valueOf(sDate1);
        System.out.println(sDate1);
//        System.exit(0);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Guest guest = reserve.getGuest();
        String Guest_ID = ReservationService.GuestID(guest);
        String SQL = "Select Reserv_id From Reservation where Guest_ID=? and ReservationDate=?";
        SqlRowSet result = db.queryForRowSet(SQL, Guest_ID, sDate1);
        SQL = "Update Reservation set Check_in = ? where Guest_ID=? and ReservationDate=?";
        db.update(SQL, date, Guest_ID, sDate1);
        String id = "";

        if (result.next()) {
            id = result.getString("Reserv_id");
        }
        return new ResponseEntity<>("Check In Successfull", HttpStatus.OK);

    }

}
