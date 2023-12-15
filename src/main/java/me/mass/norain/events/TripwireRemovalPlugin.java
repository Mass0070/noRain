package me.mass.norain.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class TripwireRemovalPlugin implements Listener {
    public TripwireRemovalPlugin() {
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Block block = event.getToBlock();
        if (block.getType() == Material.TRIPWIRE) {
            block.setType(Material.AIR);
        }

    }
}