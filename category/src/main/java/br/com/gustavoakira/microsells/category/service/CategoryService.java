package br.com.gustavoakira.microsells.category.service;

import br.com.gustavoakira.microsells.category.model.Category;
import br.com.gustavoakira.microsells.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {


    private CategoryRepository repository;
    @Autowired
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }



    public Category saveCategory(Category category){
        return  this.repository.save(category);
    }

    public Category getCategoryById(Long id){
        return  this.repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Page<Category> getAll(){
        return  this.repository.findAll(Pageable.ofSize(5).withPage(0));
    }

    public Page<Category> getAll(Integer page){
        return this.repository.findAll(Pageable.ofSize(5).withPage(page));
    }

    public Category deleteById(Long id){
        Category category = this.getCategoryById(id);
        this.repository.deleteById(id);
        return category;
    }

    public Category updateCategory(Long id, Category category){
        Category old = this.getCategoryById(id);
        old.setName(category.getName());
        return this.repository.save(old);
    }
}
