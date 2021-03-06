package omtteam.omlib.util.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import omtteam.omlib.compatability.minecraft.CompatCommandBase;
import omtteam.omlib.tileentity.TileEntityOwnedBlock;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static omtteam.omlib.util.compat.ChatTools.addChatMessage;

/**
 * Created by nico on 6/4/15.
 * Command for changing owners of an owned block
 */

public class CommandChangeOwner extends CompatCommandBase {
    @Override
    @Nonnull
    public String getName() {
        return "omchangeowner";
    }


    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender) {
        return "<dimension> <x> <y> <z> <new owner>";
    }

    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] params) {
        if (params.length != 5) {
            addChatMessage(sender, new TextComponentString(getUsage(sender)));
            return;
        }
        try {
            int dimension = Integer.parseInt(params[0]);
            int x = Integer.parseInt(params[1]);
            int y = Integer.parseInt(params[2]);
            int z = Integer.parseInt(params[3]);
            String ownerName = params[4];
            if (DimensionManager.getWorld(dimension) == null) {
                addChatMessage(sender, new TextComponentString("Invalid dimension"));
                return;
            }
            WorldServer worldserver = server.worldServerForDimension(dimension);

            TileEntity tileEntity = worldserver.getTileEntity(new BlockPos(x, y, z));
            if (tileEntity instanceof TileEntityOwnedBlock) {
                TileEntityOwnedBlock turret = (TileEntityOwnedBlock) tileEntity;
                turret.setOwner(ownerName);
                addChatMessage(sender, new TextComponentString("Block ownership has been updated"));
            } else {
                addChatMessage(sender, new TextComponentString("No ownable block was found at that location"));
            }
        } catch (NumberFormatException e) {
            addChatMessage(sender, new TextComponentString("Dimension and coordinates must be numbers"));
        }
    }
}

