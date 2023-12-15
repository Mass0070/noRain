package me.mass.norain.utils;

import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class executeCMD {
    public static void executeCMD(CommandSender sender, ProcessBuilder builder) {
        builder.redirectErrorStream(true);

        try {
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = r.readLine()) != null) {
                sender.sendMessage(line);
            }

            p.waitFor();
        } catch (IOException e) {
            sender.sendMessage("An error occurred while executing the command: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            sender.sendMessage("Command execution was interrupted.");
        }
    }
}