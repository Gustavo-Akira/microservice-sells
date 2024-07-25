package br.com.gustavoakira.microsells.category.service;

import br.com.gustavoakira.microsells.category.model.Category;
import br.com.gustavoakira.microsells.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    private CategoryRepository repository;
    @Autowired
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }



    public Category saveCategory(Category category){

    }

    public Category getCategoryById(Long id){

    }

    public Page<Category> getAll(){

    }

    public Page<Category> getAll(Integer page){

    }

    public Category deleteById(Long id){

    }

    public Category updateCategory(Long id, Category category){

    }
}
