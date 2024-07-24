package br.com.gustavoakira.microsells.category.exception;

import java.util.Map;

public class ValidationException extends  RuntimeException{
    public ValidationException(Map<String, String> fieldAndErrors){
        super(fieldAndErrors.toString());
    }
}
