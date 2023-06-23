package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import static org.phanesan.superhardcoresurvival.SuperHardcoreSurvival.PLAYER_DEATH_LEVEL;

public class ResetLevel {

    public ResetLevel(CommandSender sender, String[] args) {
        if(args.length > 1 && !args[1].isEmpty()) {
            Player player = Bukkit.getServer().getPlayer(args[1]);
            if(player != null) {
                setPersistentData(player,0);
                sender.sendMessage(ColorText.translate("&aNivel de muertes de " + player.getName() + " reseteado"));
            } else {
                sender.sendMessage("&c¡El jugador " + args[1] + " no existe!");
            }
        } else {
            sender.sendMessage(ColorText.translate("&cIngresa un jugador"));
        }
    }

    public void setPersistentData(Player player, int value) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        dataContainer.set(PLAYER_DEATH_LEVEL, PersistentDataType.INTEGER, value);
    }
}
