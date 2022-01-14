package com.example.demo.security;

import com.example.demo.model.Card;
import com.example.demo.service.IGeneralService;

import java.util.Optional;

public interface ICardService extends IGeneralService<Card> {
    Optional<Card> findByUserId(Long id);

}
