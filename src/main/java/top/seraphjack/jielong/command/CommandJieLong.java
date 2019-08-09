package top.seraphjack.jielong.command;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CommandJieLong extends CommandTreeBase {
    public CommandJieLong() {
        addSubcommand(new CommandStart());
    }

    @Override
    public String getName() {
        return "jielong";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/jielong start <起始成语>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
