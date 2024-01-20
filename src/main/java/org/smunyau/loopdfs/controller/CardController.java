package org.smunyau.loopdfs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/cards")
@AllArgsConstructor
@Tag(
        name="CRUD REST api for Cards",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)
public class CardController {

    private CardService cardService;

    @PostMapping("")
    @Operation(
            summary = "Create Card API",
            description = "Creates a new Card"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    public ResponseEntity<Card> createCard(@RequestBody Card card, @RequestParam Long accountId){
        return new ResponseEntity<>(cardService.createCard(card, accountId), HttpStatus.CREATED);

    }
    @Operation(
            summary = "GetList Cards List Endpoint",
            description = "Gets List of  Cards"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("")
    public ResponseEntity<List<Card>> getCards(){
        return ResponseEntity.ok(cardService.getCards());
    }


    @Operation(
            summary = "Update Card Endpoint",
            description = "Updates an existing Card"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId,@RequestBody Card card){

        return  ResponseEntity.ok(cardService.updateCard(cardId,card));

    }
    @Operation(
            summary = "Delete Card Endpoint",
            description = "Deletes a Card"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @DeleteMapping("/{cardId}")
    public String deleteCard(@PathVariable Long cardId){
        boolean deleted = cardService.deleteCard(cardId);
        if(deleted){
            return "Card deleted successfully";
        }
        return "Something went wrong";

    }
}
