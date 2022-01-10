/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Model;

/**
 *
 * @author Rohit Yadav
 */
public class Guest {

    private String id;
    private String firstName;
    private String lastName;
    private String Email;
    private String Contact;
    private String Document;
    private String DocumentNumber;
    private int Age;
    private AddressModel Address;

    public Guest(String id, String firstName, String lastName, String Email, String Contact, String Document, String DocumentNumber, int Age,AddressModel Address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = Email;
        this.Contact = Contact;
        this.Document = Document;
        this.DocumentNumber = DocumentNumber;
        this.Age = Age;
        this.Address = Address;
    }

    public void setAddress(AddressModel Address) {
        this.Address = Address;
    }

    public AddressModel getAddress() {
        return Address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getContact() {
        return Contact;
    }

    public String getDocument() {
        return Document;
    }

    public String getDocumentNumber() {
        return DocumentNumber;
    }

    public int getAge() {
        return Age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public void setDocument(String Document) {
        this.Document = Document;
    }

    public void setDocumentNumber(String DocumentNumber) {
        this.DocumentNumber = DocumentNumber;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

}
