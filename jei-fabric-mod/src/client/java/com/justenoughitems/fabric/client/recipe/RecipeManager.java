package com.justenoughitems.fabric.client.recipe;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.Registries;

import java.util.*;
import java.util.stream.Collectors;

public class RecipeManager {
    private List<ItemStack> allItems;
    private Map<Item, List<RecipeEntry<?>>> recipesByOutput;
    private Map<Item, List<RecipeEntry<?>>> recipesByIngredient;
    
    public RecipeManager() {
        this.allItems = new ArrayList<>();
        this.recipesByOutput = new HashMap<>();
        this.recipesByIngredient = new HashMap<>();
    }
    
    public void refresh() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return;
        }
        
        // Index all items
        allItems.clear();
        for (Item item : Registries.ITEM) {
            allItems.add(new ItemStack(item));
        }
        
        // Index recipes
        indexRecipes();
    }
    
    private void indexRecipes() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return;
        }
        
        recipesByOutput.clear();
        recipesByIngredient.clear();
        
        // Get all recipes from the recipe manager
        var recipes = client.world.getRecipeManager().values();
        
        for (RecipeEntry<?> recipe : recipes) {
            // Index by output
            ItemStack output = recipe.value().getResult(client.world.getRegistryManager());
            if (!output.isEmpty()) {
                recipesByOutput.computeIfAbsent(output.getItem(), k -> new ArrayList<>()).add(recipe);
            }
            
            // Index by ingredients
            recipe.value().getIngredients().forEach(ingredient -> {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    recipesByIngredient.computeIfAbsent(stack.getItem(), k -> new ArrayList<>()).add(recipe);
                }
            });
        }
    }
    
    public List<ItemStack> getAllItems() {
        if (allItems.isEmpty()) {
            refresh();
        }
        return allItems;
    }
    
    public List<ItemStack> searchItems(String query) {
        if (query == null || query.isEmpty()) {
            return getAllItems();
        }
        
        String lowerQuery = query.toLowerCase();
        return getAllItems().stream()
            .filter(stack -> stack.getName().getString().toLowerCase().contains(lowerQuery))
            .collect(Collectors.toList());
    }
    
    public List<RecipeEntry<?>> getRecipesForItem(Item item) {
        if (recipesByOutput.isEmpty()) {
            refresh();
        }
        return recipesByOutput.getOrDefault(item, Collections.emptyList());
    }
    
    public List<RecipeEntry<?>> getRecipesUsingItem(Item item) {
        if (recipesByIngredient.isEmpty()) {
            refresh();
        }
        return recipesByIngredient.getOrDefault(item, Collections.emptyList());
    }
}
