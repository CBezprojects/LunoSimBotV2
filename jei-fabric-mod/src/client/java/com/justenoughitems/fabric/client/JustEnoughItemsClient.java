package com.justenoughitems.fabric.client;

import com.justenoughitems.fabric.JustEnoughItems;
import com.justenoughitems.fabric.client.gui.OverlayRenderer;
import com.justenoughitems.fabric.client.recipe.RecipeManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class JustEnoughItemsClient implements ClientModInitializer {
    private static RecipeManager recipeManager;
    private static OverlayRenderer overlayRenderer;
    
    public static KeyBinding toggleOverlayKey;
    public static KeyBinding showRecipeKey;
    public static KeyBinding showUsageKey;
    
    @Override
    public void onInitializeClient() {
        JustEnoughItems.LOGGER.info("Just Enough Items Client initialized!");
        
        // Initialize managers
        recipeManager = new RecipeManager();
        overlayRenderer = new OverlayRenderer();
        
        // Register key bindings
        registerKeyBindings();
        
        // Register HUD renderer
        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
            overlayRenderer.render(drawContext, renderTickCounter.getTickDelta(true));
        });
        
        // Register client tick handler
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                overlayRenderer.tick(client);
            }
            
            // Handle key presses
            while (toggleOverlayKey.wasPressed()) {
                overlayRenderer.toggleVisibility();
            }
            
            while (showRecipeKey.wasPressed()) {
                overlayRenderer.showRecipeForHoveredItem();
            }
            
            while (showUsageKey.wasPressed()) {
                overlayRenderer.showUsageForHoveredItem();
            }
        });
    }
    
    private void registerKeyBindings() {
        toggleOverlayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.justenoughitems.toggle_overlay",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category.justenoughitems.main"
        ));
        
        showRecipeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.justenoughitems.show_recipe",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.justenoughitems.main"
        ));
        
        showUsageKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.justenoughitems.show_usage",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_U,
            "category.justenoughitems.main"
        ));
    }
    
    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }
    
    public static OverlayRenderer getOverlayRenderer() {
        return overlayRenderer;
    }
}
