package br.com.gustavoakira.microsells.category.repository;

import br.com.gustavoakira.microsells.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
