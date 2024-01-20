package org.smunyau.loopdfs.service;

import org.smunyau.loopdfs.entity.Card;

import java.util.List;

public interface CardService {

    Card createCard(Card card, Long accountId);

    List<Card> getCards();
    Card updateCard(Long cardId, Card card);
    boolean deleteCard(Long cardId);

}
