/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.ArchiveRepository;
import com.Management.Hotel.Model.InvoiceData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rohit Yadav
 */
@Service
public class Archive {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public Archive(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

//    @Autowired
    ArchiveRepository AR;

    public ResponseEntity<?> EnterArchive(String date) {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (date != null) {
            criteria.add(Criteria.where("ReservationDate").is(date));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        return new ResponseEntity<>(mongoTemplate.find(query, InvoiceData.class), HttpStatus.OK);
    }

    public ResponseEntity<?> GetByRoom(String Room) {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (Room != null) {
            criteria.add(Criteria.where("Room_Detail").regex(Room+".*", "i"));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }

        return new ResponseEntity<>(mongoTemplate.find(query, InvoiceData.class), HttpStatus.OK);
    }

}
