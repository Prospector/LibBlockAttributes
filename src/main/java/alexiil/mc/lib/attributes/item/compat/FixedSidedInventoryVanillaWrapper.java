package alexiil.mc.lib.attributes.item.compat;

import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

import alexiil.mc.lib.attributes.item.FixedItemInv;

/** An {@link FixedItemInv} that wraps a vanilla {@link SidedInventory} for a particular {@link Direction side}. */
public class FixedSidedInventoryVanillaWrapper extends FixedInventoryVanillaWrapper {

    final SidedInventory sidedInv;
    final Direction side;

    public FixedSidedInventoryVanillaWrapper(SidedInventory inv, Direction side) {
        super(inv);
        this.side = side;
        this.sidedInv = inv;
    }

    public static FixedItemInv create(SidedInventory inventory, Direction side) {
        FixedSidedInventoryVanillaWrapper wrapper = new FixedSidedInventoryVanillaWrapper(inventory, side);
        return wrapper.getMappedInv(inventory.getInvAvailableSlots(side));
    }

    @Override
    protected boolean canExtract(int slot, ItemStack extractedStack) {
        return sidedInv.canExtractInvStack(slot, extractedStack, side);
    }

    @Override
    protected boolean canInsert(int slot, ItemStack newStack) {
        return isItemValidForSlot(slot, newStack) && sidedInv.canInsertInvStack(slot, newStack, side);
    }
}
