package com.stackroute.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.stackroute.exception.DuplicateEntityException;
import com.stackroute.exception.NotFoundException;
import com.stackroute.model.Equipment;
import com.stackroute.model.GymInfo;
import com.stackroute.model.MediaFile;
import com.stackroute.model.Plan;
import com.stackroute.model.Slot;
import com.stackroute.model.Trainer;
import com.stackroute.repository.EquipmentRepository;
import com.stackroute.repository.GymInfoRepository;
import com.stackroute.repository.MediaRepository;
import com.stackroute.repository.PlanRepository;
import com.stackroute.repository.SlotRepository;
import com.stackroute.repository.TrainerRepository;

@Service
public class GymServiceImpl implements GymService {

	@Autowired
	private GymInfoRepository gymInfoRepository;
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private MediaRepository mediaRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private SlotRepository slotRepository;
	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private Cloudinary cloudinary;

	private static final String GYM_NAME = "FitConnect";

	@Override
	public GymInfo getGymInfo() {
		Optional<GymInfo> existingGymInfoOp = gymInfoRepository.findById(GYM_NAME);
		GymInfo existingGymInfo = null;
		if(existingGymInfoOp.isPresent()) {
			existingGymInfo = existingGymInfoOp.get();
		}
		return existingGymInfo;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepository.findAll();
	}

	@Override
	public Plan getPlanById(String planId) {
		Optional<Plan> existingPlanOp = planRepository.findById(planId);
		Plan existingPlan = existingPlanOp.orElseThrow(() -> new NotFoundException());
		return existingPlan;
	}

	@Override
	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	@Override
	public List<Trainer> getAllTrainersBySlotId(String slotId) {
		List<Trainer> trainerList = null;
		Slot existingSlot = getSlotById(slotId);
		if (existingSlot != null) {
			trainerList = new ArrayList<>();
			List<String> trainerIds = existingSlot.getTrainerList();
			for (String trainerId : trainerIds) {
				Trainer existingTrainer = getTrainerById(trainerId);
				if (existingTrainer != null) {
					trainerList.add(existingTrainer);
				}
			}
		}
		return trainerList;
	}

	@Override
	public Trainer getTrainerById(String trainerId) {
		Optional<Trainer> existingTrainerOp = trainerRepository.findById(trainerId);
		Trainer existingTrainer = existingTrainerOp.orElseThrow(() -> new NotFoundException());
		return existingTrainer;
	}

	@Override
	public List<Slot> getAllSlots() {
		return slotRepository.findAll();
	}

	@Override
	public List<Slot> getSlotsBySlotDate(String slotDate) {
		return slotRepository.findBySlotDate(slotDate);
	}
	
