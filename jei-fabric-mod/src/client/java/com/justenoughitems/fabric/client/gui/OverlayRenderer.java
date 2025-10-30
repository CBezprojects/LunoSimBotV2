package com.justenoughitems.fabric.client.gui;

import com.justenoughitems.fabric.client.JustEnoughItemsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class OverlayRenderer {
    private boolean visible = true;
    private String searchQuery = "";
    private List<ItemStack> displayedItems;
    private int scrollOffset = 0;
    private static final int ITEMS_PER_ROW = 9;
    private static final int ROWS_VISIBLE = 8;
    private static final int ITEM_SIZE = 18;
    private static final int PADDING = 5;
    
    public OverlayRenderer() {
        this.displayedItems = new ArrayList<>();
    }
    
    public void render(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Only render on inventory screens
        if (!visible || !(client.currentScreen instanceof HandledScreen)) {
            return;
        }
        
        if (client.world == null) {
            return;
        }
        
        // Render overlay panel
        renderItemGrid(context, client);
        renderSearchBar(context, client);
    }
    
    private void renderItemGrid(DrawContext context, MinecraftClient client) {
        Screen screen = client.currentScreen;
        if (screen == null) return;
        
        // Position on the right side of the screen
        int screenWidth = screen.width;
        int screenHeight = screen.height;
        int panelWidth = ITEMS_PER_ROW * ITEM_SIZE + PADDING * 2;
        int panelHeight = ROWS_VISIBLE * ITEM_SIZE + PADDING * 2 + 20; // +20 for search bar
        
        int x = screenWidth - panelWidth - 10;
        int y = 10;
        
        // Draw background
        context.fill(x, y, x + panelWidth, y + panelHeight, 0xC0000000);
        
        // Get items to display
        if (displayedItems.isEmpty()) {
            displayedItems = JustEnoughItemsClient.getRecipeManager().searchItems(searchQuery);
        }
        
        // Render items
        int itemsPerPage = ITEMS_PER_ROW * ROWS_VISIBLE;
        int startIndex = scrollOffset * ITEMS_PER_ROW;
        int endIndex = Math.min(startIndex + itemsPerPage, displayedItems.size());
        
        for (int i = startIndex; i < endIndex; i++) {
            int localIndex = i - startIndex;
            int col = localIndex % ITEMS_PER_ROW;
            int row = localIndex / ITEMS_PER_ROW;
            
            int itemX = x + PADDING + col * ITEM_SIZE;
            int itemY = y + PADDING + 20 + row * ITEM_SIZE; // +20 for search bar space
            
            ItemStack stack = displayedItems.get(i);
            context.drawItem(stack, itemX, itemY);
            context.drawItemInSlot(client.textRenderer, stack, itemX, itemY);
            
            // Check if mouse is hovering over this item
            if (client.mouse.getX() >= itemX && client.mouse.getX() < itemX + ITEM_SIZE &&
                client.mouse.getY() >= itemY && client.mouse.getY() < itemY + ITEM_SIZE) {
                context.fill(itemX, itemY, itemX + ITEM_SIZE, itemY + ITEM_SIZE, 0x80FFFFFF);
            }
        }
    }
    
    private void renderSearchBar(DrawContext context, MinecraftClient client) {
        Screen screen = client.currentScreen;
        if (screen == null) return;
        
        int screenWidth = screen.width;
        int panelWidth = ITEMS_PER_ROW * ITEM_SIZE + PADDING * 2;
        
        int x = screenWidth - panelWidth - 10;
        int y = 10 + PADDING;
        
        // Draw search bar background
        context.fill(x + PADDING, y, x + panelWidth - PADDING, y + 15, 0xFF444444);
        
        // Draw search text
        String displayText = searchQuery.isEmpty() ? "Search..." : searchQuery;
        context.drawText(client.textRenderer, displayText, x + PADDING + 2, y + 3, 
            searchQuery.isEmpty() ? 0x888888 : 0xFFFFFF, false);
    }
    
    public void tick(MinecraftClient client) {
        // Update search results if needed
        if (client.currentScreen instanceof HandledScreen) {
            // Refresh displayed items periodically
        }
    }
    
    public void toggleVisibility() {
        visible = !visible;
    }
    
    public void updateSearch(String query) {
        this.searchQuery = query;
        this.scrollOffset = 0;
        this.displayedItems = JustEnoughItemsClient.getRecipeManager().searchItems(query);
    }
    
    public void showRecipeForHoveredItem() {
        ItemStack hoveredItem = getHoveredItem();
        if (hoveredItem != null && !hoveredItem.isEmpty()) {
            MinecraftClient client = MinecraftClient.getInstance();
            var recipes = JustEnoughItemsClient.getRecipeManager().getRecipesForItem(hoveredItem.getItem());
            
            if (!recipes.isEmpty()) {
                client.player.sendMessage(Text.literal("Found " + recipes.size() + " recipe(s) for " + 
                    hoveredItem.getName().getString()), false);
                // TODO: Open recipe viewing GUI
            }
        }
    }
    
    public void showUsageForHoveredItem() {
        ItemStack hoveredItem = getHoveredItem();
        if (hoveredItem != null && !hoveredItem.isEmpty()) {
            MinecraftClient client = MinecraftClient.getInstance();
            var recipes = JustEnoughItemsClient.getRecipeManager().getRecipesUsingItem(hoveredItem.getItem());
            
            if (!recipes.isEmpty()) {
                client.player.sendMessage(Text.literal("Found " + recipes.size() + " usage(s) for " + 
                    hoveredItem.getName().getString()), false);
                // TODO: Open usage viewing GUI
            }
        }
    }
    
    private ItemStack getHoveredItem() {
        MinecraftClient client = MinecraftClient.getInstance();
        // Check if hovering over an item in our overlay
        // This is a simplified version - you'd want more precise hit detection
        return ItemStack.EMPTY;
    }
    
    public void scroll(double amount) {
        scrollOffset = Math.max(0, scrollOffset + (int)amount);
    }
}
