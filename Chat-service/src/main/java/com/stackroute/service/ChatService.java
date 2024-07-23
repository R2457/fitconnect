package com.stackroute.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.stackroute.model.Chat;
import com.stackroute.model.Message;
import com.stackroute.repository.ChatRepository;

@Service
public class ChatService {
	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private Cloudinary cloudinary;

	public Chat AddChat(Chat chat) {
		try {
			List<Chat> createdChat = chatRepository.findByChatUserEmail(chat.getChatUserEmail());
			if (createdChat.isEmpty()) {
				chatRepository.save(chat);
			} else {
				List<Message> existingMessage = createdChat.get(0).getChatMessage();
				List<Message> newMessage = chat.getChatMessage();

				if (existingMessage == null) {
					existingMessage = newMessage;
				} else {
					existingMessage.addAll(newMessage);
				}

				Chat existingChat = createdChat.get(0);
				existingChat.setChatMessage(existingMessage);
				chatRepository.save(existingChat);
			}

			return chatRepository.findByChatUserEmail(chat.getChatUserEmail()).get(0);
		} catch (Exception e) {
			throw new RuntimeException("Error while adding a chat: " + e.getMessage(), e);
		}
	}

	public Chat AddMessage(String ChatUserEmail, Message msg) {
		try {
			Chat existingChat = null;
			List<Chat> chat = chatRepository.findByChatUserEmail(ChatUserEmail);

			if (!chat.isEmpty()) {
				existingChat = chat.get(0);

				if (existingChat.getChatMessage().isEmpty()) {
					existingChat.getChatMessage().add(0, msg);
				} else {
					existingChat.getChatMessage().add(msg);
				}
			}

			return chatRepository.save(existingChat);
		} catch (Exception e) {
			throw new RuntimeException("Error while adding a message: " + e.getMessage(), e);
		}
	}

	public List<Chat> GetAllChats() {
		try {
			return chatRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving all chats: " + e.getMessage(), e);
		}
	}

	public Chat getChatByChatUserEmail(String Email) {
		try {
			List<Chat> ChatByEmail = chatRepository.findByChatUserEmail(Email);
			Chat NewChat = null;

			if (!ChatByEmail.isEmpty()) {
				NewChat = ChatByEmail.get(0);
			}

			return NewChat;
		} catch (Exception e) {
			throw new RuntimeException("Error while retrieving chat by email: " + e.getMessage(), e);
		}
	}

	public Chat deleteMessage(String emailId, String day, String time) {
		try {
			List<Chat> chatList = chatRepository.findByChatUserEmail(emailId);

			if (!chatList.isEmpty()) {
				Chat existingChat = chatList.get(0);
				List<Message> existingChatMessages = existingChat.getChatMessage();
				List<Message> messagesToRemove = new ArrayList<>();

				LocalDate targetDate = LocalDate.parse(day);
				LocalTime targetTime = LocalTime.parse(time);

				for (Message message : existingChatMessages) {
					if (message.getDateStamp().isEqual(targetDate) && message.getTimeStamp().equals(targetTime)) {
						messagesToRemove.add(message);
					}
				}

				if (!messagesToRemove.isEmpty()) {
					existingChatMessages.removeAll(messagesToRemove);
					chatRepository.save(existingChat);
				}
			}

			return chatRepository.findByChatUserEmail(emailId).stream().findFirst().orElse(null);
		} catch (Exception e) {
			throw new RuntimeException("Error while deleting a message: " + e.getMessage(), e);
		}
	}

	public Chat UpdateMessage(String emailId, String day, String time, Message msg) {
		try {
			List<Chat> chatList = chatRepository.findByChatUserEmail(emailId);

			if (!chatList.isEmpty()) {
				Chat existingChat = chatList.get(0);
				List<Message> existingChatMessages = existingChat.getChatMessage();
				List<Message> messagesToUpdate = new ArrayList<>();
				List<Message> messageToRemove = new ArrayList<>();

				LocalDate targetDate = LocalDate.parse(day);
				LocalTime targetTime = LocalTime.parse(time);

				for (Message message : existingChatMessages) {
					if (message.getDateStamp().isEqual(targetDate) && message.getTimeStamp().equals(targetTime)) {
						msg.setTimeStamp(targetTime);
						msg.setDateStamp(targetDate);
						messageToRemove.add(message);
						messagesToUpdate.add(msg);
					}
				}

				if (!messagesToUpdate.isEmpty() && !messageToRemove.isEmpty()) {
					existingChatMessages.removeAll(messageToRemove);
					existingChatMessages.addAll(messagesToUpdate);
					chatRepository.save(existingChat);
				}
			}

			return chatRepository.findByChatUserEmail(emailId).stream().findFirst().orElse(null);
		} catch (Exception e) {
			throw new RuntimeException("Error while updating a message: " + e.getMessage(), e);
		}
	}

	public boolean deleteAChat(String Email) {
		try {
			List<Chat> existingChat = chatRepository.findByChatUserEmail(Email);

			if (!existingChat.isEmpty()) {
				chatRepository.deleteByChatUserEmail(Email);
				return true;
			}

			return false;
		} catch (Exception e) {
			throw new RuntimeException("Error while deleting a chat: " + e.getMessage(), e);
		}
	}

	public Chat UploadMediaFile(String email, MultipartFile file) throws IOException {
		
		Map<String, String> uploadMedia = cloudinary.uploader().upload(file.getBytes(), Map.of());
		String url = uploadMedia.get("url");
		Chat existingChat = null;
		List<Chat> chat = chatRepository.findByChatUserEmail(email);
		List<String>Links = new ArrayList<>();
		Links.add(url);
		Message msg = new Message();
		msg.setSenderMediaLinks(Links);
		try {
			if (!chat.isEmpty()) {
				existingChat = chat.get(0);

				if (existingChat.getChatMessage().isEmpty()) {
					existingChat.getChatMessage().add(0, msg);
				} else {
					msg.setSenderEmail(existingChat.getChatMessage().get(0).getSenderEmail());
					existingChat.getChatMessage().add(msg);
				}
			}

			return chatRepository.save(existingChat);
		} catch (Exception e) {
			throw new RuntimeException("Error while Uploading File"+e.getMessage(),e);
		}
	}

}
