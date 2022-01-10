/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Management.Hotel.Interface;

import com.Management.Hotel.Model.InvoiceData;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rohit Yadav
 */
public interface ArchiveCustom {

    List<?> query(String date);

    List<?> GetByRoom(String date);

}
