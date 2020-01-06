package com.bookshop.service;

import com.bookshop.entities.BankCard;
import com.bookshop.entities.User;

import java.util.Optional;

public interface BankCardService  {

    void saveCard(User user, BankCard bankCard);

    Optional<BankCard> getAllById(Long id);
}
