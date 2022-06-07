package com.mycompany.recipe.service;

import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.recipe.BaseUnitTest;
import com.mycompany.recipe.dto.IngredientDto;
import com.mycompany.recipe.dto.RecipeDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class RecipeServiceTest extends BaseUnitTest {
    @Test
    void createRecipes_findRecipes_shouldReturnRecipesList() {
        //given
        createRecipeDto();
        //when
        List<RecipeDto> recipeDtoList=recipeService.getRecipes();
        //return
        assertThat(recipeDtoList)
            .isNotEmpty();
        assertThat(recipeDtoList.size())
            .isEqualTo(1);
        assertThat(recipeDtoList.get(0).getName())
            .isEqualTo("Pot-roast beef");
        assertThat(recipeDtoList.get(0).getServings())
            .isEqualTo("2");
        assertThat(recipeDtoList.get(0).getIsVeg())
            .isFalse();
        assertThat(recipeDtoList.get(0).getCreatedDate())
            .isNotNull();
        assertThat(recipeDtoList.get(0).getIngredients())
            .isNotEmpty();
        assertThat(recipeDtoList.get(0).getIngredients().size())
            .isEqualTo(1);
        assertThat(recipeDtoList.get(0).getIngredients().get(0).getDescription())
            .isEqualTo("beef-stuck");
        assertThat(recipeDtoList.get(0).getIngredients().get(0).getAmount())
            .isEqualTo("5.00");
        assertThat(recipeDtoList.get(0).getCookingInstructions())
            .isEqualTo("marinated beef");

    }

    @Test
    void createRecipes_findRecipeById_shouldReturnRecipe(){
        //given
        RecipeDto recipeResponseDto = createRecipeDto();
        //when
        ResponseEntity<RecipeDto> recipeDto=recipeService.findRecipe(recipeResponseDto.getId());
        //return
        assertThat(recipeDto.getBody())
            .isNotNull();

        assertThat(recipeDto.getBody().getName())
            .isEqualTo("Pot-roast beef");
        assertThat(recipeDto.getBody().getServings())
            .isEqualTo("2");
        assertThat(recipeDto.getBody().getIsVeg())
            .isFalse();
        assertThat(recipeDto.getBody().getCreatedDate())
            .isNotNull();
        assertThat(recipeDto.getBody().getIngredients())
            .isNotEmpty();
        assertThat(recipeDto.getBody().getIngredients().size())
            .isEqualTo(1);
        assertThat(recipeDto.getBody().getIngredients().get(0).getDescription())
            .isEqualTo("beef-stuck");
        assertThat(recipeDto.getBody().getIngredients().get(0).getAmount())
            .isEqualTo("5.00");
        assertThat(recipeDto.getBody().getCookingInstructions())
            .isEqualTo("marinated beef");
    }
    @Test
    void getRecipe_createRecipe_shouldReturnCreatedRecipe(){
        //given
        RecipeDto recipeDto = getRecipeDto();
        //when
        RecipeDto recipeResponseDto = recipeService.createRecipe(recipeDto);

        //return
        assertThat(recipeResponseDto)
            .isNotNull();

        assertThat(recipeResponseDto.getName())
            .isEqualTo("Pot-roast beef");
        assertThat(recipeResponseDto.getServings())
            .isEqualTo("2");
        assertThat(recipeResponseDto.getIsVeg())
            .isFalse();
        assertThat(recipeResponseDto.getCreatedDate())
            .isNotNull();
        assertThat(recipeResponseDto.getIngredients())
            .isNotEmpty();
        assertThat(recipeResponseDto.getIngredients().size())
            .isEqualTo(1);
        assertThat(recipeResponseDto.getIngredients().get(0).getDescription())
            .isEqualTo("beef-stuck");
        assertThat(recipeResponseDto.getIngredients().get(0).getAmount())
            .isEqualTo("5.00");
        assertThat(recipeResponseDto.getCookingInstructions())
            .isEqualTo("marinated beef");
    }

    @Test
    void createRecipe_updateRecipe_shouldReturnUpdatedRecipe() {
        //given
        RecipeDto recipeResponseDto = createRecipeDto();
        //when
        recipeResponseDto.setCookingInstructions("marinated hot beef");
        RecipeDto updateRecipeResponseDto = recipeService.updateRecipe(recipeResponseDto);

        //return
        assertThat(updateRecipeResponseDto)
            .isNotNull();

        assertThat(updateRecipeResponseDto.getName())
            .isEqualTo("Pot-roast beef");
        assertThat(updateRecipeResponseDto.getServings())
            .isEqualTo("2");
        assertThat(updateRecipeResponseDto.getIsVeg())
            .isFalse();
        assertThat(updateRecipeResponseDto.getCreatedDate())
            .isNotNull();
        assertThat(updateRecipeResponseDto.getIngredients())
            .isNotEmpty();
        assertThat(updateRecipeResponseDto.getIngredients().size())
            .isEqualTo(1);
        assertThat(updateRecipeResponseDto.getIngredients().get(0).getDescription())
            .isEqualTo("beef-stuck");
        assertThat(updateRecipeResponseDto.getIngredients().get(0).getAmount())
            .isEqualTo("5.00");
        assertThat(updateRecipeResponseDto.getCookingInstructions())
            .isEqualTo("marinated hot beef");
    }
    @Test
    void createRecipe_deleteRecipe_shouldReturnUpdatedRecipe() {
        //given
        RecipeDto recipeResponseDto = createRecipeDto();
        //when
        recipeResponseDto.setCookingInstructions("marinated hot beef");
        ResponseEntity<Long> id = recipeService.deleteRecipe(recipeResponseDto.getId());

        //return

        assertThat(id.getBody())
            .isEqualTo(recipeResponseDto.getId());

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
