/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Management.Hotel.Interface;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.Management.Hotel.Model.InvoiceData;
/**
 *
 * @author Rohit Yadav
 */
public interface ArchiveRepository extends MongoRepository<InvoiceData , String>{
    
}
