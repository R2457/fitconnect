package com.stackroute.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer>{
	
	public UserActivity findBySlotNumber(String slotNumber);
	
	public  List<UserActivity> findBySlotStatusIn(List<UserActivity.SlotStatus> slotStatusList);
	
	public List<UserActivity> findByUserEmail(String userEmail);
}
