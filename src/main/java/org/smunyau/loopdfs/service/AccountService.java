package org.smunyau.loopdfs.service;

import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> getAccounts();
    Account updateAccount(Long accountId, Account account);
    boolean deleteAccount(Long accountId);
    List<Card> getCardsByAccountId(Long accountId,Long clientId );
}
