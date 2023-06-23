package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.ColorText;

public class CommandMain implements CommandExecutor {

    private SuperHardcoreSurvival plugin;

    public CommandMain(SuperHardcoreSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(args.length > 0 && !args[0].isEmpty()) {
            switch(args[0].toLowerCase()) {
                case "resetlevel":
                    new ResetLevel(commandSender,args);
                    break;
                case "resettimer":
                    new ResetTimer(commandSender,plugin);
                    break;
                default:
                    commandSender.sendMessage(ColorText.translate("&cComando desconocido."));
            }
        } else {
            commandSender.sendMessage(ColorText.translate("&a" + SuperHardcoreSurvival.NAME_PLUGIN + " version: " + SuperHardcoreSurvival.VERSION));
        }
        return true;
    }
}
