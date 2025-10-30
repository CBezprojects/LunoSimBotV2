package com.justenoughitems.fabric.client.mixin;

import com.justenoughitems.fabric.client.JustEnoughItemsClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {
    
    @Inject(method = "mouseScrolled(DDDD)Z", at = @At("HEAD"), cancellable = true)
    private void onMouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount, CallbackInfoReturnable<Boolean> cir) {
        // Handle scroll events for the item overlay
        if (JustEnoughItemsClient.getOverlayRenderer() != null) {
            JustEnoughItemsClient.getOverlayRenderer().scroll(-verticalAmount);
        }
    }
}
