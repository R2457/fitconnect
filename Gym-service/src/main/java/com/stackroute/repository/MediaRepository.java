package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.MediaFile;

@Repository
public interface MediaRepository extends MongoRepository<MediaFile, String> {

}