	@Override
	public Slot getSlotById(String slotId) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slotId);
		Slot existingSlot = existingSlotOp.orElseThrow(() -> new NotFoundException());
		return existingSlot;
	}

	@Override
	public List<Equipment> getAllEquipments() {
		return equipmentRepository.findAll();
	}

	@Override
	public Equipment getEquipmentById(String equipmentId) {
		Optional<Equipment> existingEquipmentOp = equipmentRepository.findById(equipmentId);
		Equipment existingEquipment = existingEquipmentOp.orElseThrow(() -> new NotFoundException());
		return existingEquipment;
	}

	@Override
	public List<MediaFile> getAllMediaFiles() {
		return mediaRepository.findAll();
	}

	@Override
	public MediaFile getMediaFileById(String mediaId) {
		Optional<MediaFile> existingMediaOp = mediaRepository.findById(mediaId);
		MediaFile existingMedia = existingMediaOp.orElseThrow(() -> new NotFoundException());
		return existingMedia;
	}
	
	@Override
	public Integer getMaximumLimit(String slotId) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slotId);
		Integer maximumLimit = null;
		if(existingSlotOp.isPresent()) {
			maximumLimit = existingSlotOp.get().getMaximumLimit();
		} else {
			throw new NotFoundException();
		}
		return maximumLimit;
	}

	@Override
	public GymInfo addGymInfo(GymInfo gymInfo) {
		GymInfo existingGymInfo = getGymInfo();
		if (existingGymInfo == null) {
			return gymInfoRepository.save(gymInfo);
		}
		throw new DuplicateEntityException();
	}

	@Override
	public Plan addPlan(Plan plan) {
		Optional<Plan> existingPlanOp = planRepository.findById(plan.getPlanId());
		if (existingPlanOp.isPresent()) {
			throw new DuplicateEntityException();
		}
		return planRepository.save(plan);
	}

	@Override
	public Equipment addEquipment(Equipment equipment) {
		Optional<Equipment> existingEquipmentOp = equipmentRepository.findById(equipment.getEquipmentId());
		if (existingEquipmentOp.isPresent()) {
			throw new DuplicateEntityException();
		}
		return equipmentRepository.save(equipment);
	}

	@Override
	public Trainer addTrainer(Trainer trainer) {
		Optional<Trainer> existingTrainerOp = trainerRepository.findById(trainer.getTrainerId());
		if (existingTrainerOp.isPresent()) {
			throw new DuplicateEntityException();
		}
		return trainerRepository.save(trainer);
	}

	@Override
	public Slot addSlot(Slot slot) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slot.getSlotId());
		if (existingSlotOp.isPresent()) {
			throw new DuplicateEntityException();
		}
		return slotRepository.save(slot);
	}

	@Override
	public MediaFile addAMediaFile(MediaFile mediaFile) {
		Optional<MediaFile> existingMediaOp = mediaRepository.findById(mediaFile.getMediaId());
		if (existingMediaOp.isPresent()) {
			throw new DuplicateEntityException();
		}
		return mediaRepository.save(mediaFile);
	}

	@Override
	public boolean deletePlan(String planId) {
		Optional<Plan> existingPlanOp = planRepository.findById(planId);
		if (existingPlanOp.isPresent()) {
			planRepository.delete(existingPlanOp.get());
			return true;
		}
		throw new NotFoundException();
	}

	@Override
	public boolean deleteEquipment(String equipmentId) {
		Optional<Equipment> existingEquipmentOp = equipmentRepository.findById(equipmentId);
		if (existingEquipmentOp.isPresent()) {
			deleteMediaFileFromCloud(existingEquipmentOp.get().getEquipmentImage());
			equipmentRepository.delete(existingEquipmentOp.get());
			return true;
		}
		throw new NotFoundException();
	}

	@Override
	public boolean deleteTrainer(String trainerId) {
		Optional<Trainer> existingTrainerOp = trainerRepository.findById(trainerId);
		if (existingTrainerOp.isPresent()) {
			deleteMediaFileFromCloud(existingTrainerOp.get().getTrainerImage());
			trainerRepository.delete(existingTrainerOp.get());
			return true;
		}
		throw new NotFoundException();
	}

	@Override
	public boolean deleteSlot(String slotId) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slotId);
		if (existingSlotOp.isPresent()) {
			slotRepository.delete(existingSlotOp.get());
			return true;
		}
		throw new NotFoundException();
	}

	@Override
	public boolean deleteMediaFile(String mediaId) {
		Optional<MediaFile> existingMediaOp = mediaRepository.findById(mediaId);
		if (existingMediaOp.isPresent()) {
			MediaFile existingMedia = existingMediaOp.get();
			boolean deletedOnCloud = deleteMediaFileFromCloud(existingMedia.getMediaUrl());
			if (deletedOnCloud) {
				mediaRepository.delete(existingMedia);
				return true;
			} else {
				throw new RuntimeException("Media Deletion Failed!!!");
			}
		}
		throw new NotFoundException();
	}

	@Override
	public GymInfo updateGymInfo(GymInfo gymInfo) {
		GymInfo existingGymInfo = getGymInfo();

		if (existingGymInfo != null) {
			existingGymInfo.update(gymInfo);
			gymInfoRepository.save(existingGymInfo);
			return existingGymInfo;
		}
		throw new NotFoundException();
	}

	@Override
	public Plan updatePlan(String planId, Plan updatedPlan) {
		Plan existingPlan = getPlanById(planId);

		if (existingPlan != null) {
			existingPlan.update(updatedPlan);
			planRepository.save(existingPlan);
			return existingPlan;
		}
		throw new NotFoundException();
	}

	@Override
	public Equipment updateEquipment(String equipmentId, Equipment updatedEquipment) {
		Equipment existingEquipment = getEquipmentById(equipmentId);

		if (existingEquipment != null) {
			if(updatedEquipment.getEquipmentImage() != null) {
				deleteMediaFileFromCloud(existingEquipment.getEquipmentImage());
			}
			existingEquipment.update(updatedEquipment);
			equipmentRepository.save(existingEquipment);
			return existingEquipment;
		}
		throw new NotFoundException();
	}

	@Override
	public Trainer updateTrainer(String trainerId, Trainer updatedTrainer) {
		Trainer existingTrainer = getTrainerById(trainerId);

		if (existingTrainer != null) {
			if(updatedTrainer.getTrainerImage() != null) {
				deleteMediaFileFromCloud(existingTrainer.getTrainerImage());
			}
			existingTrainer.update(updatedTrainer);
			trainerRepository.save(existingTrainer);
			return existingTrainer;
		}
		throw new NotFoundException();
	}

	@Override
	public Slot updateSlot(String slotId, Slot updatedSlot) {
		Slot existingSlot = getSlotById(slotId);

		if (existingSlot != null) {
			existingSlot.update(updatedSlot);
			slotRepository.save(existingSlot);
			return existingSlot;
		}
		throw new NotFoundException();
	}

	@Override
	public MediaFile updateMediaFile(String mediaId, MediaFile updatedMediaFile) {
		MediaFile existingMediaFile = getMediaFileById(mediaId);

		if (existingMediaFile != null) {
			if(updatedMediaFile.getMediaUrl() != null) {
				deleteMediaFileFromCloud(existingMediaFile.getMediaUrl());
			}
			existingMediaFile.update(updatedMediaFile);
			mediaRepository.save(existingMediaFile);
			return existingMediaFile;
		}
		throw new NotFoundException();
	}

	@Override
	public Integer updateMaximumLimitWhenBooked(String slotId) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slotId);
		Integer maximumLimit = null;
		if(existingSlotOp.isPresent()) {
			Slot slot = existingSlotOp.get();
			maximumLimit = slot.getMaximumLimit();
			if(maximumLimit != 0) {
				maximumLimit--;
				slot.setMaximumLimit(maximumLimit);
				slotRepository.save(slot);
			}
		} else {
			throw new NotFoundException();
		}
		return maximumLimit;
	}
	
	@Override
	public Integer updateMaximumLimitWhenCanceled(String slotId) {
		Optional<Slot> existingSlotOp = slotRepository.findById(slotId);
		Integer maximumLimit = null;
		if(existingSlotOp.isPresent()) {
			Slot slot = existingSlotOp.get();
			maximumLimit = slot.getMaximumLimit();
			maximumLimit++;
			slot.setMaximumLimit(maximumLimit);
			slotRepository.save(slot);

		} else {
			throw new NotFoundException();
		}
		return maximumLimit;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> uploadMediaFile(MultipartFile mediaFile) {
		Map<String, Object> data = null;
		try {
			data = this.cloudinary.uploader().upload(mediaFile.getBytes(), Map.of());
		} catch (IOException e) {
			throw new RuntimeException("Media Uploading Failed!!!");
		}
		return data;
	}

	@Override
	public boolean deleteMediaFileFromCloud(String mediaUrl) {

		String publicId = extractPublicIdFromUrl(mediaUrl);
		try {
			ApiResponse apiResponse = cloudinary.api().deleteResources(Arrays.asList(publicId),
					ObjectUtils.asMap("type", "upload", "resource_type", "image"));
			if (apiResponse.get("deleted").toString().contains("deleted")) {
				return true;
			}

		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String extractPublicIdFromUrl(String mediaUrl) {
		// Extract the public ID from the media URL
		int startIndex = mediaUrl.lastIndexOf("/") + 1;
		int endIndex = mediaUrl.lastIndexOf('.');
		if (startIndex >= 2 && endIndex >= 0 && endIndex > startIndex) {
			System.out.println(mediaUrl.substring(startIndex, endIndex));
			return mediaUrl.substring(startIndex, endIndex);
		} else {
			throw new RuntimeException("Invalid media URL: " + mediaUrl);
		}
	}





}
