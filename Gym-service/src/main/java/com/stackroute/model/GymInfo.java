package com.stackroute.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "GymInfoCollection")
public class GymInfo {

	@Id
	private String gymName;
	private String gymAddress;
	private String mobile;

    public void update(GymInfo updatedGymInfo) {
        if (updatedGymInfo.getGymAddress() != null) {
            this.setGymAddress(updatedGymInfo.getGymAddress());
        }
        if (updatedGymInfo.getMobile() != null) {
            this.setMobile(updatedGymInfo.getMobile());
        }
    }
    
}
