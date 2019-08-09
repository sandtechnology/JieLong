package top.seraphjack.jielong;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.seraphjack.jielong.command.CommandJieLong;
import top.seraphjack.jielong.idiom.IdiomProvider;
import top.seraphjack.jielong.idiom.IdiomProviderFile;

@Mod(modid = JieLong.MODID, name = JieLong.NAME, version = JieLong.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class JieLong {
    public static final String MODID = "jielong", NAME = "JieLong", VERSION = "@VERSION_INJECT@";

    public static final Logger logger = LogManager.getLogger(MODID);

    public static IdiomProvider idiomProvider;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        idiomProvider = new IdiomProviderFile();
        event.registerServerCommand(new CommandJieLong());
    }
}
