/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.GuestRepository;
import com.Management.Hotel.Model.AddressModel;
import com.Management.Hotel.Model.Guest;
import com.Management.Hotel.Model.InvoiceData;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rohit Yadav
 */
@Service

public class BillServices {

    @Autowired
    JdbcTemplate db;
    @Autowired
    GuestRepository gr;
    @Autowired
    private RedisTemplate red;

    public BillServices(JdbcTemplate db, GuestRepository gr, RedisTemplate red) {
        this.db = db;
        this.gr = gr;
        this.red = red;
    }

    public ResponseEntity<?> getInvoiceDetail(String ID) {
        InvoiceData invoice = this.invoiceD(ID);
        return new ResponseEntity<>(invoice, HttpStatus.OK);

    }

    public InvoiceData invoiceD(String ID) {
        String Guest = "";
        String SQL = "SELECT \n"
                + "    rv.Reserv_id,\n"
                + "    rv.Guest_id,\n"
                + "    rv.ReservationDate,\n"
                + "    rv.Check_in,\n"
                + "    rv.Check_out,\n"
                + "    rv.Room_id,\n"
                + "    Rm.Type,\n"
                + "    Rm.Charge AS PerDayCharge,\n"
                + "    (DATE(Check_out) - DATE(Check_in)) AS Days,\n"
                + "    (Rm.Charge * (DATE(Check_out) - DATE(Check_in))) AS TotalAmount,\n"
                + "    Pay.PaymnetID,\n"
                + "    Pay.Response\n"
                + "FROM\n"
                + "    Reservation rv,\n"
                + "    Room Rm,\n"
                + "    Payment Pay\n"
                + "WHERE\n"
                + "    rv.Reserv_id = ?\n"
                + "        AND rv.Room_id = Rm.Room_ID\n"
                + "        AND rv.Reserv_id = Pay.ReservID";
        List<Map<String, Object>> result = this.db.queryForList(SQL, ID);
//        List<InvoiceData> result = db.query(SQL, new Object[]{ID},(rs, rowNum) -> (new InvoiceData()));
        InvoiceData invoice = new InvoiceData();
        for (Map r : result) {
            Guest = r.get("Guest_id").toString();
            invoice.setReservationID(r.get("Reserv_id").toString());
            invoice.setRoom_Detail(r.get("Room_id").toString() + r.get("Type").toString());
            invoice.setAmount((Long) r.get("TotalAmount"));
            invoice.setDays_spent((Long) r.get("Days"));
            invoice.setPerAmount((Integer) r.get("PerDayCharge"));
            invoice.setReservationDate(r.get("ReservationDate").toString());
            invoice.setPaymentID(r.get("PaymnetID").toString());
            invoice.setCheckinDate(new Date(r.get("Check_in").toString()));
            invoice.setCheckoutDate(new Date(r.get("Check_out").toString()));
//            r.get("Rese").toString();
            if ("1".equals(r.get("Response").toString())) {
                invoice.setPaid(true);

            } else {
                invoice.setPaid(false);

            }

        }
        Optional<Guest> emps = this.gr.findById(Guest);
        if (emps.isPresent()) {
//            result.add(emps);
            invoice.setFirstName(emps.get().getFirstName());
            invoice.setLastName(emps.get().getLastName());
            invoice.setAge(emps.get().getAge());
            invoice.setContact(emps.get().getContact());
            invoice.setDocument(emps.get().getDocument());
            invoice.setDocumentNo(emps.get().getDocumentNumber());
            AddressModel address = new AddressModel(emps.get().getAddress().getAddress1(), emps.get().getAddress().getCity(), emps.get().getAddress().getState(), emps.get().getAddress().getCountry());
            invoice.setAddress(address);
            invoice.setEmail(emps.get().getEmail());

        }
        return invoice;
    }
}
