package es.skere.gulag.mixin;

import es.skere.gulag.RaceCamera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    /** Flecha dorada (chevron) que marca al jugador desde la camara elevada. */
    @Inject(method = "render", at = @At("TAIL"))
    private void skere$indicator(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!RaceCamera.active()) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        double angle = Math.toRadians(25.0D);
        double vertical = -9.1D * Math.cos(angle) + 16.0D * Math.sin(angle);
        double depth = 9.1D * Math.sin(angle) + 16.0D * Math.cos(angle);
        double focal = graphics.guiHeight() / (2.0D * Math.tan(Math.toRadians(mc.options.fov().get() / 2.0D)));
        int top = (int) (graphics.guiHeight() / 2.0D - vertical * focal / depth) - 14;
        int centre = graphics.guiWidth() / 2;
        int colour = -13278; // 0xFFFFCC22
        for (int row = 0; row < 2; row++) {
            for (int step = 0; step < 5; step++) {
                int y = top + row * 7 + step;
                graphics.fill(centre - 5 + step, y, centre - 3 + step, y + 2, colour);
                graphics.fill(centre + 3 - step, y, centre + 5 - step, y + 2, colour);
            }
        }
    }
}
