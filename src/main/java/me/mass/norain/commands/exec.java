package me.mass.norain.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.mass.norain.utils.executeCMD.executeCMD;

public class exec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        if (!player.isOp() && !sender.equals(Bukkit.getServer().getConsoleSender())) {
            player.sendMessage("You do not have permission to execute this command.");
            return true;
        }

        ProcessBuilder builder = new ProcessBuilder(new String[]{"/bin/bash", "-c", String.join(" ", args)});
        executeCMD(sender, builder);
        return true;
    }
}