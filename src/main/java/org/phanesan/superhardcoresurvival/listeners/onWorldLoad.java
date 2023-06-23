package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;

import java.util.Collection;

public class onWorldLoad implements Listener {

    private SuperHardcoreSurvival plugin;

    public onWorldLoad(SuperHardcoreSurvival plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        if (e.getWorld() == plugin.getServer().getWorld(plugin.configFile.getConfigFile().getString("heavyrain_world"))) {
            LivingEntity entity = plugin.findArmorStand(plugin);

            if (entity != null) {
                plugin.logger.info("ArmorStand encontrado, Restaurando datos...");
                plugin.data.MAX_TIME = plugin.getPersistentData(entity, plugin.MAX_TIME);
                plugin.data.ELAPSED_TIME = plugin.getPersistentData(entity, plugin.ELAPSED_TIME);
                plugin.data.isHeavyRain = plugin.getPersistentData(entity, plugin.HEAVYRAIN_ON) == 1 ? true : false;

                if(plugin.data.isHeavyRain) {
                    plugin.heavyRain.start();
                }
            } else {
                plugin.logger.info("ArmorStand no encontrado, creando uno nuevo...");
                ArmorStand armorStand = Bukkit.getWorld("world").spawn(new Location(Bukkit.getWorld("world"), 0, 255, 0), ArmorStand.class);
                armorStand.addScoreboardTag("armor_stand_hardcore");
                armorStand.setInvisible(true);
                armorStand.setAI(false);
                armorStand.setGravity(false);
                plugin.setPersistentData(armorStand, plugin.data.ELAPSED_TIME, plugin.ELAPSED_TIME);
                plugin.setPersistentData(armorStand, plugin.data.MAX_TIME, plugin.MAX_TIME);
                plugin.setPersistentData(armorStand, 0, plugin.HEAVYRAIN_ON);
            }

            BukkitRunnable update = new BukkitRunnable() {

                @Override
                public void run() {
                    LivingEntity entity = plugin.findArmorStand(plugin);
                    plugin.setPersistentData(entity, plugin.data.ELAPSED_TIME, plugin.ELAPSED_TIME);
                    plugin.setPersistentData(entity, plugin.data.MAX_TIME, plugin.MAX_TIME);
                }
            };

            update.runTaskTimer(plugin, 0, 20);
        }
    }

}
