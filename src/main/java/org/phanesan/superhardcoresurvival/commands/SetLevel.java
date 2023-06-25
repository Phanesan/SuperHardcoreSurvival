package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.ColorText;

public class SetLevel {

    public SetLevel(SuperHardcoreSurvival plugin, CommandSender sender, String[] args) {
        if(args.length > 1 && !args[1].isEmpty()) {
            if(plugin.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(args[1]))) {
                Player player = plugin.getServer().getPlayer(args[1]);
                if(args.length > 2 && !args[2].isEmpty()) {
                    int level = Integer.valueOf(args[2]);
                    if(level >= 0 && level <= 6) {
                        plugin.setPersistentData(player,Integer.valueOf(args[2]),plugin.PLAYER_DEATH_LEVEL);
                        sender.sendMessage(ColorText.translate("&aNivel de muerte del jugador " + player.getName() + " ahora es de " + args[2]));
                    } else {
                        sender.sendMessage(ColorText.translate("&cIngrese un nivel de muerte valido (0-6)"));
                    }
                } else {
                    sender.sendMessage(ColorText.translate("&cIngresa el nivel de muerte (0-6)."));
                }
            } else {
                sender.sendMessage(ColorText.translate("&cJugador no encontrado o no esta en linea."));
            }
        } else {
            sender.sendMessage(ColorText.translate("&cIngresa un jugador"));
        }
    }

}
