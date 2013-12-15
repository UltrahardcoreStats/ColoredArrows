package com.ttaylorr.uhc.coloredarrows.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.uhc.coloredarrows.ColoredArrowsPlugin;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import static com.ttaylorr.uhc.coloredarrows.util.ColoredArrowsUtil.detonateFireworkIn;
import static com.ttaylorr.uhc.coloredarrows.util.ColoredArrowsUtil.isColoredFirework;

public class ProjectileLandListener implements Listener {

    private ColoredArrowsPlugin plugin;

    public ProjectileLandListener(ColoredArrowsPlugin plugin) {
        Preconditions.checkArgument(plugin.isEnabled(), "Plugin is not enabled!");
        this.plugin = Preconditions.checkNotNull(plugin, "plugin");
    }

    @EventHandler
    public void onArrowCollide(EntityDamageByEntityEvent event) {

        if(!isColoredFirework(plugin, event.getDamager())) return;

        Arrow arrow = (Arrow) event.getDamager();

        Firework firework = (Firework) arrow.getPassenger();
        detonateFireworkIn(plugin, firework, 0);
    }

    @EventHandler
    public void onArrowInBlock(ProjectileHitEvent event) {

        if(!isColoredFirework(plugin, event.getEntity())) return;

        detonateFireworkIn(plugin, (Firework) event.getEntity().getPassenger(), 0);
    }

}
