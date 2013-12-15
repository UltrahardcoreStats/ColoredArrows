package com.ttaylorr.uhc.coloredarrows.util;

import com.google.common.base.Preconditions;
import com.ttaylorr.uhc.coloredarrows.ColoredArrowsPlugin;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftFirework;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;

public class ColoredArrowsUtil {

    private ColoredArrowsUtil() {

    }

    public static boolean ensureColoredFirework(ColoredArrowsPlugin plugin, Entity entity) {
        if(!(entity instanceof Arrow)) return false;

        Arrow arrow;

        try {
            arrow = (Arrow) entity;
        } catch (ClassCastException e) {
            plugin.getLogger().severe("Cannot cast given entity to firework, aborting!");
            return false;
        }

        if(!(arrow.getMetadata("ColoredArrow").get(0).asBoolean())) return false;
        if(!(arrow.getPassenger() instanceof Firework)) return false;

        return true;
    }

    public static void detonateFireworkIn(ColoredArrowsPlugin plugin, Firework firework, int time) {
        CraftFirework craftFirework;

        try {
            craftFirework = (CraftFirework) firework;
        } catch (ClassCastException e) {
            plugin.getLogger().severe("Cannot cast given firework to CraftFirework, aborting!");
            return;
        }

        Preconditions.checkArgument(time >= 0, "Time must be a non-negative integer!");
        craftFirework.getHandle().expectedLifespan = time;
    }

}
