package com.stackroute.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stackroute.exception.SlotNotFoundException;
import com.stackroute.exception.SlotOccupiedException;
import com.stackroute.model.UserActivity;
import com.stackroute.model.UserActivity.SlotStatus;
import com.stackroute.repository.UserActivityRepository;
import com.stackroute.service.UserActivityService;

@RestController
@RequestMapping("/slot")
public class UserActivityController {

	@Autowired
	private UserActivityService activityService;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	This is removed once authentication is implemented
	private final static String USEREMAIL = "username@gmail.com";
	
	@GetMapping("/home")
	public ResponseEntity<?> home(){
		String msg="User Activity is working";
		return ResponseEntity.ok(msg);
		
	}

	@PostMapping("/bookslot")
	public ResponseEntity<?> bookSlot(@RequestBody UserActivity userActivity){
		
		 try {
	            String getUrl = "http://localhost:8008/api/v1/gym-service/slots/available/" + userActivity.getSlotNumber();
	            int availableSlots = restTemplate.getForObject(getUrl, Integer.class);

	            if (availableSlots > 0) {
	                UserActivity bookedSlot = activityService.bookSlot(userActivity);
	                if (bookedSlot != null) {
	                    String putUrl = "http://localhost:8008/api/v1/gym-service/slots/booked/" + bookedSlot.getSlotNumber();

	                    restTemplate.put(putUrl, bookedSlot);

	                    return new ResponseEntity<>(bookedSlot, HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<>("Not added", HttpStatus.CONFLICT);
	                }
	            } else {
	                throw new SlotOccupiedException("Slot is occupied. Cannot proceed.");
	            }
	        } catch (SlotOccupiedException ex) {
	            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	        } catch (Exception ex) {
	            return new ResponseEntity<>("Error while booking slot: " + ex.getMessage(), HttpStatus.CONFLICT);
	        }
		}
		
		
	
	@PutMapping("/cancel/{activityId}")
	public ResponseEntity<?> cancelSlot(@PathVariable int activityId){
		 try {
		        UserActivity userActivity = activityService.cancelSlot(activityId);

		        if (userActivity != null) {

		            String putUrl = "http://localhost:8008/api/v1/gym-service/slots/cancel/" + userActivity.getSlotNumber();

		            restTemplate.put(putUrl, null);

		            return new ResponseEntity<String>("Slot cancelled Successfully", HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>("Slot not cancelled", HttpStatus.NOT_FOUND); 
		        }

		    } catch (SlotNotFoundException e) {
		        return new ResponseEntity<String>("Activity not found", HttpStatus.NOT_FOUND);
		    } catch (Exception e) {
		        return new ResponseEntity<String>("Error while cancelling slot", HttpStatus.CONFLICT);
		    }
	        
	}
	
	@PutMapping("/rescheduleSlot/{activityId}")
	public ResponseEntity<?> rescheduleSlot(@PathVariable int activityId, @RequestBody UserActivity userActivity){
		try {
			UserActivity reschedule= activityService.rescheduleSlot( activityId,userActivity);
			
			return new ResponseEntity<>(reschedule, HttpStatus.OK);
	    } catch (SlotNotFoundException e) {
	        return new ResponseEntity<String>("Activity not found", HttpStatus.NOT_FOUND);
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        return new ResponseEntity<String>("Error while rescheduling: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/slotByNumber/{slotNumber}")
	public ResponseEntity<?> findBySlotNumber(@PathVariable String slotNumber){
		try {
			UserActivity slot = activityService.findBySlotNumber(slotNumber);
			if (slot != null) {
				return new ResponseEntity<>(slot, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Cant find the slot with number", HttpStatus.CONFLICT);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error while fetching " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/user-email")
	public ResponseEntity<List<UserActivity>> getUserActivityListByUserEmail() {
	    try {
	        List<UserActivity> userActivityList = activityService.getUserActivityListByUserEmail(USEREMAIL);
	        
	        if (!userActivityList.isEmpty()) {
	            return new ResponseEntity<>(userActivityList, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    } catch (Exception ex) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

}
