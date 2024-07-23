package com.stackroute.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Slot;

@Repository
public interface SlotRepository extends MongoRepository<Slot, String> {

	List<Slot> findBySlotDate(String slotDate);
	
	//"yyyy-MM-dd"
}
