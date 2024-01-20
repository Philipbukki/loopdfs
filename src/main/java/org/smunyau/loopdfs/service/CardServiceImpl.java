package org.smunyau.loopdfs.service;

import lombok.AllArgsConstructor;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.exception.ResourceNotFoundException;
import org.smunyau.loopdfs.repository.AccountRepository;
import org.smunyau.loopdfs.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;
    private AccountRepository accountRepository;
    @Override
    public Card createCard(Card card, Long accountId) {
        card.setAccount(accountRepository.findById(accountId).orElseThrow(
                ()->new ResourceNotFoundException("Account","id",accountId)
        ));
        return cardRepository.save(card);
    }
    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card updateCard(Long cardId, Card card) {
        Card cardToUpdate = cardRepository.findById(cardId).orElseThrow(
                ()-> new  RuntimeException("card not found with id "+ cardId)
        );

        cardToUpdate.setCardName(card.getCardName());
        cardToUpdate.setAccount(card.getAccount());
        return cardRepository.save(cardToUpdate);
    }
    @Override
    public boolean deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
        return true;
    }
}
