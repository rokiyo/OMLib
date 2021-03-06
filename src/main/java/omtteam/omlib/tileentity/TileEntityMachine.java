package omtteam.omlib.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import omtteam.omlib.power.OMEnergyStorage;
import omtteam.omlib.util.TrustedPlayer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public abstract class TileEntityMachine extends TileEntityContainerElectric implements ITrustedPlayersManager {
    private boolean active = false;
    protected boolean inverted = false;
    private boolean redstone = false;
    protected List<TrustedPlayer> trustedPlayers;

    public TileEntityMachine() {
        super();
        this.trustedPlayers = new ArrayList<>();
        this.storage = new OMEnergyStorage(10, 10);
        this.inverted = true;
        this.active = true;
    }

    @Override
    public List<TrustedPlayer> getTrustedPlayers() {
        return trustedPlayers;
    }

    public void setTrustedPlayers(List<TrustedPlayer> list) {
        this.trustedPlayers = list;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("active", active);
        nbtTagCompound.setBoolean("inverted", inverted);
        nbtTagCompound.setBoolean("redstone", redstone);
        nbtTagCompound.setTag("trustedPlayers", getTrustedPlayersAsNBT());
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        this.active = !nbtTagCompound.hasKey("active") || nbtTagCompound.getBoolean("active");
        this.inverted = !nbtTagCompound.hasKey("inverted") || nbtTagCompound.getBoolean("inverted");
        this.redstone = nbtTagCompound.getBoolean("redstone");
        buildTrustedPlayersFromNBT(nbtTagCompound.getTagList("trustedPlayers", 10));
    }

    public boolean isActive() {
        return active;
    }

    public boolean getInverted() {
        return this.inverted;
    }

    protected void setInverted(boolean inverted) {
        this.inverted = inverted;
        this.active = redstone ^ this.inverted;
        this.markDirty();
    }

    public boolean getRedstone() {
        return this.redstone;
    }

    public void setRedstone(boolean redstone) {
        this.redstone = redstone;
        this.active = this.redstone ^ inverted;
        this.markDirty();
    }
}
