package com.example.demo.service.impl;

import com.example.demo.model.Card;
import com.example.demo.repository.ICardRepository;
import com.example.demo.security.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService implements ICardService {


    @Autowired
    private ICardRepository cardRepository;

    @Override
    public Optional<Card> findByUserId(Long id) {
        return cardRepository.findByUserId(id);
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Card save(Card card) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
