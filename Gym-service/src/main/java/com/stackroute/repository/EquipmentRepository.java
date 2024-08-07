package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Equipment;

@Repository
public interface EquipmentRepository extends MongoRepository<Equipment, String> {

}
