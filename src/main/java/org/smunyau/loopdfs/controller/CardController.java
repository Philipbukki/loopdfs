package org.smunyau.loopdfs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.smunyau.loopdfs.dto.ErrorResponseDto;
import org.smunyau.loopdfs.entity.Card;
import org.smunyau.loopdfs.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @ApiResponses(
        {
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATS NOT_FOUND"
            )
        }
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
    public ResponseEntity<Page<Card>> getCards(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "1") int pageSize,
            @RequestParam(required = false, defaultValue = "cardId") String sortBy,
            @RequestParam(required = false, defaultValue ="asc" ) String sortDir)
    {
        return ResponseEntity.ok(cardService.getCards(pageNo,pageSize,sortBy,sortDir));
    }


    @Operation(
            summary = "Update Card Endpoint",
            description = "Updates an existing Card"
    )

    @ApiResponses(
    {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT_FOUND",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            )
    }
    )
    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId,@RequestBody Card card){

        return  ResponseEntity.ok(cardService.updateCard(cardId,card));

    }
    @Operation(
            summary = "Delete Card Endpoint",
            description = "Deletes a Card"
    )

    @ApiResponses(
            {
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP STATUS OK"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "HTTP STATUS NOT_FOUND",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                )
            }
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
