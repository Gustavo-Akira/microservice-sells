package br.com.gustavoakira.microsells.category.model;

import br.com.gustavoakira.microsells.category.exception.ValidationException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    public Category(String name){

        this.name = name;
        validate();
    }

    public Category(Long id,String name){
        this.id = id;
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
