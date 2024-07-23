package com.stackroute.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Chat")
public class Chat {
	@Id
	private UUID chatId;

	public Chat(UUID chatId, String chatUserEmail, List<Message> chatMessage) {
		super();
		this.chatId = chatId;
		this.chatUserEmail = chatUserEmail;
		this.chatMessage = chatMessage;
	}

	public Chat() {
		this.chatId = UUID.randomUUID();
	}

	public UUID getChatId() {
		return chatId;
	}

	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

	public String getChatUserEmail() {
		return chatUserEmail;
	}

	public void setChatUserEmail(String chatUserEmail) {
		this.chatUserEmail = chatUserEmail;
	}

	public List<Message> getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(List<Message> chatMessage) {
		this.chatMessage = chatMessage;
	}

	private String chatUserEmail;
	private List<Message> chatMessage;
}
