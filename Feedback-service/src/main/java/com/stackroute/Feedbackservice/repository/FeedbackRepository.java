package com.stackroute.Feedbackservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.Feedbackservice.model.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
	
}
