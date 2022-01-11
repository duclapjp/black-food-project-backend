package com.example.demo.controller;


import com.example.demo.model.Message;
import com.example.demo.service.extend.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class WebsocketController {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    gui tin nhan tra ve theo roomID
    @MessageMapping("/messages/create/{roomId}")
    public void createMessage(@DestinationVariable String roomId, Message message) {
        messageService.save(message);
//        messagingTemplate.convertAndSend("/topic/message/" + roomId, messageService.findAll());
        messagingTemplate.convertAndSend("/topic/message/" + roomId, messageService.save(message));
    }


}
