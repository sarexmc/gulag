package es.skere.gulag.mixin;

import es.skere.gulag.RaceCamera;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow
    protected abstract void setPosition(double x, double y, double z);

    @Shadow
    protected abstract void setRotation(float yRot, float xRot);

    @Inject(method = "setup", at = @At("TAIL"))
    private void skere$camera(BlockGetter level, Entity entity, boolean detached, boolean thirdPersonReverse,
                              float partialTick, CallbackInfo ci) {
        if (!RaceCamera.active()) {
            return;
        }
        Vec3 pos = entity.getPosition(partialTick);
        this.setPosition(pos.x, pos.y + 11.5D, pos.z - 16.0D);
        this.setRotation(0.0F, 25.0F);
    }
}
