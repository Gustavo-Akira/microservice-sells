package br.com.gustavoakira.microsells.category.controller;

import br.com.gustavoakira.microsells.category.model.Category;
import br.com.gustavoakira.microsells.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Category category = service.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        category = service.saveCategory(category);
        return ResponseEntity.created(URI.create("/categories/"+category.getId())).body(category);
    }

    @GetMapping()
    public ResponseEntity<Page<Category>> getCategories(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("page/{page}")
    public ResponseEntity<Page<Category>> getCategoriesWithPage(@PathVariable int page){
        return ResponseEntity.ok(service.getAll(page));
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id){
        return ResponseEntity.ok(service.updateCategory(id,category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteById(id));
    }
}
