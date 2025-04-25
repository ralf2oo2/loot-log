package ralf2oo2.lootlog;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LootLogManager {
    public static final LootLogManager INSTANCE = new LootLogManager();
    private List<LogLine> logLines = new ArrayList<>();

    private void addLogLineOrIncreaseCount(ItemStack stack, int count){
        Optional<LogLine> logline = logLines.stream().filter(line -> line.isItemEqual(stack) && !line.isAnimating()).findFirst();
        if(logline.isPresent()){
            logline.get().increaseCount(count);
        }
        else {
            logLines.add(new LogLine(stack, count));
        }
    }

    public void handlePickup(ItemEntity itemEntity, int count){
        this.addLogLineOrIncreaseCount(itemEntity.stack,  count);
    }

    public List<LogLine> getLogLines() {
        return logLines.stream().sorted(Comparator.comparingInt(LogLine::getVisibleTicks)).toList();
    }

    public void tick(){
        logLines.removeIf(LogLine::shouldRemove);
        for(LogLine logLine : logLines){
            logLine.tick();
        }
    }

    public void renderTick(){
        for(LogLine logLine : logLines){
            logLine.renderTick();
        }
    }
}
