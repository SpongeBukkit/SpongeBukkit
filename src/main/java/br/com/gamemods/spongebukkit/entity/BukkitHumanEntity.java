package br.com.gamemods.spongebukkit.entity;

import br.com.gamemods.spongebukkit.server.BukkitServer;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class BukkitHumanEntity<E extends EntityPlayer> extends BukkitLivingEntity<E> implements HumanEntity
{
    private PermissibleBase permissible = new PermissibleBase(this);

    public BukkitHumanEntity(BukkitServer server, E entity)
    {
        super(server, entity);
    }

    @Override
    public String getName()
    {
        return entity.getDisplayName();
    }

    @Override
    public PlayerInventory getInventory()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Inventory getEnderChest()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property property, int i)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public InventoryView getOpenInventory()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public InventoryView openInventory(Inventory itemStacks)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean b)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void openInventory(InventoryView inventoryView)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void closeInventory()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStack getItemInHand()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setItemInHand(ItemStack stack)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStack getItemOnCursor()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setItemOnCursor(ItemStack stack)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSleeping()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSleepTicks()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameMode getGameMode()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGameMode(GameMode gameMode)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlocking()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getExpToLevel()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPermissionSet(String s)
    {
        return permissible.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission)
    {
        return permissible.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s)
    {
        return permissible.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission)
    {
        return permissible.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b)
    {
        return permissible.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin)
    {
        return permissible.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i)
    {
        return permissible.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i)
    {
        return permissible.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment)
    {
        permissible.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions()
    {
        permissible.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions()
    {
        return permissible.getEffectivePermissions();
    }

    @Override
    public boolean isOp()
    {
        return server.getVanillaServer().getConfigurationManager().func_152596_g(entity.getGameProfile());
    }

    @Override
    public void setOp(boolean b)
    {
        server.getVanillaServer().getConfigurationManager().func_152605_a(entity.getGameProfile());
    }
}
