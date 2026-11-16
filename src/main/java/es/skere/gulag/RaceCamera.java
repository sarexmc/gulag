package es.skere.gulag;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * Estado de la camara elevada del Gulag.
 *
 * En 1.21.8 el datapack activaba la camara con el atributo camera_distance (+16).
 * Ese atributo no existe en 1.21.1, asi que el datapack ahora aplica el modificador
 * skere_gulag:race_camera (+16) al atributo minecraft:generic.luck, que el servidor
 * sincroniza con el cliente. La camara se activa con luck >= 15, solo en la
 * dimension skere_gulag:arena y nunca en modo espectador.
 */
public final class RaceCamera {
    public static final String DIMENSION = "skere_gulag:arena";
    public static final double LUCK_THRESHOLD = 15.0D;

    private static boolean previouslyActive = false;
    private static CameraType previous = CameraType.FIRST_PERSON;
    private static boolean previousBobbing;
    private static double previousFovEffect;

    private RaceCamera() {
    }

    public static boolean active() {
        Minecraft mc = Minecraft.getInstance();
        return mc.player != null
                && mc.level != null
                && !mc.player.isSpectator()
                && mc.level.dimension().location().toString().equals(DIMENSION)
                && mc.player.getAttributeValue(Attributes.LUCK) >= LUCK_THRESHOLD;
    }

    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        boolean active = active();
        if (active) {
            if (!previouslyActive) {
                previous = mc.options.getCameraType();
                previousBobbing = mc.options.bobView().get();
                previousFovEffect = mc.options.fovEffectScale().get();
            }
            mc.options.bobView().set(false);
            mc.options.fovEffectScale().set(0.0D);
            mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
            mc.player.setYRot(0.0F);
            mc.player.setXRot(0.0F);
            mc.player.yRotO = 0.0F;
            mc.player.xRotO = 0.0F;
            mc.player.yHeadRot = 0.0F;
            mc.player.yBodyRot = 0.0F;
            mc.player.yHeadRotO = 0.0F;
            mc.player.yBodyRotO = 0.0F;
        } else if (previouslyActive) {
            mc.options.setCameraType(previous);
            mc.options.bobView().set(previousBobbing);
            mc.options.fovEffectScale().set(previousFovEffect);
        }
        previouslyActive = active;
    }
}
