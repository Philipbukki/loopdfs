package org.smunyau.loopdfs.exception;

import org.smunyau.loopdfs.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(
            MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            String field = error.getField();
            String fieldError = error.getDefaultMessage();
            errors.put(field,fieldError);

        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(UnAuthorizedClientException.class)
    public ResponseEntity<ErrorResponseDto> handleApplicationException(
            UnAuthorizedClientException ex, WebRequest webRequest){

        ErrorResponseDto error = ErrorResponseDto.build(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),ex.getMessage()
                ,webRequest.getDescription(false));

        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest webRequest){
        ErrorResponseDto error = ErrorResponseDto.build(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),ex.getMessage()
                ,webRequest.getDescription(false));

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
}
