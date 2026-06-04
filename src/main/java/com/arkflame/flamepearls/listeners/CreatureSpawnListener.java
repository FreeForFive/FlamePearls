package com.arkflame.flamepearls.listeners;

import com.arkflame.flamepearls.config.GeneralConfigHolder;
import org.bukkit.entity.Endermite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
    private final GeneralConfigHolder generalConfigHolder;

    public CreatureSpawnListener(final GeneralConfigHolder generalConfigHolder) {
        this.generalConfigHolder = generalConfigHolder;
    }

    @EventHandler(ignoreCancelled = true)
    public void onCreatureSpawn(final CreatureSpawnEvent event) {
        if (!(event.getEntity() instanceof Endermite)) {
            return;
        }

        if (!generalConfigHolder.isEndermitesEnabled()) {
            event.setCancelled(true);
            return;
        }

        if (isEnderPearlSpawn(event)) {
            event.setCancelled(true);
        }
    }

    private boolean isEnderPearlSpawn(final CreatureSpawnEvent event) {
        return event.getSpawnReason() != null && "ENDER_PEARL".equals(event.getSpawnReason().name());
    }
}
