package br.com.gustavoakira.microsells.category.model;

import br.com.gustavoakira.microsells.category.exception.ValidationException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Category {
    private String name;
    public Category(String name){

        this.name = name;
        validate();
    }

    public void setName(String name) {
        this.name = name;
        validate();
    }

    void validate(){
        Map<String, String> errors = new HashMap<>();
        if(name == null){
            errors.put("name","Category name cannot be null");
            throw new ValidationException(errors);
        }
        else if(name.isEmpty()){
            errors.put("name","Category name cannot be empty");
            throw new ValidationException(errors);
        }else if (name.length() < 4 || name.length() > 15){
            errors.put("name","Category name cannot be less than 4 letters or more than 15");
            throw new ValidationException(errors);
        }

    }
}
