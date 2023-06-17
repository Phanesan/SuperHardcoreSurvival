package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.BanPlayer;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import java.util.logging.Logger;

public class onDeath implements Listener {

    private static final NamespacedKey PLAYER_DEATH_LEVEL = new NamespacedKey("SuperHardcoreSurvival","PlayerDeathLevel");
    private SuperHardcoreSurvival main;

    public onDeath(SuperHardcoreSurvival main) {
        this.main = main;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Logger logger = main.logger;
        /*
        Level 0: Sin cooldown
        Level 1: 10 Min
        Level 2: 30 Min
        Level 3: 1 Hora
        Level 4: 6 Horas
        Level 5: 18 Horas
        Level 6: 1 Dia
         */
        int level = getPersistentData(player);

        switch(level) {
            case 0:
                logger.fine(ColorText.translate("&a" + player + " revivio instantaneamente."));
                break;
            case 1:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 10 minutos.",
                        600*1000);
                break;
            case 2:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 30 minutos.",
                        1800*1000);
                break;
            case 3:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 1 Hora.",
                        3600*1000);
                break;
            case 4:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 6 Horas.",
                        21600*1000);
                break;
            case 5:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 18 Horas.",
                        64800*1000);
                break;
            case 6:
                BanPlayer.banPlayer(player,
                        "&4&l¡HAS MUERTO!n/&cReviviras en 1 Dia.",
                        86400*1000);
                break;
        }

        if(level <= 5) {
            setPersistentData(player, level + 1);
        }
    }

    public void setPersistentData(Player player, int value) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        dataContainer.set(PLAYER_DEATH_LEVEL, PersistentDataType.INTEGER, value);
    }

    public int getPersistentData(Player player) {
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();
        if (dataContainer.has(PLAYER_DEATH_LEVEL, PersistentDataType.INTEGER)) {
            return dataContainer.get(PLAYER_DEATH_LEVEL, PersistentDataType.INTEGER);
        }
        return 0; // Valor predeterminado si el dato no está establecido
    }

}
