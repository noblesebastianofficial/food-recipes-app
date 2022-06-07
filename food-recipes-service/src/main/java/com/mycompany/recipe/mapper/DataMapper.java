package com.mycompany.recipe.mapper;

import com.mycompany.recipe.dto.RecipeDto;
import com.mycompany.recipe.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DataMapper {

    RecipeDto mapToRecipeDto(Recipe recipe);

    Recipe mapToRecipeModel(RecipeDto recipeDto);

}
