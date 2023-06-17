package org.phanesan.superhardcoresurvival.utils;

import org.bukkit.ChatColor;

public abstract class ColorText {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}
