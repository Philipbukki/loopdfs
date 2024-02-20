package org.smunyau.loopdfs.service;

import org.smunyau.loopdfs.dto.AccountResponseDto;
import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    AccountResponseDto getAccounts(int pageNo, int pageSize, String sortBy, String sortDir);
    Account updateAccount(Long accountId, Account account);
    boolean deleteAccount(Long accountId);
    List<Card> getCardsByAccountId(Long accountId,Long clientId );
}
