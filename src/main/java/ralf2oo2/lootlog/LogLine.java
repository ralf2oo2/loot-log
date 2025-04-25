package ralf2oo2.lootlog;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LogLine {
    private Item item;
    private int damage;
    private int count;
    private int visibleTicks;

    public LogLine(Item item, int damage, int count, int visibleTicks){
        this.item = item;
        this.damage = damage;
        this.count = count;
        this.visibleTicks = visibleTicks;
    }

    public LogLine(ItemStack stack){
        this(stack.getItem(), stack.getDamage(), stack.count, 40);
    }

    public void tick(){
        if(visibleTicks > 0){
            visibleTicks--;
        }
    }

    public void increaseCount(int count){
        this.count += count;
    }

    public String getLineString(){
        String itemName = I18n.getTranslation(item.getTranslationKey(new) + ".name");
        return "";
    }

    public int getColor(){
        return 0xFFFFFF;
    }

    public boolean shouldRemove(){
        return visibleTicks <= 0;
    }

    public boolean isItemEqual(ItemStack stack){
        if(stack.itemId != item.id) return false;
        if(item.hasSubtypes() && damage != stack.getDamage()) return false;
        return true;
    }
}
