package org.smunyau.loopdfs.exception;

import org.smunyau.loopdfs.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizedClientException.class)
    public ResponseEntity<ErrorResponseDto> handleApplicationException(
            UnAuthorizedClientException ex, WebRequest webRequest){

        ErrorResponseDto error = new ErrorResponseDto(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),ex.getMessage()
                ,webRequest.getDescription(false));

        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponseDto error = new ErrorResponseDto(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),ex.getMessage()
                ,webRequest.getDescription(false));

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
}
