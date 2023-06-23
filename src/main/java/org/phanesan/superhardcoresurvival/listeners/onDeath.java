package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;
import org.phanesan.superhardcoresurvival.utils.BanPlayer;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import java.util.Collection;
import java.util.logging.Logger;

import static org.phanesan.superhardcoresurvival.SuperHardcoreSurvival.PLAYER_DEATH_LEVEL;

public class onDeath implements Listener {

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

        new BukkitRunnable() {
            @Override
            public void run() {
                World worldPlayerDeath = player.getWorld();
                for(int i = 0; i < 5; i++) {
                    worldPlayerDeath.strikeLightningEffect(player.getLocation());
                }
            }
        }.runTask(main);

        switch(level) {
            case 0:
                logger.fine(ColorText.translate("&a" + player.getName() + " revivio instantaneamente."));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(player.getGameMode() == GameMode.SPECTATOR) {
                            player.setGameMode(GameMode.SURVIVAL);
                            cancel();
                        }
                    }
                }.runTaskTimer(main,0,3);
                break;
            case 1:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 10 Minutos"),
                        600,main);
                main.data.isHeavyRain = true;
                break;
            case 2:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 30 Minutos."),
                        1800,main);
                main.data.isHeavyRain = true;
                break;
            case 3:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 1 Hora."),
                        3600,main);
                main.data.isHeavyRain = true;
                break;
            case 4:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 6 Horas."),
                        21600,main);
                main.data.isHeavyRain = true;
                break;
            case 5:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 18 Horas."),
                        64800,main);
                main.data.isHeavyRain = true;
                break;
            case 6:
                BanPlayer.banPlayer(player,
                        ColorText.translate("&4&l¡HAS MUERTO!\n&cReviviras en 1 Dia."),
                        86400,main);
                main.data.isHeavyRain = true;
                break;
        }

        if(level <= 5) {
            setPersistentData(player, level + 1);
        }

        // GLOBAL MESSAGE
        BukkitRunnable globalDeathTitle = new BukkitRunnable() {
            @Override
            public void run() {
                Collection<?> players = main.getServer().getOnlinePlayers();

                for(Object p : players) {
                    Player player1 = (Player) p;
                    player1.sendTitle(ChatColor.DARK_RED + "" + ChatColor.BOLD + "¡HEAVY RAIN!", ChatColor.RED + event.getDeathMessage(), 30, 120, 40);
                    player1.playSound(player1, Sound.ENTITY_BLAZE_DEATH, SoundCategory.MASTER, 1, 0.5f);
                }
            }
        };
        globalDeathTitle.runTask(main);

        if(main.data.isHeavyRain) {
            BukkitRunnable globalHeavyRainMessage = new BukkitRunnable() {
                @Override
                public void run() {
                    Collection<?> players = main.getServer().getOnlinePlayers();

                    for(Object p : players) {
                        Player player1 = (Player) p;
                        player1.sendRawMessage(ColorText.translate("&7¡HEAVY RAIN ACTIVADA! (+20 Minutos)"));
                        player1.playSound(player1,Sound.ENTITY_SKELETON_HORSE_DEATH,SoundCategory.MASTER,1,1);
                    }

                    main.data.MAX_TIME+=1200;
                    main.heavyRain.start();
                    main.setPersistentData(main.findArmorStand(main),1,main.HEAVYRAIN_ON);
                }
            };
            globalHeavyRainMessage.runTaskLater(main,90);
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
