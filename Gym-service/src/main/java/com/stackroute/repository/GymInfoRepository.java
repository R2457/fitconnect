package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.GymInfo;

@Repository
public interface GymInfoRepository extends MongoRepository<GymInfo, String> {

}
