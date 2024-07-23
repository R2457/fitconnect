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
@Document(collection = "EquipmentCollection")
public class Equipment {
	
	@Id
	@Field("equipmentId") 
    private String equipmentId;
    private String equipmentName;
    private String equipmentImage;
    private String equipmentDescription;
    private Integer quantity;
    
    public void update(Equipment updatedEquipment) {
        if (updatedEquipment.getEquipmentName() != null) {
            this.setEquipmentName(updatedEquipment.getEquipmentName());
        }
        if (updatedEquipment.getEquipmentImage() != null) {
            this.setEquipmentImage(updatedEquipment.getEquipmentImage());
        }
        if (updatedEquipment.getEquipmentDescription() != null) {
            this.setEquipmentDescription(updatedEquipment.getEquipmentDescription());
        }
        if (updatedEquipment.getQuantity() != null) {
            this.setQuantity(updatedEquipment.getQuantity());
        }
    }
}
