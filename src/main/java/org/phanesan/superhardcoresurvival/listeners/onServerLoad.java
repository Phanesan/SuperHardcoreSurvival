package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;

public class onServerLoad implements Listener {

    private SuperHardcoreSurvival plugin;

    public onServerLoad(SuperHardcoreSurvival plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        if (e.getWorld() == plugin.getServer().getWorld(plugin.configFile.getConfigFile().getString("heavyrain_world"))) {
            LivingEntity entity = plugin.findArmorStand(plugin);

            if (entity != null) {
                plugin.logger.info("ArmorStand encontrado, Restaurando datos...");
                plugin.data.MAX_TIME = plugin.getPersistentData(entity, SuperHardcoreSurvival.MAX_TIME);
                plugin.data.ELAPSED_TIME = plugin.getPersistentData(entity, SuperHardcoreSurvival.ELAPSED_TIME);
                plugin.data.isHeavyRain = plugin.getPersistentData(entity, SuperHardcoreSurvival.HEAVYRAIN_ON) == 1;
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
        }
    }

    @EventHandler
    public void onLoadServer(ServerLoadEvent e) {
        if(plugin.getPersistentData(plugin.findArmorStand(plugin),SuperHardcoreSurvival.HEAVYRAIN_ON) == 1) {
            plugin.data.MAX_TIME = plugin.getPersistentData(plugin.findArmorStand(plugin), SuperHardcoreSurvival.MAX_TIME);
            plugin.data.ELAPSED_TIME = plugin.getPersistentData(plugin.findArmorStand(plugin), SuperHardcoreSurvival.ELAPSED_TIME);
            plugin.data.isHeavyRain = plugin.getPersistentData(plugin.findArmorStand(plugin), SuperHardcoreSurvival.HEAVYRAIN_ON) == 1;
            plugin.heavyRain.start();
            plugin.logger.info("Ultimos datos cargados, Se activa la Heavy Rain.");
        } else {
            plugin.logger.info("Ultimos datos cargados, No se activa Heavy Rain.");
        }
    }
}
