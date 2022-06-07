package com.mycompany.recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseUnitTest extends BaseTest{
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RecipeService recipeService;
}
