package com.mycompany.recipe.service;

import com.mycompany.recipe.dto.RecipeDto;
import com.mycompany.recipe.mapper.DataMapper;
import com.mycompany.recipe.model.Recipe;
import com.mycompany.recipe.repo.RecipeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RecipeServiceImpl class which contains implementation methods for recipes.
 *
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final DataMapper dataMapper;

    @NonNull
    private final RecipeRepository recipeRepository;

    @Override
    public List<RecipeDto> getRecipes() {
        return recipeRepository.findAll().stream()
            .map(dataMapper::mapToRecipeDto)
            .collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<RecipeDto> findRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.map(value -> new ResponseEntity<>(dataMapper.mapToRecipeDto(value), HttpStatus.OK))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        return dataMapper.mapToRecipeDto(recipeRepository.save(dataMapper.mapToRecipeModel(recipeDto)));
    }

    @Override
    public RecipeDto updateRecipe(RecipeDto recipeDto) {
        return dataMapper.mapToRecipeDto(recipeRepository.save(dataMapper.mapToRecipeModel(recipeDto)));
    }


    @Override
    public ResponseEntity<Long> deleteRecipe(Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            recipeRepository.deleteById(id);
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }


}