package org.phanesan.superhardcoresurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> argsString = new ArrayList<String>();
        switch(args.length) {
            case 1:
                argsString.add("resetlevel");
                argsString.add("resettimer");
                break;
            case 2:
                switch(args[0]) {
                    case "resetlevel":
                        Collection<?> players = Bukkit.getServer().getOnlinePlayers();
                        for (Object p : players) {
                            argsString.add(((Player) p).getName());
                        }
                        break;
                }
                break;
        }

        return argsString;
    }
}
