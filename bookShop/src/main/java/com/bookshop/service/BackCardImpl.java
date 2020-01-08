package com.bookshop.service;

import com.bookshop.entities.BankCard;
import com.bookshop.entities.User;
import com.bookshop.repository.BankCardRepository;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackCardImpl implements BankCardService {

    @Autowired
    BankCardRepository bankCardRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void saveCard(User user, BankCard bankCard) {
        bankCard.setUser(user);
        bankCardRepository.save(bankCard);
    }

    @Override
    public Optional<BankCard> getAllById(Long id) {
        return bankCardRepository.findById(id);
    }

    @Override
    public void deleteBankCard(Long id) {
        bankCardRepository.deleteById(id);
     }

}
