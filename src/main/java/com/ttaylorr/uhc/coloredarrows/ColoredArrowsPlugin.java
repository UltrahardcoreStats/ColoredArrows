package com.ttaylorr.uhc.coloredarrows;

import com.ttaylorr.uhc.coloredarrows.listeners.ProjectileLandListener;
import com.ttaylorr.uhc.coloredarrows.listeners.ProjectileLaunchListener;
import org.bukkit.Color;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ColoredArrowsPlugin extends JavaPlugin implements Listener {

    private static ColoredArrowsPlugin instance;
    private List<Color> colors;

    public static ColoredArrowsPlugin get() {
        return instance;
    }

    public List<Color> getColors() {
        return this.colors;
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new ProjectileLaunchListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileLandListener(this), this);

        colors = new ArrayList<>();

        for(String s : this.getConfig().getStringList("colors")) {
            String[] colors = s.split(",");

            Color c = Color.fromRGB(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
            this.colors.add(c);
        }
    }
}
