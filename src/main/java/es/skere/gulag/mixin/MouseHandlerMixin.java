package es.skere.gulag.mixin;

import es.skere.gulag.RaceCamera;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    /** Vista fija: el raton no gira al jugador mientras la camara del Gulag esta activa. */
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void skere$fixedView(CallbackInfo ci) {
        if (RaceCamera.active()) {
            ci.cancel();
        }
    }
}
