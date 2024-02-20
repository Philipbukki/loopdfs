package org.smunyau.loopdfs.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.smunyau.loopdfs.dto.AccountResponseDto;
import org.smunyau.loopdfs.entity.Account;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.service.AccountService;
import org.smunyau.loopdfs.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/accounts")
@Tag(
        name="CRUD REST api for Accounts",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)

public class AccountController {

    private AccountService accountService;
    @Operation(
            summary = "Create Account Endpoint",
            description = "Creates an account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )

    @PostMapping
    public ResponseEntity<Account> createAccount(Account account) {
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(accountService.createAccount(account));
    }

    @Operation(
            summary = "Get Accounts Endpoint",
            description = "Gets List of Accounts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )

    @GetMapping("")
    public ResponseEntity<AccountResponseDto> getAccounts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_FIELD, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return ResponseEntity.ok(accountService.getAccounts(pageNo,pageSize,sortBy,sortDir));

    }
    @Operation(
            summary = "Get Account Cards Endpoint",
            description = "Gets a List of Cards for a specific Account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("/{accountId}/cards")
    public ResponseEntity<List<Card>> getCardsByAccountId(@PathVariable Long accountId, @RequestParam Long clientId) {
        return ResponseEntity.ok(accountService.getCardsByAccountId(accountId, clientId));

    }
    @Operation(
            summary = "Update Account Endpoint",
            description = "Updates an existing account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(accountId, account));

    }
    @Operation(
            summary = "Delete Account Endpoint",
            description = "Deletes an existing account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        boolean isDeleted = accountService.deleteAccount(accountId);
        if (isDeleted) {
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok("Account deleted successfully");
        }
        return ResponseEntity.ok("something wrong happened");


    }
}
