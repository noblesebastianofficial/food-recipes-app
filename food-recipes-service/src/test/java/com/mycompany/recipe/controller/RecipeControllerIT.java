package com.mycompany.recipe.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mycompany.recipe.BaseIntegrationIT;
import com.mycompany.recipe.dto.IngredientDto;
import com.mycompany.recipe.dto.RecipeDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class RecipeControllerIT extends BaseIntegrationIT {
    private static final String API_ROOT = "/api/recipes";

    @Test
    void getAllRecipes_findRecipes_isSuccessful() throws Exception{
        mockMvc.perform(get(API_ROOT )
            .accept(APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void getRecipeById_findRecipes_isNotFound() throws Exception{
        mockMvc.perform(get(API_ROOT+"/1" )
            .accept(APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    @Test
    void getRecipeById_findRecipes_isSuccessful() throws Exception{
        // Given
        RecipeDto recipeResponseDto = createRecipeDto();
        // When
        mockMvc.perform(get(API_ROOT+"/"+recipeResponseDto.getId() )
            .accept(APPLICATION_JSON))
        //Then
            .andExpect(status().isOk());
    }

    @Test
    void deleteRecipeById_deleteRecipe_isNotFound() throws Exception{
        mockMvc.perform(delete(API_ROOT+"/1" )
            .accept(APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteRecipeById_deleteRecipe_isOk() throws Exception{
        // Given
        RecipeDto recipeResponseDto = createRecipeDto();
        // When
        mockMvc.perform(delete(API_ROOT+"/"+recipeResponseDto.getId() )
            .accept(APPLICATION_JSON))
        //Then
            .andExpect(status().isOk());
    }

    @Test
    void createRecipeById_createRecipe_isBadRequest() throws Exception{
        mockMvc.perform(post(API_ROOT )
            .accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void createRecipeById_createRecipe_isCreated() throws Exception{
        // Given
        RecipeDto recipeDto = getRecipeDto();
        // When
        mockMvc.perform(post(API_ROOT )
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(recipeDto)))
        //Then
            .andExpect(status().isCreated());
    }



    @Test
    void updateRecipeById_updateRecipe_isBadRequest() throws Exception{
        mockMvc.perform(put(API_ROOT )
            .accept(APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void updateRecipeById_updateRecipe_isOk() throws Exception{
        // Given
        RecipeDto recipeResponseDto = createRecipeDto();
        recipeResponseDto.setIsVeg(false);
        // When
        mockMvc.perform(put(API_ROOT )
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(recipeResponseDto)))
        //Then
            .andExpect(status().isOk());
    }

    private RecipeDto createRecipeDto() {
        RecipeDto recipeDto = getRecipeDto();
        return recipeService.createRecipe(recipeDto);
    }

    private RecipeDto getRecipeDto() {
        List<IngredientDto> ingredientDtoList= new ArrayList<>();
        ingredientDtoList.add(IngredientDto.builder().amount(new BigDecimal("5.00")).description("beef-stuck").build());

        return RecipeDto.builder().name("Pot-roast beef").isVeg(false).servings("2").
            cookingInstructions("marinated beef").ingredients(ingredientDtoList).build();
    }


}
