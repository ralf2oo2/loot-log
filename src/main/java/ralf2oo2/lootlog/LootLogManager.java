package ralf2oo2.lootlog;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LootLogManager {
    public static final LootLogManager INSTANCE = new LootLogManager();
    private List<LogLine> logLines = new ArrayList<>();

    private void addLogLineOrIncreaseCount(ItemStack stack){
        Optional<LogLine> logline = logLines.stream().filter(line -> line.isItemEqual(stack)).findFirst();
        if(logline.isPresent()){
            logline.get().increaseCount(stack.count);
        }
        else {
            logLines.add(new LogLine(stack));
        }
    }

    public void handlePickup(ItemEntity itemEntity, int count){
        this.addLogLineOrIncreaseCount(itemEntity.stack);
    }

    public void tick(){
        logLines.removeIf(LogLine::shouldRemove);
        for(LogLine logLine : logLines){
            logLine.tick();
        }
    }
}
