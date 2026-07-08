package com.henkels.pharmalab.recipe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RecipeService {
    private final HashMap<Integer, Recipe> recipesInMemory = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(1);

    public RecipeService() {
        save(Recipe.builder().title("recipe 01").build());
        save(Recipe.builder().title("recipe 02").build());
    }

    public Recipe save(Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(id.getAndIncrement());
        }
        recipesInMemory.put(recipe.getId(), recipe);
        return recipe;
    }

    public Recipe get(int id) {
        return recipesInMemory.get(id);
    }

    public void delete(int id) {
        recipesInMemory.remove(id);
    }

    public List<Recipe> list() {
        return List.copyOf(recipesInMemory.values());
    }
}
