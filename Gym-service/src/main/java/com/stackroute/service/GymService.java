package com.stackroute.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.stackroute.model.Equipment;
import com.stackroute.model.GymInfo;
import com.stackroute.model.MediaFile;
import com.stackroute.model.Plan;
import com.stackroute.model.Slot;
import com.stackroute.model.Trainer;

public interface GymService {

    public GymInfo getGymInfo();
    public List<Plan> getAllPlans();
    public Plan getPlanById(String planId);
    public List<Trainer> getAllTrainers(); 
    public List<Trainer> getAllTrainersBySlotId(String slotId);
    public Trainer getTrainerById(String trainerId);
    public List<Slot> getAllSlots();
    public List<Slot> getSlotsBySlotDate(String slotDate);
    public Slot getSlotById(String slotId);
    public List<Equipment> getAllEquipments();
    public Equipment getEquipmentById(String equipmentId);
    public List<MediaFile> getAllMediaFiles();
    public MediaFile getMediaFileById(String mediaId);
    public Integer getMaximumLimit(String slotId);
    
    public GymInfo addGymInfo(GymInfo gymInfo);
    public Plan addPlan(Plan plan);
    public Equipment addEquipment(Equipment equipment);
    public Trainer addTrainer(Trainer trainer);
    public Slot addSlot(Slot slot);
    public MediaFile addAMediaFile(MediaFile mediaFile);
    
    public boolean deletePlan(String planId);
    public boolean deleteEquipment(String equipmentId);
    public boolean deleteTrainer(String trainerId);
    public boolean deleteSlot(String slotId);
    public boolean deleteMediaFile(String mediaId);

    public GymInfo updateGymInfo(GymInfo gymInfo);
    public Plan updatePlan(String planId, Plan updatedPlan);
    public Equipment updateEquipment(String equipmentId, Equipment updatedEquipment);
    public Trainer updateTrainer(String trainerId, Trainer updatedTrainer);
    public Slot updateSlot(String slotId, Slot updatedSlot);
	public MediaFile updateMediaFile(String mediaId, MediaFile updatedMediaFile);
	public Integer updateMaximumLimitWhenBooked(String slotId);
	public Integer updateMaximumLimitWhenCanceled(String slotId);
	
	public Map<String, Object> uploadMediaFile(MultipartFile mediaFile);
    public boolean deleteMediaFileFromCloud(String mediaUrl);
	
}
