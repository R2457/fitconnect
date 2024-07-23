package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Plan;

@Repository
public interface PlanRepository extends MongoRepository<Plan, String> {

}
