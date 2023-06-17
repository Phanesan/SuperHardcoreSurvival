package org.phanesan.superhardcoresurvival.utils;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;

public abstract class BanPlayer {

    public static void banPlayer(Player player, String reason, long duration) {
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);

        if (duration <= 0) {
            banList.addBan(player.getName(), reason, null, null);
        } else {
            long expiration = System.currentTimeMillis() + (duration * 1000); // Duración en segundos
            banList.addBan(player.getName(), reason, new Date(expiration), null);
        }

        player.kickPlayer(reason);
    }

}
