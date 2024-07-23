package com.stackroute.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PlanCollection")
public class Plan {
	
	@Id
	@Field("planId")
	private String planId;
	private String planName;
	private Double planPrice;
	private String planDuration;
	
    public void update(Plan updatedPlan) {
        if (updatedPlan.getPlanName() != null) {
            this.setPlanName(updatedPlan.getPlanName());
        }
        if (updatedPlan.getPlanPrice() != null) {
            this.setPlanPrice(updatedPlan.getPlanPrice());
        }
        if (updatedPlan.getPlanDuration() != null) {
            this.setPlanDuration(updatedPlan.getPlanDuration());
        }
    }
    
}
