package com.stackroute.service;

import java.util.List;

import com.stackroute.model.UserActivity;
import com.stackroute.model.UserActivity.SlotStatus;

public interface UserActivityService {
	  public UserActivity bookSlot(UserActivity userActivity);
	  public UserActivity cancelSlot(int activityId);
	  public UserActivity rescheduleSlot(int activityId, UserActivity userActivity);
	  public UserActivity findBySlotNumber(String slotNumber);
	  public  List<UserActivity> getUserActivityBySlotStatus(List<UserActivity.SlotStatus> slotStatusList);
	  public List<UserActivity> getUserActivityListByUserEmail(String userEmail);
}
