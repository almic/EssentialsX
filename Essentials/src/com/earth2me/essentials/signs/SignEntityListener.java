package com.earth2me.essentials.signs;

import net.ess3.api.IEssentials;
import org.bukkit.event.Listener;


@SuppressWarnings("unused")
public class SignEntityListener implements Listener {

    //private final transient IEssentials ess;

    public SignEntityListener(final IEssentials ess) {
        //this.ess = ess;
    }

    /*@EventHandler(priority = EventPriority.LOW)
    public void onSignEntityExplode(final EntityExplodeEvent event) {
        if (ess.getSettings().areSignsDisabled()) {
            event.getHandlers().unregister(this);
            return;
        }

        for (Block block : event.blockList()) {
            if ((MaterialUtil.isSign(block.getType()) && EssentialsSign.isValidSign(ess, new EssentialsSign.BlockSign(block))) || EssentialsSign.checkIfBlockBreaksSigns(block)) {
                event.setCancelled(true);
                return;
            }
            for (EssentialsSign sign : ess.getSettings().enabledSigns()) {
                if (sign.areHeavyEventRequired() && sign.getBlocks().contains(block.getType())) {
                    event.setCancelled(!sign.onBlockExplode(block, ess));
                    return;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSignEntityChangeBlock(final EntityChangeBlockEvent event) {
        if (ess.getSettings().areSignsDisabled()) {
            event.getHandlers().unregister(this);
            return;
        }

        final Block block = event.getBlock();
        if ((MaterialUtil.isSign(block.getType()) && EssentialsSign.isValidSign(ess, new EssentialsSign.BlockSign(block))) || EssentialsSign.checkIfBlockBreaksSigns(block)) {
            event.setCancelled(true);
            return;
        }
        for (EssentialsSign sign : ess.getSettings().enabledSigns()) {
            if (sign.areHeavyEventRequired() && sign.getBlocks().contains(block.getType()) && !sign.onBlockBreak(block, ess)) {
                event.setCancelled(true);
                return;
            }
        }
    }*/
}
