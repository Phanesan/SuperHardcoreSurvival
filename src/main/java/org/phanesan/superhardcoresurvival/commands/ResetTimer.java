package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.command.CommandSender;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.ColorText;

public class ResetTimer {

    public ResetTimer(CommandSender sender, SuperHardcoreSurvival plugin) {
        plugin.data.ELAPSED_TIME = plugin.data.MAX_TIME;

        sender.sendMessage(ColorText.translate("&aEl tiempo de la Heavy Rain se ha reseteado."));
    }

}
