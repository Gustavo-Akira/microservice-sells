package br.com.gustavoakira.microsells.category.exception;

import br.com.gustavoakira.microsells.category.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<ErrorResponseDTO> notFound(NoSuchElementException exception){
        ErrorResponseDTO dto = new ErrorResponseDTO(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name(), Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<ErrorResponseDTO>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorResponseDTO> notFound(ValidationException exception){
        ErrorResponseDTO dto = new ErrorResponseDTO(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(), Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<ErrorResponseDTO>(dto, HttpStatus.BAD_REQUEST);
    }
}
