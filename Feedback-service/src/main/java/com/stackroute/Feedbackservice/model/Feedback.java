package com.stackroute.Feedbackservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Feedback {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerName;
    private String customerMembershipPlan;
    private String feedbackTitle;
    private String feedbackDescription;
    private String feedbackRemarks;
    private int ratings;
}
