/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Controller;

import com.Management.Hotel.Model.InvoiceData;
import com.Management.Hotel.Model.Reservation;
import com.Management.Hotel.Services.Archive;
import com.Management.Hotel.Services.ReservationService;
import com.Management.Hotel.Services.BillServices;
import com.Management.Hotel.Services.CheckInService;
import com.Management.Hotel.Services.CheckoutService;
import com.Management.Hotel.Services.RoomServices;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rohit Yadav
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/hotels")
public class HotelContoller {

    @Autowired
    private ReservationService Reservation;
    @Autowired
    private RoomServices room;
    @Autowired
    private CheckoutService checkout;
    @Autowired
    private CheckInService CheckIn;
    @Autowired
    private BillServices Bill;
    @Autowired
    private Archive arc;

    @PostMapping(value = "/reserve", consumes = "application/json", produces = "application/json")

    public ResponseEntity<?> reservation(@RequestBody Reservation reserve) {
        return Reservation.Reservation(reserve);
    }

    @PutMapping(value = "/CheckIn", consumes = "application/json", produces = "application/json")

    public ResponseEntity<?> CheckIN(@RequestBody Reservation reserve) throws Exception {
        return CheckIn.checkin(reserve);
    }

    @PutMapping(value = "/CheckOut", consumes = "application/json", produces = "application/json")

    public ResponseEntity<?> CheckOut(@RequestBody Reservation reserve) throws Exception {
        return checkout.checkin(reserve);
    }

    @GetMapping(value = "/get/{ID}")
    public ResponseEntity<?> GetRoom(@PathVariable String ID) {
        return room.getRoombyID(ID);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> GetRoom() {
        return room.getRoom();
    }

    @GetMapping(value = "/Invoice/{ID}", produces = "application/json")

    public ResponseEntity<?> Invoice(@PathVariable String ID) {
        return Bill.getInvoiceDetail(ID);
    }

    @GetMapping(value = "/Archive/{Date}", produces = "application/json")

    public ResponseEntity<?> Archive(@PathVariable String Date) {
        return arc.EnterArchive(Date);
    }

    @GetMapping(value = "/getroomdetails/{room}", produces = "application/json")

    public ResponseEntity<?> getByRoom(@PathVariable String room) throws Exception {

        
        return arc.GetByRoom(room);
    }

}
