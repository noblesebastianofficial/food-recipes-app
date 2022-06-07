package com.mycompany.recipe.contoller;

import com.mycompany.recipe.dto.RecipeDto;
import com.mycompany.recipe.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RecipeController class which contains all the rest api methods for recipes.
 *
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/recipes")
@Api(value = "Food Recipes API. Set of endpoints for retrieve the recipes")
@Slf4j
@RequiredArgsConstructor
public class RecipeController {

    @NonNull
    private final RecipeService recipeService;

    /**
     * Retrieves all recipes in food recipe application.
     *
     * @return ResponseEntity returns all the recipes
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves all recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getRecipes());
    }

    /**
     * Retrieves recipes for the given recipe id.
     *
     * @param id recipe id
     * @return ResponseEntity returns the recipe.
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieves recipe by given recipe id")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("id") Long id) {
        return recipeService.findRecipe(id);

    }

    /**
     * Create new recipe.
     *
     * @param recipeDto details to populate recipe
     * @return ResponseEntity returns created recipe
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new recipe")
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        return new ResponseEntity<>(recipeService.createRecipe(recipeDto), HttpStatus.CREATED);
    }

    /**
     * Update the given recipe.
     *
     * @param recipeDto details to update recipe
     * @return ResponseEntity returns updated recipe
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates the given recipe")
    public ResponseEntity<RecipeDto> updateRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok(recipeService.updateRecipe(recipeDto));
    }

    /**
     * Deletes given recipe.
     *
     * @param id recipe id to delete
     * @return ResponseEntity returns the deleted recipe id
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deletes the given recipe")
    public ResponseEntity<Long> deleteRecipe(@PathVariable("id") Long id) {
        return recipeService.deleteRecipe(id);
    }

}
