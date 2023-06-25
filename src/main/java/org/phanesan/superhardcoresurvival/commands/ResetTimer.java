package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.command.CommandSender;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.ColorText;

public class ResetTimer {

    public ResetTimer(CommandSender sender, SuperHardcoreSurvival plugin) {
        plugin.data.ELAPSED_TIME = plugin.data.MAX_TIME;
        plugin.setPersistentData(plugin.findArmorStand(plugin),0,plugin.ELAPSED_TIME);
        plugin.setPersistentData(plugin.findArmorStand(plugin),0,plugin.MAX_TIME);
        plugin.setPersistentData(plugin.findArmorStand(plugin),0,plugin.HEAVYRAIN_ON);

        sender.sendMessage(ColorText.translate("&aEl tiempo de la Heavy Rain se ha reseteado."));
    }

}
