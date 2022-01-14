package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.security.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cards")
public class CardController {


    @Autowired
    private ICardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> findAll(){
        return new ResponseEntity<>(cardService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findCardByUserId(@PathVariable Long id){

        Optional<Card> cardOptional = cardService.findByUserId(id);
        return new ResponseEntity<>(cardOptional.get(), HttpStatus.OK);
    }
}
