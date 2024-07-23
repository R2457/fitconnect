package com.stackroute.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stackroute.exception.AlreadyBookedSlotException;
import com.stackroute.exception.SlotNotFoundException;
import com.stackroute.exception.SlotOccupiedException;
import com.stackroute.model.UserActivity;
import com.stackroute.model.UserActivity.SlotStatus;
import com.stackroute.repository.UserActivityRepository;

@Service
public class UserActivityServiceImpl implements UserActivityService{

	
	@Autowired
	private UserActivityRepository activityRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public UserActivity bookSlot(UserActivity userActivity) {
		// TODO Auto-generated method stub
		UserActivity exsisting= activityRepository.findBySlotNumber(userActivity.getSlotNumber());
		if (exsisting != null) {
			throw new AlreadyBookedSlotException("The slot is already booked.");
		}else {
			return activityRepository.save(userActivity);	
		}

		
		
	}

	
	@Override
	public UserActivity cancelSlot(int activityId) {
		// TODO Auto-generated method stub
		UserActivity userActivity= activityRepository.findById(activityId)
									.orElseThrow(() -> new SlotNotFoundException("Activity with ID " + activityId + " not found"));
		
		
			userActivity.setSlotStatus(SlotStatus.CANCELLED);
			activityRepository.save(userActivity);
			return userActivity;
		
	}

	@Override
	public UserActivity rescheduleSlot( int activityId,UserActivity userActivity) {
		// TODO Auto-generated method stub
		UserActivity reschedule = activityRepository.findById(activityId)
				.orElseThrow(() -> new SlotNotFoundException("Activity with ID " + activityId + " not found"));
		
			
			if(reschedule.getSlotStatus()==SlotStatus.BOOKED) {
				
				String newSlotCheckUrl = "http://localhost:8008/api/v1/gym-service/slots/available/"
						+ userActivity.getSlotNumber();
				
				int availableSlots = restTemplate.getForObject(newSlotCheckUrl, Integer.class);
				
				
				if (availableSlots > 0) {
					String oldSlotCancelUrl = "http://localhost:8008/api/v1/gym-service/slots/cancel/"
							+ reschedule.getSlotNumber();
					
					restTemplate.put(oldSlotCancelUrl, null);

					String newSlotBookUrl = "http://localhost:8008/api/v1/gym-service/slots/booked/"
							+ userActivity.getSlotNumber();

					restTemplate.put(newSlotBookUrl, userActivity);

					reschedule.setSlotNumber(userActivity.getSlotNumber());
					reschedule.setTrainerName(userActivity.getTrainerName());
					reschedule.setBookingDate(userActivity.getBookingDate());

					return activityRepository.save(reschedule);
				} else {
					throw new IllegalArgumentException("New slot is occupied. Slot cannot be rescheduled.");
				}
			} else {
				throw new IllegalArgumentException("Slot cannot be rescheduled");
			}
			
}

	@Override
	public UserActivity findBySlotNumber(String slotNumber) {
		// TODO Auto-generated method stub
		UserActivity slot= activityRepository.findBySlotNumber(slotNumber);
		
		
		if(slot!=null) {
			return slot;
		}else {
			throw new SlotNotFoundException("Slot with slot number not found");
		}
	
	}

	@Override
	public List<UserActivity> getUserActivityBySlotStatus(List<UserActivity.SlotStatus> slotStatusList) {
	    return activityRepository.findBySlotStatusIn(slotStatusList);
		
		
	}
	
	@Override
	public List<UserActivity> getUserActivityListByUserEmail(String userEmail) {
		return activityRepository.findByUserEmail(userEmail);
	}

}
