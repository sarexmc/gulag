# Smaash – Gulag Camera (Fabric 1.21.1)

Mod **solo cliente** para el Gulag jurásico de Smaash. Cámara elevada fija, vista bloqueada, sin
ataque/uso/minado, y flecha dorada sobre el jugador mientras dura la carrera.

- Minecraft 1.21.1 · Fabric Loader ≥ 0.16 · Java 21 · no requiere Fabric API.
- Se activa en la dimensión `skere_gulag:arena` cuando el jugador (no espectador) tiene el atributo
  `minecraft:generic.luck` ≥ 15. El datapack lo consigue con el modificador `skere_gulag:race_camera` (+16).
- Mappings oficiales de Mojang, así que los nombres del código coinciden con la versión de NeoForge.

## Compilar
GitHub: sube este contenido a la raíz de un repo → Actions → *build* → descarga el artefacto (.jar).
Local: `gradle build` (Gradle 8.10+, JDK 21) → `build/libs/smaash-gulag-camera-1.0.1.jar`.
