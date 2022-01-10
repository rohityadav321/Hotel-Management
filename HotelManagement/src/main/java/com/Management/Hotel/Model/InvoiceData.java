/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

import java.util.Date;
import java.util.logging.Logger;
import org.apache.tomcat.jni.Address;

/**
 *
 * @author Rohit Yadav
 */
public class InvoiceData {

    private String ReservationID;
    private String firstName;
    private String LastName;
    private String Room_Detail;
    private Integer PerAmount;
    private Long Amount;
    private AddressModel address;
    private int Age;
    private Long Days_spent;
    private String email;
    private String Contact;
    private String Document;
    private String DocumentNo;
    private String PaymentID;
    private Boolean Paid;
    private String ReservationDate;
    private Date CheckinDate;
    private Date CheckoutDate;

    private static final Logger LOG = Logger.getLogger(InvoiceData.class.getName());

    public Date getCheckoutDate() {
        return CheckoutDate;
    }

    public void setCheckoutDate(Date CheckoutDate) {
        this.CheckoutDate = CheckoutDate;
    }

    public void setCheckinDate(Date CheckinDate) {
        this.CheckinDate = CheckinDate;
    }

    public Date getCheckinDate() {
        return CheckinDate;
    }

    public Integer getPerAmount() {
        return PerAmount;
    }

    public void setPerAmount(Integer PerAmount) {
        this.PerAmount = PerAmount;
    }

    public void setReservationID(String ReservationID) {
        this.ReservationID = ReservationID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setRoom_Detail(String Room_Detail) {
        this.Room_Detail = Room_Detail;
    }

    public void setAmount(Long Amount) {
        this.Amount = Amount;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setDays_spent(Long Days_spent) {
        this.Days_spent = Days_spent;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public void setDocument(String Document) {
        this.Document = Document;
    }

    public void setDocumentNo(String DocumentNo) {
        this.DocumentNo = DocumentNo;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public void setPaid(Boolean Paid) {
        this.Paid = Paid;
    }

    public void setReservationDate(String ReservationDate) {
        this.ReservationDate = ReservationDate;
    }

    public String getReservationID() {
        return ReservationID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getRoom_Detail() {
        return Room_Detail;
    }

    public Long getAmount() {
        return Amount;
    }

    public AddressModel getAddress() {
        return address;
    }

    public int getAge() {
        return Age;
    }

    public Long getDays_spent() {
        return Days_spent;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return Contact;
    }

    public String getDocument() {
        return Document;
    }

    public String getDocumentNo() {
        return DocumentNo;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public Boolean getPaid() {
        return Paid;
    }

    public String getReservationDate() {
        return ReservationDate;
    }

}
