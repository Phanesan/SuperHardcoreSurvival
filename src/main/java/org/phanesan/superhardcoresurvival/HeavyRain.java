package org.phanesan.superhardcoresurvival;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import java.util.Collection;
import java.util.List;

public class HeavyRain {

    public BukkitRunnable updateHeavyRain;
    private SuperHardcoreSurvival main;
    private World overWorld;
    private World nether;
    private World end;

    public HeavyRain(SuperHardcoreSurvival main) {
        this.main = main;
    }

    public void getConfigWorlds() {
        try {
            this.overWorld = main.getServer().getWorld(main.configFile.getConfigFile().getString("heavyrain_world"));
            this.nether = main.getServer().getWorld(main.configFile.getConfigFile().getString("heavyrain_world_nether"));
            this.end = main.getServer().getWorld(main.configFile.getConfigFile().getString("heavyrain_world_the_end"));
        } catch (Exception e) {
            main.logger.warning("Algo fallo al detectar el mundo, revise si el nombre de los mundos esta correcto.");
        }
    }

    public void start() {
        updateHeavyRain = new BukkitRunnable() {

            @Override
            public void run() {
                getConfigWorlds();

                if(main.data.ELAPSED_TIME >= main.data.MAX_TIME) {
                    main.data.ELAPSED_TIME = 0;
                    main.data.MAX_TIME = 0;
                    overWorld.setStorm(false);
                    main.data.isHeavyRain = false;
                    main.setPersistentData(main.findArmorStand(main),0,main.HEAVYRAIN_ON);
                    main.setPersistentData(main.findArmorStand(main),0,main.ELAPSED_TIME);
                    main.setPersistentData(main.findArmorStand(main),0,main.MAX_TIME);
                    stop();
                    return;
                }

                overWorld.setStorm(true);
                overWorld.setThundering(true);

                Collection<?> players = Bukkit.getOnlinePlayers();
                for(Object p : players) {
                    Player player = (Player) p;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorText.translate("&b"+formatTimer(main.data.MAX_TIME-main.data.ELAPSED_TIME))));
                }

                List<Player> playersInNether = nether.getPlayers();
                for(Player p : playersInNether) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,20*5,0));
                }

                List<Player> playersInEnd = end.getPlayers();
                for(Player p : playersInEnd) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,20*5,0));
                }

                main.data.ELAPSED_TIME++;
            }
        };

        updateHeavyRain.runTaskTimer(main,0,20);
    }

    public void stop() {
        updateHeavyRain.cancel();
        updateHeavyRain = null;
    }

    private String formatTimer(int seconds) {
        int horas = seconds / 3600;
        int minutos = (seconds % 3600) / 60;
        int segundos = seconds % 60;
        return horas + ":" + minutos + ":" + segundos;
    }

}
