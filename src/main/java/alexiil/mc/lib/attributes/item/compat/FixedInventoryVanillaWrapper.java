/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.item.compat;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import alexiil.mc.lib.attributes.Simulation;
import alexiil.mc.lib.attributes.item.FixedItemInv;
import alexiil.mc.lib.attributes.item.ItemStackUtil;

/** An {@link FixedItemInv} that wraps a vanilla {@link Inventory}. */
public class FixedInventoryVanillaWrapper extends FixedInventoryViewVanillaWrapper implements FixedItemInv {

    public FixedInventoryVanillaWrapper(Inventory inv) {
        super(inv);
    }

    @Override
    public boolean setInvStack(int slot, ItemStack to, Simulation simulation) {
        boolean allowed = false;
        ItemStack current = getInvStack(slot);
        if (to.isEmpty()) {
            allowed = canExtract(slot, current);
        } else {
            if (current.isEmpty()) {
                allowed = canInsert(slot, to);
            } else if (ItemStackUtil.areEqualIgnoreAmounts(to, current)) {
                if (to.getAmount() < current.getAmount()) {
                    allowed = canExtract(slot, current);
                } else {
                    allowed = canInsert(slot, to);
                }
            } else {
                allowed = canInsert(slot, to) && canExtract(slot, current);
            }
        }
        if (allowed) {
            if (simulation == Simulation.ACTION) {
                inv.setInvStack(slot, to);
            }
            return true;
        }
        return false;
    }

    protected boolean canExtract(int slot, ItemStack extractedStack) {
        return true;
    }

    protected boolean canInsert(int slot, ItemStack newStack) {
        return isItemValidForSlot(slot, newStack);
    }
}
