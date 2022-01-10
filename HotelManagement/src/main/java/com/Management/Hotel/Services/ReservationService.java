/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.GuestRepository;
import com.Management.Hotel.Model.AddressModel;
import com.Management.Hotel.Model.Guest;
import com.Management.Hotel.Model.Payment;
import com.Management.Hotel.Model.Reservation;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class ReservationService {
    @Autowired
    JdbcTemplate db;
    @Autowired
    GuestRepository gr;
    @Autowired
    private RedisTemplate red;

    Date date = new Date();

    public ResponseEntity<?> Reservation(Reservation Reserv) {
        String Adult;
        String Children;

        Guest guest = Reserv.getGuest();
        AddressModel address = guest.getAddress();

        String Guest_ID = GuestID(guest);
        String Reserv_Id = ReservID(Guest_ID);
        Boolean isAdult = guest.getAge() > 18;
        if (isAdult == true) {
            Adult = "Y";
            Children = "N";
        } else {
            Adult = "N";
            Children = "Y";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = format.format(date);
        String SQL = "Insert Into Reservation(Reserv_id,Guest_ID,Room_ID,Adult,Children,ReservationDate,Days)values(?,?,?,?,?,?,?)";
        Guest _guest = new Guest(Guest_ID,
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail(),
                guest.getContact(),
                guest.getDocument(),
                guest.getDocumentNumber(),
                guest.getAge(),
                new AddressModel(address.getAddress1(),
                        address.getCity(),
                        address.getState(),
                        address.getCountry()
                )
        );
        if (true == IfRoomAvailable(Reserv.getRoomtype())) {

            if (false == IfGuest(Guest_ID)) {
               String SSQL = "Insert Into Guest(Guest_ID, FirstName, LastName, Address, City, State, Country, Contact, Email, Document, DocumentNumber) Value(?,?,?,?,?,?,?,?,?,?,?)";
                db.update(SSQL,
                        Guest_ID,
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getAddress().getAddress1(),
                        guest.getAddress().getCity(),
                        guest.getAddress().getState(),
                        guest.getAddress().getCountry(),
                        guest.getContact(),
                        guest.getEmail(),
                        guest.getDocument(),
                        guest.getDocumentNumber());
//System.out.prinln("Added")
//            log.info("Guest Added.....");

            }
            gr.save(_guest);

            db.update(SQL, Reserv_Id, Guest_ID, Reserv.getRoomtype(), Adult, Children, currentDateTime, Reserv.getDays());
            SQL = "Select Charge from Room where Room_ID=?";
            SqlRowSet res = db.queryForRowSet(SQL, Reserv.getRoomtype());
            if (res.next()) {
                int PerAmount = res.getInt("Charge");
                int Amounts = PerAmount * Reserv.getDays();
                String InSQL = "Insert Into Payment(PaymnetID, ReservID, GuestID, PayAmount, Response) value(?,?,?,?,?)";
                db.update(InSQL, "T" + Reserv_Id + "P", Reserv_Id, Guest_ID, Amounts, true);
            }
            SQL = "update Room set Status_id='1_B' where Room_ID=?";
            db.update(SQL, Reserv.getRoomtype());
            String PayId = "T" + Reserv_Id + "P";
            Payment payment = new Payment(PayId, Reserv_Id, Guest_ID, guest.getFirstName(), guest.getLastName(), true);
            red.opsForHash().put("Payment", payment.getReservID(), payment);
            return new ResponseEntity<>("Room Reserved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Room Not Available", HttpStatus.OK);
    }

    public String ReservID(String Guest_ID) {
        Integer day = date.getDate();
        Integer month = date.getMonth();
        Integer year = date.getYear();
        Integer min = date.getMinutes();
        Integer hour = date.getHours();
        Integer sec = date.getSeconds();

        String Reserv_id = day.toString()
                + month.toString()
                + year.toString()
                + Guest_ID
                + min.toString()
                + hour.toString()
                + sec.toString();
        return Reserv_id;
    }

    public static String GuestID(Guest guest) {
        return guest.getFirstName().substring(0, 4) + guest.getContact().substring(0, 4);
    }

    public Boolean IfGuest(String Guest_id) {
        String SQL = "Select Count(Guest_ID) as Guests from Guest where Guest_ID=?";
        SqlRowSet result = db.queryForRowSet(SQL, Guest_id);
        if (result.next()) {

            if (result.getInt("Guests") == 0) {
                System.out.println("false");
                return false;
            }
        }
        System.out.println("True");
        return true;

    }

    public Boolean IfRoomAvailable(String Room_id) {
        String SQL = "Select Status_ID from Room where Room_ID=?";
        SqlRowSet result = db.queryForRowSet(SQL, Room_id);
        if (result.next()) {

            if ("1_A".equals(result.getString("Status_ID"))) {
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;

    }
}
