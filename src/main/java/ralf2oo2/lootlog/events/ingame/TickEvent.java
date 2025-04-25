package ralf2oo2.lootlog.events.ingame;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;
import net.modificationstation.stationapi.api.tick.TickScheduler;

public class TickEvent {
    @EventListener
    public void tick(GameTickEvent event){
        System.out.println("tick");
    }
}
