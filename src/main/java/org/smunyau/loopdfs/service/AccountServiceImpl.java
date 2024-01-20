package org.smunyau.loopdfs.service;

import lombok.AllArgsConstructor;
import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.exception.ResourceNotFoundException;
import org.smunyau.loopdfs.exception.UnAuthorizedClientException;
import org.smunyau.loopdfs.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    @Override
    public Account createAccount(Account account) {
        if(account.getCards() == null){
            account.setCards(new ArrayList<>());

        }
        return accountRepository.save(account);

    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {

        Account accountToUpdate = accountRepository.findById(accountId).orElseThrow(
                ()-> new ResourceNotFoundException(Account.class.getName(),"accountId",accountId)
        );

        accountToUpdate.setIban(account.getIban());
        accountToUpdate.setBicSwift(account.getBicSwift());
        return accountRepository.save(accountToUpdate);
    }
    @Override
    public boolean deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
        return true;
    }
    public List<Card> getCardsByAccountId(Long accountId, Long clientId){
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()->new ResourceNotFoundException("Account","accountId",accountId)
        );
        if(!clientId.equals(account.getClientId())){
            throw new UnAuthorizedClientException(Account.class.getName(),clientId);
        }
        return account.getCards();

    }
}
