package ralf2oo2.lootlog.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ralf2oo2.lootlog.LootLogManager;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "sendPickup", at = @At("HEAD"))
    void lootLog_sendPickup(Entity itemEntity, int count, CallbackInfo ci){
        System.out.println("picked up " + count + " items");
        LootLogManager.INSTANCE.handlePickup((ItemEntity)itemEntity, count);
    }
}
