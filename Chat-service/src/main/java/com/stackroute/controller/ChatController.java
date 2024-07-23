package com.stackroute.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.model.Chat;
import com.stackroute.model.Message;
import com.stackroute.service.ChatService;

@RestController
@RequestMapping("/Chat")
public class ChatController {
	@Autowired
	ChatService chatService;

	// exception added methods
	@PostMapping("/Add")
	public ResponseEntity<?> AddChat(@RequestBody Chat chat) {
		try {
			Chat NewChat = chatService.AddChat(chat);
			if (NewChat != null) {
				return new ResponseEntity<Chat>(NewChat, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/GetChats")
	public ResponseEntity<?> GetAllChats() {
		try {
			List<Chat> AllChats = chatService.GetAllChats();
			if (AllChats.isEmpty()) {
				return new ResponseEntity<String>("Looks Like there is nothing Here", HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<Chat>>(AllChats, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Get/{Email}")
	public ResponseEntity<?> GetChatById(@PathVariable String Email) {
		try {
			Chat ChatByChatUserEmail = chatService.getChatByChatUserEmail(Email);
			if (ChatByChatUserEmail != null) {
				return new ResponseEntity<Chat>(ChatByChatUserEmail, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No Such UserFound", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{ChatUserEmail}/AddMessage")
	public ResponseEntity<?> AddAMessageInChat(@PathVariable String ChatUserEmail, @RequestBody Message msg) {
		try {
			Chat UpdatedChat = chatService.AddMessage(ChatUserEmail, msg);
			if (UpdatedChat != null) {
				return new ResponseEntity<Chat>(UpdatedChat, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("Error", HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/DeleteChat/{Email}")
	public ResponseEntity<?> DeleteAChat(@PathVariable String Email) {
		try {
			boolean DeleteChat = chatService.deleteAChat(Email);
			if (DeleteChat) {
				return new ResponseEntity<String>("User Chat Deleted Successfully ", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Seems like an Issue While attempting to Delete: " + Email,
						HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/DeleteMessage/{emailId}/{dateStamp}/{timeStamp}")
	public ResponseEntity<?> DeleteAMessage(@PathVariable String emailId, @PathVariable String dateStamp,
			@PathVariable String timeStamp) {
		try {
			Chat updatedChat = chatService.deleteMessage(emailId, dateStamp, timeStamp);
			if (updatedChat != null) {
				return new ResponseEntity<Chat>(updatedChat, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Uh! msg not Deleted", HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/UpdateMessage/{emailId}/{dateStamp}/{timeStamp}")
	public ResponseEntity<?> UpdateAMessage(@PathVariable String emailId, @PathVariable String dateStamp,
			@PathVariable String timeStamp, @RequestBody Message msg) {
		try {
			Chat updatedChat = chatService.UpdateMessage(emailId, dateStamp, timeStamp, msg);
			if (updatedChat != null) {
				return new ResponseEntity<Chat>(updatedChat, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Uh! msg not Updated", HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    @PutMapping("/UploadFile/{Email}")
	public ResponseEntity<?> UploadMedia(@PathVariable String Email, @RequestParam("file") MultipartFile file) {
		try {
			Chat updatedChat = chatService.UploadMediaFile(Email, file);
			if (updatedChat != null) {
				return new ResponseEntity<Chat>(updatedChat, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Uh! msg not Updated", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
