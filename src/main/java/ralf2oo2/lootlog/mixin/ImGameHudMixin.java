package ralf2oo2.lootlog.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.util.ScreenScaler;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralf2oo2.lootlog.LogLine;
import ralf2oo2.lootlog.LootLogManager;
import ralf2oo2.lootlog.config.LootLogConfig;

import java.util.List;

@Mixin(InGameHud.class)
public class ImGameHudMixin extends DrawContext {
    @Shadow private Minecraft minecraft;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2))
    void lootLog_render(float delta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci){
        GL11.glPushMatrix();
        LootLogManager lootLogManager = LootLogManager.INSTANCE;
        lootLogManager.renderTick();
        List<LogLine> logLines = lootLogManager.getLogLines();
        GL11.glScalef(LootLogConfig.config.scale, LootLogConfig.config.scale, 1.0F);

        this.renderLogLeft(logLines);
        this.renderLogRight(logLines);

        GL11.glPopMatrix();
    }

    void renderLogLeft(List<LogLine> logLines){
        for(int i = 0; i < logLines.size(); i++){
            int height = 9;
            int y = 2 + height * i;
            BlockRenderManager blockRenderManager = ((WorldRendererAccessor)minecraft.worldRenderer).getBlockRenderManager();
            drawTextWithShadow(minecraft.textRenderer, logLines.get(i).getLineString(), -logLines.get(i).getXOffset(), y, logLines.get(i).getColor());
            GL11.glPushMatrix();
            //GL11.glTranslatef(0f, height, 0f);
            blockRenderManager.render(Block.SAND, 0, 1f);
            GL11.glPopMatrix();
        }
    }

    void renderLogRight(List<LogLine> logLines){
        ScreenScaler scaledResolution = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
        for(int i = 0; i < logLines.size(); i++){
            int height = 9;
            int y = 2 + height * i;

            int width = minecraft.textRenderer.getWidth(logLines.get(i).getLineString());
            int windowWidth = (int) ((scaledResolution.getScaledWidth() / LootLogConfig.config.scale) - 2 - width);

            drawTextWithShadow(minecraft.textRenderer, logLines.get(i).getLineString(), windowWidth + logLines.get(i).getXOffset(), y, logLines.get(i).getColor());
        }
    }
}
