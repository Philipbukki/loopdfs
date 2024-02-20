package org.smunyau.loopdfs.service;

import lombok.AllArgsConstructor;
import org.hibernate.query.SortDirection;
import org.smunyau.loopdfs.dto.AccountResponseDto;
import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.exception.ResourceNotFoundException;
import org.smunyau.loopdfs.exception.UnAuthorizedClientException;
import org.smunyau.loopdfs.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
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
    public AccountResponseDto getAccounts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Account> accounts = accountRepository.findAll(pageable);
        AccountResponseDto responseDto = new AccountResponseDto();

        responseDto.setContent(accounts.getContent());
        responseDto.setPageNo(accounts.getNumber());
        responseDto.setPageSize(accounts.getSize());
        responseDto.setTotalElements(accounts.getTotalElements());
        responseDto.setTotalPages(accounts.getTotalPages());
        responseDto.setLast(accounts.isLast());


        return responseDto;

    }

    @Override
    public Account updateAccount(Long accountId, Account account) {

        Account accountToUpdate = accountRepository.findById(accountId).orElseThrow(
                ()-> new ResourceNotFoundException("Account","accountId",accountId)
        );

        accountToUpdate.setIban(account.getIban());
        accountToUpdate.setBicSwift(account.getBicSwift());
        return accountRepository.save(accountToUpdate);
    }
    @Override
    public boolean deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()-> new ResourceNotFoundException("Account","id",accountId)
        );
        accountRepository.delete(account);
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
