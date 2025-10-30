package com.justenoughitems.fabric.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.text.Text;

import java.util.List;

public class RecipeViewerScreen extends Screen {
    private final RecipeEntry<?> recipe;
    private final Screen parent;
    private static final int RECIPE_GRID_SIZE = 3;
    private static final int SLOT_SIZE = 18;
    
    public RecipeViewerScreen(RecipeEntry<?> recipe, Screen parent) {
        super(Text.translatable("justenoughitems.recipe.title"));
        this.recipe = recipe;
        this.parent = parent;
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int panelWidth = 176;
        int panelHeight = 120;
        
        // Draw background
        context.fill(centerX - panelWidth / 2, centerY - panelHeight / 2, 
                    centerX + panelWidth / 2, centerY + panelHeight / 2, 0xC0000000);
        
        // Draw title
        context.drawText(this.textRenderer, this.title, 
                        centerX - this.textRenderer.getWidth(this.title) / 2, 
                        centerY - panelHeight / 2 + 10, 0xFFFFFF, false);
        
        // Render recipe
        renderRecipe(context, centerX, centerY);
    }
    
    private void renderRecipe(DrawContext context, int centerX, int centerY) {
        var recipeValue = recipe.value();
        
        // Get ingredients
        List<Ingredient> ingredients = recipeValue.getIngredients();
        
        // Starting position for recipe grid
        int startX = centerX - (RECIPE_GRID_SIZE * SLOT_SIZE) / 2;
        int startY = centerY - SLOT_SIZE;
        
        if (recipeValue instanceof ShapedRecipe shapedRecipe) {
            // Render shaped recipe
            int width = shapedRecipe.getWidth();
            int height = shapedRecipe.getHeight();
            
            for (int i = 0; i < ingredients.size(); i++) {
                int x = i % width;
                int y = i / width;
                
                Ingredient ingredient = ingredients.get(i);
                ItemStack[] stacks = ingredient.getMatchingStacks();
                
                if (stacks.length > 0) {
                    int slotX = startX + x * SLOT_SIZE;
                    int slotY = startY + y * SLOT_SIZE;
                    
                    // Draw slot background
                    context.fill(slotX, slotY, slotX + 16, slotY + 16, 0xFF8B8B8B);
                    
                    // Cycle through matching stacks
                    ItemStack stack = stacks[(int)((System.currentTimeMillis() / 1000) % stacks.length)];
                    context.drawItem(stack, slotX, slotY);
                    context.drawItemInSlot(this.textRenderer, stack, slotX, slotY);
                }
            }
        } else if (recipeValue instanceof ShapelessRecipe) {
            // Render shapeless recipe in a grid
            for (int i = 0; i < ingredients.size() && i < 9; i++) {
                int x = i % RECIPE_GRID_SIZE;
                int y = i / RECIPE_GRID_SIZE;
                
                Ingredient ingredient = ingredients.get(i);
                ItemStack[] stacks = ingredient.getMatchingStacks();
                
                if (stacks.length > 0) {
                    int slotX = startX + x * SLOT_SIZE;
                    int slotY = startY + y * SLOT_SIZE;
                    
                    // Draw slot background
                    context.fill(slotX, slotY, slotX + 16, slotY + 16, 0xFF8B8B8B);
                    
                    ItemStack stack = stacks[(int)((System.currentTimeMillis() / 1000) % stacks.length)];
                    context.drawItem(stack, slotX, slotY);
                    context.drawItemInSlot(this.textRenderer, stack, slotX, slotY);
                }
            }
        }
        
        // Draw arrow
        int arrowX = centerX + SLOT_SIZE * 2;
        int arrowY = centerY - 5;
        context.drawText(this.textRenderer, "=>", arrowX, arrowY, 0xFFFFFF, false);
        
        // Draw output
        ItemStack output = recipeValue.getResult(client.world.getRegistryManager());
        int outputX = centerX + SLOT_SIZE * 3;
        int outputY = centerY - SLOT_SIZE / 2;
        
        context.fill(outputX, outputY, outputX + 16, outputY + 16, 0xFF8B8B8B);
        context.drawItem(output, outputX, outputY);
        context.drawItemInSlot(this.textRenderer, output, outputX, outputY);
    }
    
    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(parent);
        }
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
