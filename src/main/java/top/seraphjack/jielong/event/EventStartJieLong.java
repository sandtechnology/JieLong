package top.seraphjack.jielong.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class EventStartJieLong extends PlayerEvent {
    private final String idiom;

    public EventStartJieLong(EntityPlayer player, String idiom) {
        super(player);
        this.idiom = idiom;
    }

    public String getIdiom() {
        return idiom;
    }
}
