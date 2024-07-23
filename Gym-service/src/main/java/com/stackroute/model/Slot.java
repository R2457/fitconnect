package com.stackroute.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.sql.Time;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SlotCollection")
public class Slot {

    @Id
    @Field("slotId")
    private String slotId;
    private Time startTime;
    private Time endTime;
    private Integer maximumLimit;
    private String slotDate; // yyyy-MM-dd
    private List<String> trainerList;

    public void update(Slot updatedSlot) {
        if (updatedSlot.getStartTime() != null) {
            this.setStartTime(updatedSlot.getStartTime());
        }
        if (updatedSlot.getEndTime() != null) {
            this.setEndTime(updatedSlot.getEndTime());
        }
        if (updatedSlot.getMaximumLimit() != null) {
            this.setMaximumLimit(updatedSlot.getMaximumLimit());
        }
        if( updatedSlot.getSlotDate() != null) {
        	this.setSlotDate(updatedSlot.getSlotDate());
        }
        if (updatedSlot.getTrainerList() != null) {
            this.setTrainerList(updatedSlot.getTrainerList());
        }
    }
}
