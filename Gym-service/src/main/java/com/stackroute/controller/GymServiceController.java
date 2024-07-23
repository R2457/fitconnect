package com.stackroute.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.model.*;
import com.stackroute.service.GymService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.stackroute.exception.DuplicateEntityException;
import com.stackroute.exception.NotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/gym-service")
@Api(value = "Gym Service API", description = "Operations related to the Gym Service")
public class GymServiceController {

	@Autowired
	private GymService gymService;

	@GetMapping("/home")
	@ApiOperation(value = "Get welcome message", notes = "Get a welcome message for the Gym Service.")
	public ResponseEntity<String> home() {
		return new ResponseEntity<String>("Welcome to the Gym Service!", HttpStatus.OK);
	}

	@GetMapping("/gym-info")
	@ApiOperation(value = "Get gym information", notes = "Get the details of the gym.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved gym information"),
			@ApiResponse(code = 404, message = "Gym information not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getGymInfo() {
		try {
			GymInfo existingGymInfo = gymService.getGymInfo();
			if (existingGymInfo != null) {
				return ResponseEntity.ok(existingGymInfo);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/gym-info")
	@ApiOperation(value = "Add gym information", notes = "Add details of the gym.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Gym information added successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 409, message = "Duplicate entity found") })
	public ResponseEntity<?> addGymInfo(@RequestBody GymInfo gymInfo) {
		try {
			GymInfo addedGymInfo = gymService.addGymInfo(gymInfo);
			if (addedGymInfo != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedGymInfo);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/gym-info")
	@ApiOperation(value = "Update gym information", notes = "Update details of the gym.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Gym information updated successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Gym information not found") })
	public ResponseEntity<?> updateGymInfo(@RequestBody GymInfo gymInfo) {
		try {
			GymInfo updatedGymInfo = gymService.updateGymInfo(gymInfo);
			if (updatedGymInfo != null) {
				return ResponseEntity.ok(updatedGymInfo);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/plans")
	@ApiOperation(value = "Get all plans", notes = "Retrieve a list of all available plans.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of plans retrieved successfully"),
			@ApiResponse(code = 204, message = "No plans found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllPlans() {
		try {
			List<Plan> planList = gymService.getAllPlans();
			if (!planList.isEmpty()) {
				return ResponseEntity.ok(planList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/equipments")
	@ApiOperation(value = "Get all equipment", notes = "Retrieve a list of all available equipment.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of equipment retrieved successfully"),
			@ApiResponse(code = 204, message = "No equipment found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllEquipments() {
		try {
			List<Equipment> equipmentList = gymService.getAllEquipments();
			if (!equipmentList.isEmpty()) {
				return ResponseEntity.ok(equipmentList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/slots")
	@ApiOperation(value = "Get all slots", notes = "Retrieve a list of all available slots.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of slots retrieved successfully"),
			@ApiResponse(code = 204, message = "No slots found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllSlots() {
		try {
			List<Slot> slotList = gymService.getAllSlots();
			if (!slotList.isEmpty()) {
				return ResponseEntity.ok(slotList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("slots/available/{slotId}")
	@ApiOperation(value = "Get maximumLimit of by slotId", notes = "Retrieve the maximumLimit of a slot by slotId")
	public ResponseEntity<?> getMaximumLimitBySlotId(@PathVariable String slotId) {
		try {
			Integer maximumLimit = gymService.getMaximumLimit(slotId);
			if(maximumLimit != null) {
				return ResponseEntity.ok(maximumLimit);
			} else {
				throw new NotFoundException();
			}
		}  catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@PutMapping("slots/booked/{slotId}")
	@ApiOperation(value = "Update maximumLimit by slotId when booked", notes = "Update the maximumLimit of slot by slotId when ever the respective slot is booked")
	public ResponseEntity<?> updateMaximumLimitBySlotId(@PathVariable("slotId") String slotId) {
		try {
			Integer maximumLimit = gymService.updateMaximumLimitWhenBooked(slotId);
			if(maximumLimit != null) {
				return ResponseEntity.ok(maximumLimit);
			} else {
				throw new NotFoundException();
			}
			
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@PutMapping("slots/cancel/{slotId}")
	@ApiOperation(value = "Update maximumLimit by slotId when canceled", notes = "Update the maximumLimit of slot by slotId when the respective slot is canceled")
	public ResponseEntity<?> updateMaximumLimitBySlotIdWhenCanceled(@PathVariable("slotId") String slotId) {
	    try {
	        Integer maximumLimit = gymService.updateMaximumLimitWhenCanceled(slotId);
	        if (maximumLimit != null) {
	            return ResponseEntity.ok(maximumLimit);
	        } else {
	            throw new NotFoundException();
	        }
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	}
	
    @GetMapping("/slots/by-date/{slotDate}")
    @ApiOperation(value = "Get slots by slotDate", notes = "Retrieve a list of slots by slotDate")
    public ResponseEntity<List<Slot>> getSlotsBySlotDate(@PathVariable String slotDate) {
		try {
			List<Slot> slotList = gymService.getSlotsBySlotDate(slotDate);
			if (!slotList.isEmpty()) {
				return ResponseEntity.ok(slotList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
    	
    }
	
	@GetMapping("/medias")
	@ApiOperation(value = "Get all media files", notes = "Retrieve a list of all available media files.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of media files retrieved successfully"),
			@ApiResponse(code = 204, message = "No media files found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllMedias() {
		try {
			List<MediaFile> mediaList = gymService.getAllMediaFiles();
			if (!mediaList.isEmpty()) {
				return ResponseEntity.ok(mediaList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/trainers")
	@ApiOperation(value = "Get all trainers", notes = "Retrieve a list of all available trainers.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of trainers retrieved successfully"),
			@ApiResponse(code = 204, message = "No trainers found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllTrainers() {
		try {
			List<Trainer> trainerList = gymService.getAllTrainers();
			if (!trainerList.isEmpty()) {
				return ResponseEntity.ok(trainerList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@GetMapping("/trainers/{slotId}")
	@ApiOperation(value = "Get trainers by slot ID", notes = "Retrieve a list of trainers for a specific slot.")
	@ApiResponses({ @ApiResponse(code = 200, message = "List of trainers retrieved successfully"),
			@ApiResponse(code = 204, message = "No trainers found for the slot"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> getAllTrainersWithSlotId(@PathVariable("slotId") String slotId) {
		try {
			List<Trainer> trainerList = gymService.getAllTrainersBySlotId(slotId);
			if (!trainerList.isEmpty()) {
				return ResponseEntity.ok(trainerList);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/plans")
	@ApiOperation(value = "Add a plan", notes = "Create and add a new plan to the system.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Plan created successfully"),
			@ApiResponse(code = 409, message = "Duplicate plan already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> addAPlan(@RequestBody Plan plan) {
		try {
			String planId = generateUniqueId();
			plan.setPlanId(planId);
			Plan addedPlan = gymService.addPlan(plan);
			if (addedPlan != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedPlan);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/equipments")
	@ApiOperation(value = "Add an equipment", notes = "Create and add a new equipment to the system.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Equipment created successfully"),
			@ApiResponse(code = 409, message = "Duplicate equipment already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> addAnEquipment(@RequestParam("equipmentImage") MultipartFile equipmentImage,
			@RequestParam("equipmentName") String equipmentName,
			@RequestParam("equipmentDescription") String equipmentDescription,
			@RequestParam("quantity") Integer quantity) {
		try {
			String equipmentImageUrl = uploadMedia(equipmentImage);
			String equipmentId = generateUniqueId();
			Equipment equipment = new Equipment(equipmentId, equipmentName, equipmentImageUrl, equipmentDescription,
					quantity);
			Equipment addedEquipment = gymService.addEquipment(equipment);
			if (addedEquipment != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedEquipment);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/slots")
	@ApiOperation(value = "Add a slot", notes = "Create and add a new slot to the system.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Slot created successfully"),
			@ApiResponse(code = 409, message = "Duplicate slot already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> addASlot(@RequestBody Slot slot) {
		try {
			String slotId = generateUniqueId();
			slot.setSlotId(slotId);
			Slot addedSlot = gymService.addSlot(slot);
			if (addedSlot != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedSlot);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/medias")
	@ApiOperation(value = "Add a media file", notes = "Upload and add a new media file to the system.")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Media file created successfully"),
		@ApiResponse(code = 409, message = "Duplicate media file already exists"),
		@ApiResponse(code = 500, message = "Internal server error") 
	})
	public ResponseEntity<?> addAMedia(@RequestParam("media") MultipartFile media,
			@RequestParam("mediaName") String mediaName,
			@RequestParam("mediaCategory") String mediaCategory) {
		try {
			String mediaUrl = uploadMedia(media);
			String mediaId = generateUniqueId();
			MediaFile uploadedFile = new MediaFile(mediaId, mediaName, mediaCategory, mediaUrl);
			MediaFile addedMediaFile = gymService.addAMediaFile(uploadedFile);
			if (addedMediaFile != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedMediaFile);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PostMapping("/trainers")
	@ApiOperation(value = "Add a trainer", notes = "Create and add a new trainer to the system.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Trainer created successfully"),
			@ApiResponse(code = 409, message = "Duplicate trainer already exists"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> addATrainer(@RequestParam("trainerImage") MultipartFile trainerImage,
			@RequestParam("trainerName") String trainerName,
			@RequestParam("trainerCategory") String trainerCategory,
			@RequestParam("trainerBio") String trainerBio) {
		try {
			String mediaUrl = uploadMedia(trainerImage);
			String trainerId = generateUniqueId();
			Trainer trainer = new Trainer(trainerId, trainerName, trainerCategory, trainerBio, mediaUrl);
			Trainer addedTrainer = gymService.addTrainer(trainer);
			if (addedTrainer != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(addedTrainer);
			} else {
				throw new DuplicateEntityException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/plans/{planId}")
	@ApiOperation(value = "Update a plan", notes = "Update an existing plan in the system.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Plan updated successfully"),
			@ApiResponse(code = 404, message = "Plan not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> updatePlan(@PathVariable("planId") String planId, @RequestBody Plan plan) {
		try {
			Plan updatedPlan = gymService.updatePlan(planId, plan);
			if (updatedPlan != null) {
				return ResponseEntity.ok(updatedPlan);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/equipments/{equipmentId}")
	@ApiOperation(value = "Update equipment", notes = "Update an existing equipment in the system.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Equipment updated successfully"),
			@ApiResponse(code = 404, message = "Equipment not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> updateEquipment(@PathVariable("equipmentId") String equipmentId,
			@RequestParam(value = "equipmentImage", required = false ) MultipartFile equipmentImage,
			@RequestParam(value = "equipmentName", required = false) String equipmentName,
			@RequestParam(value = "equipmentDescription", required = false) String equipmentDescription,
			@RequestParam(value = "quantity", required = false) Integer quantity) {

		try {
			Equipment equipment = new Equipment();
			System.out.println(equipmentImage);
			if(equipmentImage != null && !equipmentImage.isEmpty()) {
				String mediaUrl = uploadMedia(equipmentImage);
				equipment.setEquipmentImage(mediaUrl);
			}
			if(!equipmentName.isBlank()) {
				equipment.setEquipmentName(equipmentName);
			}
			if(!equipmentDescription.isEmpty()) {
				equipment.setEquipmentDescription(equipmentDescription);
			}
			if(quantity != null) {
				equipment.setQuantity(quantity);
			}
			Equipment updatedEquipment = gymService.updateEquipment(equipmentId, equipment);
			if (updatedEquipment != null) {
				return ResponseEntity.ok(updatedEquipment);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/slots/{slotId}")
	@ApiOperation(value = "Update slot", notes = "Update an existing slot in the system.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Slot updated successfully"),
			@ApiResponse(code = 404, message = "Slot not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> updateSlot(@PathVariable("slotId") String slotId, @RequestBody Slot slot) {
		try {
			Slot updatedSlot = gymService.updateSlot(slotId, slot);
			if (updatedSlot != null) {
				return ResponseEntity.ok(updatedSlot);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/medias/{mediaId}")
	@ApiOperation(value = "Update media file", notes = "Update an existing media file in the system.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Media file updated successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Media file not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> updateMediaFile(@PathVariable("mediaId") String mediaId,
			@RequestParam(value = "media", required = false) MultipartFile media,
			@RequestParam(value = "mediaName", required = false) String mediaName,
			@RequestParam(value = "mediaCategory", required = false) String mediaCategory) {
		try {
			MediaFile mediaFile = new MediaFile();
			if(media != null && !media.isEmpty()) {
				String mediaUrl = uploadMedia(media);
				mediaFile.setMediaUrl(mediaUrl);
			}
			if(!mediaName.isEmpty()) {
				mediaFile.setMediaName(mediaName);
			}
			if(!mediaCategory.isEmpty()) {
				mediaFile.setMediaCategory(mediaCategory);
			}
			MediaFile updatedMediaFile = gymService.updateMediaFile(mediaId, mediaFile);
			if (updatedMediaFile != null) {
				return ResponseEntity.ok(updatedMediaFile);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@PutMapping("/trainers/{trainerId}")
	@ApiOperation(value = "Update trainer", notes = "Update an existing trainer in the system.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Trainer updated successfully"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Trainer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> updateTrainer(@PathVariable("trainerId") String trainerId,
			@RequestParam(value = "trainerImage", required = false) MultipartFile trainerImage,
			@RequestParam(value = "trainerName", required = false) String trainerName,
			@RequestParam(value = "trainerCategory", required = false) String trainerCategory,
			@RequestParam(value = "trainerBio", required = false) String trainerBio) {
		try {
			Trainer trainer = new Trainer();
			if(trainerImage != null && !trainerImage.isEmpty()) {
				String mediaUrl = uploadMedia(trainerImage);
				trainer.setTrainerImage(mediaUrl);
			}
			if(!trainerName.isEmpty()) {
				trainer.setTrainerName(trainerName);
			}
			if(!trainerCategory.isEmpty()) {
				trainer.setTrainerCategory(trainerCategory);
			}
			if(!trainerBio.isEmpty()) {
				trainer.setTrainerBio(trainerBio);
			}
			Trainer updatedTrainer = gymService.updateTrainer(trainerId, trainer);
			if (updatedTrainer != null) {
				return ResponseEntity.ok(updatedTrainer);
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@DeleteMapping("/plans/{planId}")
	@ApiOperation(value = "Delete plan", notes = "Delete a plan by its ID from the system.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Plan deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Plan not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> deletePlan(@PathVariable("planId") String planId) {
		try {
			boolean isDeleted = gymService.deletePlan(planId);
			if (isDeleted) {
				return ResponseEntity.noContent().build();
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@DeleteMapping("/equipments/{equipmentId}")
	@ApiOperation(value = "Delete equipment", notes = "Delete an equipment by its ID from the system.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Equipment deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Equipment not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
		try {
			boolean isDeleted = gymService.deleteEquipment(equipmentId);
			if (isDeleted) {
				return ResponseEntity.noContent().build();
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@DeleteMapping("/slots/{slotId}")
	@ApiOperation(value = "Delete slot", notes = "Delete a slot by its ID from the system.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Slot deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Slot not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> deleteSlot(@PathVariable("slotId") String slotId) {
		try {
			boolean isDeleted = gymService.deleteSlot(slotId);
			if (isDeleted) {
				return ResponseEntity.noContent().build();
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@DeleteMapping("/medias/{mediaId}")
	@ApiOperation(value = "Delete media file", notes = "Delete a media file by its ID from the system.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Media file deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Media file not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> deleteMediaFile(@PathVariable("mediaId") String mediaId) {
		try {
			boolean isDeleted = gymService.deleteMediaFile(mediaId);
			if (isDeleted) {
				return ResponseEntity.noContent().build();
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@DeleteMapping("/trainers/{trainerId}")
	@ApiOperation(value = "Delete trainer", notes = "Delete a trainer by their ID from the system.")
	@ApiResponses({ @ApiResponse(code = 204, message = "Trainer deleted successfully"),
			@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Trainer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public ResponseEntity<?> deleteTrainer(@PathVariable("trainerId") String trainerId) {
		try {
			boolean isDeleted = gymService.deleteTrainer(trainerId);
			if (isDeleted) {
				return ResponseEntity.noContent().build();
			} else {
				throw new NotFoundException();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private String uploadMedia(MultipartFile file) {
		Map<?, ?> data = gymService.uploadMediaFile(file);
		String mediaUrl = (String) data.get("url");
		return mediaUrl;
	}

	@DeleteMapping("delete-media")
	public String deleteMedia(@RequestParam("mediaUrl") String mediaUrl) {
		gymService.deleteMediaFileFromCloud(mediaUrl);

		return mediaUrl;
	}

	@ExceptionHandler(DuplicateEntityException.class)
	public ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("A duplicate entity was found.");
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested entity was not found.");
	}
	
	private String generateUniqueId() {
	    return UUID.randomUUID().toString();
	}
}
