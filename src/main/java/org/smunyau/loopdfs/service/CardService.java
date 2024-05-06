package org.smunyau.loopdfs.service;

import org.smunyau.loopdfs.entity.Card;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CardService {

    Card createCard(Card card, Long accountId);

    Page<Card> getCards(int pageNo, int paeSize, String sortBy, String sortDir);
    Card updateCard(Long cardId, Card card);
    boolean deleteCard(Long cardId);

}
