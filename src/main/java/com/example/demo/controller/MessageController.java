package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.model.Restaurant;
import com.example.demo.service.extend.IRestaurantService;
import com.example.demo.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {



    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> findAll(){
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id){
        return new ResponseEntity<>(messageService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody Message message){
        return new ResponseEntity<>(messageService.save(message),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Message> update( @RequestBody Message message){
        return new ResponseEntity<>(messageService.save(message),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        messageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
