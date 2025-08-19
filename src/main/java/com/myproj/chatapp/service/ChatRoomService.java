package com.myproj.chatapp.service;

import com.myproj.chatapp.model.ChatRoom;
import com.myproj.chatapp.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    //1st if checks if there is Chatroomid for that two user(sender/Receiver) id ther it uses that or else it creates one
    public Optional<String> getChatRoomId(
        String senderId,
        String receiverId,
        boolean createRoomIfNotExist
    ) {
        return chatRoomRepository
                .findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (createRoomIfNotExist) {
                        var newChatRoomId = createChatId(senderId, receiverId);
                        return Optional.of(newChatRoomId);
                    }

                    return Optional.empty();
                });
    }

    //creates unique chat id and using sender id and reciver id
    private String createChatId(String senderId, String receiverId) {
        var chatId = String.format("%s_%s", senderId, receiverId);

        ChatRoom senderReceiver = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .receiverId(receiverId)
                .build();

        ChatRoom receiverSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(receiverId)
                .receiverId(senderId)
                .build();

        chatRoomRepository.save(senderReceiver);
        chatRoomRepository.save(receiverSender);

        return chatId;
    }
}
