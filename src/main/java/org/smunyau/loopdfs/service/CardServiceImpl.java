package org.smunyau.loopdfs.service;

import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.exception.ResourceNotFoundException;
import org.smunyau.loopdfs.repository.AccountRepository;
import org.smunyau.loopdfs.repository.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Card> getCards(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        return cardRepository.findAll(pageable);
    }

    @Override
    public Card updateCard(Long cardId, Card card) {
        Card cardToUpdate = cardRepository.findById(cardId).orElseThrow(
                ()-> new  ResourceNotFoundException("Card","id",cardId)
        );

        cardToUpdate.setCardName(card.getCardName());
        cardToUpdate.setAccount(card.getAccount());
        return cardRepository.save(cardToUpdate);
    }
    @Override
    public boolean deleteCard(Long cardId) {
       Card card = cardRepository.findById(cardId).orElseThrow(
               ()-> new ResourceNotFoundException("Card","id",cardId)
       );
       cardRepository.delete(card);
        return true;
    }
}
