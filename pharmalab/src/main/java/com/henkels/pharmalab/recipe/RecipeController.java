package com.henkels.pharmalab.recipe;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping
    public Recipe postRecipe(@RequestBody Recipe recipe) {
        return service.save(recipe);
    }

}
