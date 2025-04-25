package ralf2oo2.lootlog.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralf2oo2.lootlog.LogLine;
import ralf2oo2.lootlog.LootLogManager;

import java.util.List;

@Mixin(InGameHud.class)
public class ImGameHudMixin extends DrawContext {
    @Shadow private Minecraft minecraft;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2))
    void lootLog_render(float delta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci){
        LootLogManager lootLogManager = LootLogManager.INSTANCE;
        lootLogManager.renderTick();
        List<LogLine> logLines = lootLogManager.getLogLines();
        for(int i = 0; i < logLines.size(); i++){
            int height = 9;
            int y = 2 + height * i;
            drawTextWithShadow(minecraft.textRenderer, logLines.get(i).getLineString(), -logLines.get(i).getXOffset(), y, logLines.get(i).getColor());
        }
    }
}
