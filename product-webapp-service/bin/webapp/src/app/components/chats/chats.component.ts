import { Component } from '@angular/core';
import { Message } from 'src/app/models/message.model';
import { Chat } from 'src/app/models/chat.model';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.css']
})

export class ChatsComponent {
  selectedChat: Chat | null = null;
  newMessage: string = '';
  messages: Message[] = [];

  chatList: Chat[] = [
    {
      chatId: '1',
      chatUserEmail: 'user1@example.com',
      chatUserImage: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      chatMessages: [
        {
          senderEmail: 'user1@example.com',
          senderMessage: 'Hello!',
          senderMediaLinks: [],
          timeStamp: new Date()
        }
      ]
    },
    {
      chatId: '2',
      chatUserEmail: 'user2@example.com',
      chatUserImage: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCpY5LtQ47cqncKMYWucFP41NtJvXU06-tnQ&usqp=CAU',
      chatMessages: [
        {
          senderEmail: 'user2@example.com',
          senderMessage: 'Hi there!',
          senderMediaLinks: [],
          timeStamp: new Date()
        }
      ]
    },
  ];

  
  ngOnInit() {
    if (this.chatList.length > 0) {
      this.selectedChat = this.chatList[0];
      this.messages = this.chatList[0].chatMessages;
    }
  }

  selectChat(chat: Chat) {
    this.selectedChat = chat;
    this.messages = chat.chatMessages;
  }

  sendMessage() {
    if (this.selectedChat && this.newMessage) {
      const message: Message = {
        senderEmail: 'admin@example.com',
        senderMessage: this.newMessage,
        senderMediaLinks: [],
        timeStamp: new Date()
      };
      this.messages.push(message);
      this.newMessage = '';
    }
  }

  onAttachmentSelect(event: any) {
    const selectedFile = event.target.files[0];
  }
  
}

