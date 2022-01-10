/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Management.Hotel.Interface;

import com.Management.Hotel.Model.Guest;
import com.Management.Hotel.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Rohit Yadav
 */
public interface GuestRepository extends MongoRepository<Guest, String> {
    
}
