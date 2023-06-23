package org.phanesan.superhardcoresurvival.utils;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;

import java.util.Date;

public abstract class BanPlayer {

    public static void banPlayer(Player player, String reason, long duration, SuperHardcoreSurvival main) {
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);

        if (duration <= 0) {
            banList.addBan(player.getName(), reason, null, null);
        } else {
            long expiration = System.currentTimeMillis() + (duration * 1000); // Duración en segundos
            banList.addBan(player.getName(), reason, new Date(expiration), null);
        }

        BukkitRunnable run = new BukkitRunnable() {
            @Override
            public void run() {
                player.kickPlayer(reason);
            }
        };

        run.runTaskLater(main,80);
    }

}
