/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.ArchiveRepository;
import com.Management.Hotel.Services.BillServices;
import com.Management.Hotel.Interface.GuestRepository;
import com.Management.Hotel.Model.AddressModel;
import com.Management.Hotel.Model.Guest;
import com.Management.Hotel.Model.Reservation;
import com.Management.Hotel.Model.InvoiceData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

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
public class CheckoutService {

    @Autowired
    JdbcTemplate db;
    @Autowired
    GuestRepository gr;

    @Autowired
    ArchiveRepository AR;
    BillServices bs;

    public ResponseEntity<?> checkin(Reservation reserve) throws Exception {

        String sDate1 = reserve.getReservDate();
        System.out.println(sDate1);
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Guest guest = reserve.getGuest();
        String Guest_ID = ReservationService.GuestID(guest);
        String SQL = "Select Reserv_id,Room_id From Reservation where Guest_ID=? and ReservationDate=?";
        SqlRowSet result = db.queryForRowSet(SQL, Guest_ID, sDate1);
        SQL = "Update Reservation set Check_out = ? where Guest_ID=? and ReservationDate=?";
        db.update(SQL, date, Guest_ID, sDate1);
        String id, rid;
        if (result.next()) {
            id = result.getString("Room_id");
            rid = result.getString("Reserv_id");
            try {
                System.out.println("Result is " + this.getInvoiceDetail(rid));

                AR.save(this.getInvoiceDetail(rid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQL = "update Room set Status_ID='1_A' where Room_ID=?";
            db.update(SQL, id);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public InvoiceData getInvoiceDetail(String ID) throws Exception {
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
        System.out.println(result);
        InvoiceData invoice = new InvoiceData();
        for (Map r : result) {
            Guest = r.get("Guest_id").toString();
            invoice.setReservationID(r.get("Reserv_id").toString());
            invoice.setRoom_Detail(r.get("Room_id").toString() + r.get("Type").toString());
            invoice.setAmount((Long) r.get("TotalAmount"));
            invoice.setDays_spent((Long) r.get("Days"));
            invoice.setPerAmount((Integer) r.get("PerDayCharge"));
            invoice.setPaymentID(r.get("PaymnetID").toString());
            invoice.setReservationDate(r.get("ReservationDate").toString());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String CheckInString = r.get("check_in").toString();
            Date CheckInDate = formatter.parse(CheckInString);
            invoice.setCheckinDate(CheckInDate);

            String CheckOutString = r.get("check_out").toString();
            Date CheckOutDate = formatter.parse(CheckOutString);

            invoice.setCheckoutDate(CheckOutDate);
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
