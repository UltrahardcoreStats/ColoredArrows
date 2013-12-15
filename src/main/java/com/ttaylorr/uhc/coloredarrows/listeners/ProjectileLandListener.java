package com.ttaylorr.uhc.coloredarrows.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.uhc.coloredarrows.ColoredArrowsPlugin;
import com.ttaylorr.uhc.coloredarrows.util.ColoredArrowsUtil;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileLandListener implements Listener {

    private ColoredArrowsPlugin plugin;

    public ProjectileLandListener(ColoredArrowsPlugin plugin) {
        Preconditions.checkArgument(plugin.isEnabled(), "Plugin is not enabled!");
        this.plugin = Preconditions.checkNotNull(plugin, "plugin");
    }

    @EventHandler
    public void onArrowCollide(EntityDamageByEntityEvent event) {

        if(!ColoredArrowsUtil.ensureColoredFirework(plugin, event.getDamager())) return;

        Arrow arrow = (Arrow) event.getDamager();

        Firework firework = (Firework) arrow.getPassenger();
        ColoredArrowsUtil.detonateFireworkIn(plugin, firework, 0);
    }

    @EventHandler
    public void onArrowInBlock(ProjectileHitEvent event) {

        if(!ColoredArrowsUtil.ensureColoredFirework(plugin, event.getEntity())) return;

        ColoredArrowsUtil.detonateFireworkIn(plugin, (Firework) event.getEntity().getPassenger(), 0);
    }

}
