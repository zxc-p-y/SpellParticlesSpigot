package com.fanta.Commands;

import com.fanta.Main.Main;
import com.fanta.Main.ParticleEffects;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EffectsCommands implements CommandExecutor {

    private Main main;
    public EffectsCommands(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("eff")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.YELLOW+"Error: "+ChatColor.GRAY+"Usage: /eff {effectName}");
                    return false;
                }
                if (args[0].equalsIgnoreCase("immol")) {
//                    ParticleEffects.Immolation(player, main);
                }
            }
        }
        return false;
    }
}
