package top.seraphjack.jielong.handler;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import top.seraphjack.jielong.JieLong;
import top.seraphjack.jielong.event.EventJieLong;
import top.seraphjack.jielong.event.EventStartJieLong;

@Mod.EventBusSubscriber(modid = JieLong.MODID)
public class EventHandler {
    @SubscribeEvent
    public void onPlayerChat(ServerChatEvent event) {
        if (JieLongHandler.instance().jieLong(event.getMessage())) {
            MinecraftForge.EVENT_BUS.post(new EventJieLong(event.getPlayer()));
        }
    }

    @SubscribeEvent
    public void onJieLong(EventJieLong event) {
        FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(
                "玩家 " + event.getEntityPlayer().getGameProfile().getName()
                        + " 成功接龙！" + "当前成语：" + JieLongHandler.instance().getCurrentIdiom()
                        + " ，当前接龙数：" + JieLongHandler.instance().getCurrentCount() + "。")
        );
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onJieLongStart(EventStartJieLong event) {
        if (event.isCanceled()) return;
        FMLCommonHandler.instance().getMinecraftServerInstance().sendMessage(new TextComponentString(
                "玩家 " + event.getEntityPlayer().getGameProfile().getName() + " 成功发起接龙！起始成语是：" +
                        event.getIdiom() + "。"
        ));
    }
}
