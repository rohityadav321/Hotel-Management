/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Management.Hotel.Services;

import com.Management.Hotel.Interface.ArchiveCustom;
import com.Management.Hotel.Model.InvoiceData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Rohit Yadav
 */
public class ArchiveImpl implements ArchiveCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ArchiveImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<?> query(String day) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (day != null) {
            criteria.add(Criteria.where("ReservationDate").is(day));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, InvoiceData.class);
    }

    public List<?> GetByRoom(String Room) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (Room != null) {
            criteria.add(Criteria.where("Room_Detail").regex(Room));
        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, InvoiceData.class);
    }

}
