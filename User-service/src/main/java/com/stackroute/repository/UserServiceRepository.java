package com.stackroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.UserService;

@Repository
public interface UserServiceRepository extends JpaRepository<UserService, String>{

}
