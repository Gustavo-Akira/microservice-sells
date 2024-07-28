package br.com.gustavoakira.microsells.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity notFound(NoSuchElementException exception){
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
