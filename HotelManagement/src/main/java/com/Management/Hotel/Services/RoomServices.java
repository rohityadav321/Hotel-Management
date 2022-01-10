/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.GuestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Rohit Yadav
 */
@Service
public class RoomServices {

    @Autowired
    JdbcTemplate db;
    @Autowired
    GuestRepository gr;


    public ResponseEntity<?> getRoom() {
        String SQL = "Select * from Room where Status_ID='1_A'";
        List result = db.queryForList(SQL);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> getRoombyID(@PathVariable String Id) {
        String SQL = "Select * from Room where Room_ID=?";
        List result = db.queryForList(SQL,Id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
