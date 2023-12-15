package me.mass.norain;

import java.util.Iterator;
import java.util.Objects;

import me.mass.norain.commands.checkflags;
import me.mass.norain.commands.exec;
import me.mass.norain.commands.useflags;
import me.mass.norain.events.TripwireRemovalPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class noRainPlugin extends JavaPlugin implements Listener {
    private static noRainPlugin instance;

    public static noRainPlugin getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);

        setAlwaysSun();
        initializeTripwireRemoval();

        // Commands
        Objects.requireNonNull(getCommand("exec")).setExecutor(new exec());
        Objects.requireNonNull(getCommand("checkflags")).setExecutor(new checkflags());
        Objects.requireNonNull(getCommand("useflags")).setExecutor(new useflags());
    }

    public void onDisable() {
    }

    private void initializeTripwireRemoval() {
        TripwireRemovalPlugin tripwireRemovalPlugin = new TripwireRemovalPlugin();
        getServer().getPluginManager().registerEvents(tripwireRemovalPlugin, this);
    }

    private void setAlwaysSun() {
        Iterator var1 = Bukkit.getWorlds().iterator();

        while(var1.hasNext()) {
            World world = (World)var1.next();
            System.out.println("noRain: " + world);
            world.setThundering(false);
            world.setStorm(false);
        }

    }

    @EventHandler(
            priority = EventPriority.HIGHEST,
            ignoreCancelled = true
    )

    public void onWeatherChange(WeatherChangeEvent event) {
        boolean isWeatherChangeHandled = false;
        if (event.toWeatherState()) {
            if (!event.getWorld().getName().equalsIgnoreCase("titan")) {
                boolean newWeatherState = event.toWeatherState();
                boolean currentWeatherState = event.getWorld().hasStorm();
                if (newWeatherState != currentWeatherState && !isWeatherChangeHandled) {
                    event.setCancelled(true);
                    Plugin plugin = getInstance();
                    if (plugin != null) {
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            event.getWorld().setStorm(false);
                            event.getWorld().setWeatherDuration(1);
                        }, 1L);
                    }
                }

            }
        }
    }
}