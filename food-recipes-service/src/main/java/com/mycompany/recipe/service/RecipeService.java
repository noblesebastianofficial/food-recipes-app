package com.mycompany.recipe.service;

import com.mycompany.recipe.dto.RecipeDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * RecipeService class which contains methods for recipes.
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface RecipeService {

    List<RecipeDto> getRecipes();

    ResponseEntity<RecipeDto> findRecipe(Long id);

    RecipeDto createRecipe(RecipeDto recipeDto);

    RecipeDto updateRecipe(RecipeDto recipeDto);

    ResponseEntity<Long> deleteRecipe(Long id);

}