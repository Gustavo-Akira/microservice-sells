package br.com.gustavoakira.microsells.category.model;

import br.com.gustavoakira.microsells.category.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {
    @Test
    void shouldThrowValidationExceptionWhenConstructCategoryWithInvalidName(){
        assertThrows(ValidationException.class,()-> new Category(""));
        assertThrows(ValidationException.class,()-> new Category(null));
        assertThrows(ValidationException.class,()-> new Category("ari"));
        assertThrows(ValidationException.class, ()->new Category("akierdaeopqitrea"));
    }

    @Test
    void shouldChangeNameWhenCallingSetName(){
        Category category = new Category("akira");
        category.setName("kira");
        assertEquals("kira",category.getName());
    }
}
