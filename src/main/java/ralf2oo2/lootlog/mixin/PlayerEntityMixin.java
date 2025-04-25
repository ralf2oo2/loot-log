package ralf2oo2.lootlog.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralf2oo2.lootlog.LogLine;
import ralf2oo2.lootlog.LootLogManager;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin{
    @Inject(method = "tick", at = @At("HEAD"))
    void lootLog_tick(CallbackInfo ci){
        LootLogManager.INSTANCE.tick();
    }
}
