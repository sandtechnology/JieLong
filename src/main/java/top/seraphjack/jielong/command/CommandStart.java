package top.seraphjack.jielong.command;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import top.seraphjack.jielong.event.EventStartJieLong;
import top.seraphjack.jielong.handler.JieLongHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CommandStart extends CommandBase {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new WrongUsageException("Invalid arguments.");
        } else {
            if (JieLongHandler.instance().startJieLong(args[0])) {
                boolean result = MinecraftForge.EVENT_BUS.post(new EventStartJieLong(getCommandSenderAsPlayer(sender), args[0]));
                if (result) {
                    sender.sendMessage(new TextComponentString("接龙发起被其他 Mod 取消。"));
                    return;
                }
                sender.sendMessage(new TextComponentString("成功发起接龙！"));
            } else {
                sender.sendMessage(new TextComponentString("不是有效的成语！"));
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
