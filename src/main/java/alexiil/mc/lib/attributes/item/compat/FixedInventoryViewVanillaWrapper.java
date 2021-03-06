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

import alexiil.mc.lib.attributes.ListenerRemovalToken;
import alexiil.mc.lib.attributes.ListenerToken;
import alexiil.mc.lib.attributes.item.FixedItemInv;
import alexiil.mc.lib.attributes.item.FixedItemInvView;
import alexiil.mc.lib.attributes.item.ItemInvSlotChangeListener;

/** An {@link FixedItemInv} that wraps a vanilla {@link Inventory}. */
public class FixedInventoryViewVanillaWrapper implements FixedItemInvView {
    final Inventory inv;

    public FixedInventoryViewVanillaWrapper(Inventory inv) {
        this.inv = inv;
    }

    public static FixedInventoryViewVanillaWrapper wrapInventory(Inventory inv) {
        return new FixedInventoryViewVanillaWrapper(inv);
    }

    @Override
    public int getSlotCount() {
        return inv.getInvSize();
    }

    @Override
    public ItemStack getInvStack(int slot) {
        ItemStack stack = inv.getInvStack(slot);
        // FixedItemInv mandates that the returned stack is never modified.
        // However Inventory definitely doesn't, so we have to copy.
        return stack.isEmpty() ? ItemStack.EMPTY : stack.copy();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item) {
        return inv.isValidInvStack(slot, item);
    }

    @Override
    public int getMaxAmount(int slot, ItemStack stack) {
        return Math.min(inv.getInvMaxStackAmount(), stack.getMaxAmount());
    }

    @Override
    public ListenerToken addListener(ItemInvSlotChangeListener listener, ListenerRemovalToken remToken) {
        // Oddly enough vanilla doesn't support listeners.
        return null;
    }
}
