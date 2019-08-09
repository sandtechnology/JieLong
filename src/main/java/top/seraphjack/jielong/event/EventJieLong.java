package top.seraphjack.jielong.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventJieLong extends PlayerEvent {
    public EventJieLong(EntityPlayer player) {
        super(player);
    }
}
