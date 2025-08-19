package com.myproj.chatapp.service;

import com.myproj.chatapp.model.Message;
import com.myproj.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;

    public Message save(Message message) {
        var chatId = chatRoomService
                .getChatRoomId(message.getSenderId(), message.getReceiverId(), true)
                .orElseThrow(() -> new RuntimeException("Chat room could not be created or found"));
        message.setChatId(chatId);
        messageRepository.save(message);
        return message;
    }

    public List<Message> getChatMessage(String senderId, String receiverId) {
        var chatId = chatRoomService.getChatRoomId(senderId, receiverId, false);
        return chatId.map(messageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
//save() → Ensures a chat room exists, sets chatId on the message, and saves it.
//
//getChatMessage() → Looks for the chat and retrieves messages, or returns an empty list.
//means 1st methos id from sender ,he send and the message is saved in the message table with chatid
//2nd method is at for reciever end it searches the chatId by theire sender/reciever id and show the message right??