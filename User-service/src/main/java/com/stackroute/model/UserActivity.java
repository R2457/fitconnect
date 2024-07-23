package com.stackroute.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserActivityTable")
public class UserActivity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int activityId;
	private String slotNumber;
	private String userEmail;
	
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	private String trainerName;
	private Date bookingDate;
	
	
	
	public enum SlotStatus{
		BOOKED,
		CANCELLED
		
	}

	public UserActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserActivity(int activityId, String slotNumber, String userEmail, SlotStatus slotStatus, String trainerName,
			Date bookingDate) {
		super();
		this.activityId = activityId;
		this.slotNumber = slotNumber;
		this.userEmail = userEmail;
		this.slotStatus = slotStatus;
		this.trainerName = trainerName;
		this.bookingDate = bookingDate;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

	


		
}
