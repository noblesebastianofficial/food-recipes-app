package com.mycompany.recipe.repo;

import com.mycompany.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RecipeRepository for all crud operations.
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
