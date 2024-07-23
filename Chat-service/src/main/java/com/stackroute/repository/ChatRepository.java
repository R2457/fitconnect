package com.stackroute.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Chat;

@Repository
public interface ChatRepository extends MongoRepository<Chat, UUID> {
	
	List<Chat> findByChatUserEmail(String chatUserEmail);
	void deleteByChatUserEmail (String chatUserEmail);
}
