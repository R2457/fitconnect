import { Message } from './message.model';

export class Chat {
    chatId: string;
    chatUserEmail: string;
    chatUserImage: string;
    chatMessages: Message[];

    constructor(chatId: string, chatUserEmail: string, chatUserImage: string, chatMessages: Message[]) {
        this.chatId = chatId;
        this.chatUserEmail = chatUserEmail;
        this.chatMessages = chatMessages;
        this.chatUserImage = chatUserImage;
    }
}
