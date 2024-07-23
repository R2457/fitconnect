package com.stackroute.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TrainerCollection")
public class Trainer {

    @Id
    @Field("trainerId")
    private String trainerId;
    private String trainerName;
    private String trainerCategory;
    private String trainerBio;
    private String trainerImage;

    public void update(Trainer updatedTrainer) {
        if (updatedTrainer.getTrainerName() != null) {
            this.setTrainerName(updatedTrainer.getTrainerName());
        }
        if (updatedTrainer.getTrainerCategory() != null) {
            this.setTrainerCategory(updatedTrainer.getTrainerCategory());
        }
        if (updatedTrainer.getTrainerBio() != null) {
            this.setTrainerBio(updatedTrainer.getTrainerBio());
        }
        if (updatedTrainer.getTrainerImage() != null) {
        	this.setTrainerImage(updatedTrainer.getTrainerImage());
        }
    }
}
