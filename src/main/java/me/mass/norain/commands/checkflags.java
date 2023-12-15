package me.mass.norain.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.util.List;

public class checkflags implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        if (!player.isOp() && !sender.equals(Bukkit.getServer().getConsoleSender())) {
            player.sendMessage("You do not have permission to execute this command.");
            return true;
        }

        List<String> javaFlags = ManagementFactory.getRuntimeMXBean().getInputArguments();
        if (javaFlags.contains("-Dusing.aikars.flags=https://mcflags.emc.gs") && javaFlags.contains("-Daikars.new.flags=true")) {
            sender.sendMessage("§2Started with: ✓ Using Aikar's flags");
        } else {
            sender.sendMessage("§cStarted with: ✗ Wrong Garbage Collector");
        }

        return true;
    }
}