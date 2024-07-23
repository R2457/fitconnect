package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Trainer;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, String> {
	
}
