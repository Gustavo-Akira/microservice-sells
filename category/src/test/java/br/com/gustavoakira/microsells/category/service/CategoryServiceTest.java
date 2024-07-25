package br.com.gustavoakira.microsells.category.service;

import br.com.gustavoakira.microsells.category.model.Category;
import br.com.gustavoakira.microsells.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
    private static  CategoryService service;
    private static CategoryRepository repository;

    @BeforeAll
    static  void setup(){
        repository = mock(CategoryRepository.class);
        service = new CategoryService(repository);
    }


    @Test
    void shouldSaveWhenCategoryIsValid(){
        Category category = new Category("test");
        Mockito.when(repository.save(category)).thenReturn(new Category(1L,"test"));
        assertNotNull(service.saveCategory(category).getId());
    }
    @Test
    void shouldDeleteCategoryWhenExists(){
        Category category = new Category(1L,"test");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(category));
        assertEquals(new Category(1L,"test"),service.deleteById(1L));
    }
    @Test
    void shouldUpdateWhenCategoryExists(){
        Category category = new Category(1L,"akira");
        Mockito.when(repository.save(category)).thenReturn(category);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Category(1L, "test")));
        assertEquals(category,service.updateCategory(1L,category));
    }
    @Test
    void shouldThrowNotFoundExceptionWhenDeleteNonExistentCategory(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,()->service.deleteById(1L));
    }
    @Test
    void shouldThrowNotFoundExceptionWhenGettingAnNonExistentCategory(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class,()->service.getCategoryById(1L));
    }
    @Test
    void shouldGetEmptyPageWhenFindAllAndDoesNotHaveAnyCategory(){
        Mockito.when(repository.findAll(Pageable.ofSize(5).withPage(0))).thenReturn(Page.empty());
        assertEquals(Page.empty(),service.getAll());
    }
    @Test
    void shouldGetPageWithElementsWhenFindAllAndHaveOneOrMoreCategories(){
        List<Category> categories = new ArrayList<>();
        for (int i=0;i < 5;i++) {
            Category category = new Category((long) i,"category "+i);
            categories.add(category);
        }
        Mockito.when(repository.findAll(Pageable.ofSize(5).withPage(1))).thenReturn(new PageImpl<>(categories));
        assertEquals(categories, service.getAll(1).toList());
    }
    @Test
    void shouldGetCategoryWhenCategoryExists(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new Category(1L, "test")));
        assertEquals(new Category(1L,"test"),service.getCategoryById(1L));
    }


}
