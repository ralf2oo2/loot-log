package ralf2oo2.lootlog;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LogLine {
    private ItemStack stack;
    private int count;
    private int visibleTicks;

    private boolean animating;
    private int xOffset = 0;
    private long prevAnimationUpdate;

    public LogLine(ItemStack stack, int count){
        this.stack = stack.copy();
        this.count = count;
        this.visibleTicks = 40;
    }

    public void tick(){
        visibleTicks--;
        if(visibleTicks <= 0){
            animating = true;
        }
    }

    public void renderTick(){
        if(animating){
            int textWidth = Minecraft.class.cast(FabricLoader.getInstance().getGameInstance()).textRenderer.getWidth(getLineString());
            long time = System.currentTimeMillis();
            if(time - this.prevAnimationUpdate >= 10){
                float progress = 1f - ((float)xOffset / textWidth);
                int i = Math.max(1, (int)(progress * 20));

                xOffset += i;

                if(xOffset > textWidth){
                    animating = false;
                    System.out.println("stopped animating");
                }
                this.prevAnimationUpdate = time;
                System.out.println(xOffset + " : " + i);
            }
        }
    }

    public void increaseCount(int count){
        this.count += count;
        this.visibleTicks = 40;
    }

    public String getLineString(){
        String itemName = I18n.getTranslation(stack.getItem().getTranslationKey(stack) + ".name");
        return count + "x " + itemName;
    }

    public int getColor(){
        return 0xFFFFFF;
    }

    public boolean shouldRemove(){
        return visibleTicks <= 0 && !animating;

    }

    public boolean isItemEqual(ItemStack stack){
        return this.stack.isItemEqual(stack);
    }

    public boolean isAnimating(){
        return this.animating;
    }

    public int getVisibleTicks(){
        return visibleTicks;
    }

    public int getXOffset(){
        return this.xOffset;
    }
}
