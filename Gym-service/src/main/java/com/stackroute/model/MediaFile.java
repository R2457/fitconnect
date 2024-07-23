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
@Document(collection = "MediaCollection")
public class MediaFile {

	@Id
	@Field("mediaId")
	private String mediaId;
	private String mediaName;
	private String mediaCategory;
	private String mediaUrl;
	
    public void update(MediaFile updatedMediaFile) {
        if (updatedMediaFile.getMediaName() != null) {
            this.setMediaName(updatedMediaFile.getMediaName());
        }
        if (updatedMediaFile.getMediaCategory() != null) {
            this.setMediaCategory(updatedMediaFile.getMediaCategory());
        }
        if (updatedMediaFile.getMediaUrl() != null) {
            this.setMediaUrl(updatedMediaFile.getMediaUrl());
        }
    }
    
}
