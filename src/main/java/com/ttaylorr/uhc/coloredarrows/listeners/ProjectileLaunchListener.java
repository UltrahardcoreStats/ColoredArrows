package com.ttaylorr.uhc.coloredarrows.listeners;

import com.google.common.base.Preconditions;
import com.ttaylorr.uhc.coloredarrows.ColoredArrowsPlugin;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Taylor on 12/15/13.
 */
public class ProjectileLaunchListener implements Listener {

    private ColoredArrowsPlugin plugin;

    public ProjectileLaunchListener(ColoredArrowsPlugin plugin) {
        Preconditions.checkArgument(plugin.isEnabled(), "The plugin is not enabled!");
        this.plugin = Preconditions.checkNotNull(plugin);
    }

    @EventHandler
    public void onArrowFire(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        if (!(event.getEntity() instanceof Arrow)) return;

        Player shooter;
        Arrow projectile;
        Location location;

        try {
            shooter = (Player) event.getEntity().getShooter();
            projectile = (Arrow) event.getEntity();
        } catch (ClassCastException e) {
            e.printStackTrace();
            return;
        }

        location = projectile.getLocation();

        projectile.setMetadata("ColoredArrow", new FixedMetadataValue(plugin.get(), new Boolean(true)));

        FireworkMeta meta = (FireworkMeta) (new ItemStack(Material.FIREWORK, 1)).getItemMeta();
        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);

        meta.addEffect(FireworkEffect.builder()
                .with(FireworkEffect.Type.BURST)
                .withColor(plugin.getColors())
                .withTrail()
                .build());

        firework.setFireworkMeta(meta);
        projectile.setPassenger(firework);
    }

}
