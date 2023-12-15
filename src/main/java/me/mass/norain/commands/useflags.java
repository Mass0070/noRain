package me.mass.norain.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class useflags implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        if (!player.isOp() && !sender.equals(Bukkit.getServer().getConsoleSender())) {
            player.sendMessage("You do not have permission to execute this command.");
            return true;
        }

        String jvmArgs = System.getenv("JVM_OPTS");
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory() / 1048576;
        long totalMemory = runtime.totalMemory() / 1048576;
        long freeMemory = runtime.freeMemory() / 1048576;

        player.sendMessage("JVM arguments: " + jvmArgs);
        player.sendMessage("Maximum Memory: " + maxMemory + " MB");
        player.sendMessage("Total Memory: " + totalMemory + " MB");
        player.sendMessage("Free Memory: " + freeMemory + " MB");

        // -1024 for heap memory
        String newNemory = "-Xmx" + (maxMemory - 1024) + "M -Xms" + (maxMemory - 1024) + "M";
        player.sendMessage(newNemory);


        String path = "../init.sh";
        String newText = "/run.sh java " + newNemory + " -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true -jar server.jar nogui\n";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(newText);
            writer.close();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return true;
    }
}