/*
 * Copyright (c) 2019 AlexIIL
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package alexiil.mc.lib.attributes.fluid;

import alexiil.mc.lib.attributes.fluid.impl.SimpleLimitedGroupedFluidInv;

/** A modifiable version of {@link GroupedFluidInvView}, except that all modification methods are provided by
 * {@link FluidExtractable} and {@link FluidInsertable}. */
public interface GroupedFluidInv extends GroupedFluidInvView, FluidTransferable {

    /** @return A new {@link LimitedGroupedFluidInv} that provides a more controllable version of this
     *         {@link GroupedFluidInv}. */
    default LimitedGroupedFluidInv createLimitedInv() {
        return new SimpleLimitedGroupedFluidInv(this);
    }
}
