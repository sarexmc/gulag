package es.skere.gulag.mixin;

import es.skere.gulag.RaceCamera;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void skere$tick(CallbackInfo ci) {
        RaceCamera.tick();
    }

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void skere$noItems(CallbackInfo ci) {
        if (RaceCamera.active()) {
            ci.cancel();
        }
    }

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void skere$noAttack(CallbackInfoReturnable<Boolean> cir) {
        if (RaceCamera.active()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void skere$noMining(boolean leftClick, CallbackInfo ci) {
        if (RaceCamera.active()) {
            ci.cancel();
        }
    }
}
