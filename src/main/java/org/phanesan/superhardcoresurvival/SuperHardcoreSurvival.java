package org.phanesan.superhardcoresurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import java.util.logging.Logger;

public final class SuperHardcoreSurvival extends JavaPlugin {

    public Logger logger;

    @Override
    public void onLoad() {
        logger = getLogger();

        if(!getServer().isHardcore()) {
            logger.warning(ColorText.translate("&cEl servidor no esta en modo HARDCORE. Desactivando plugin..."));
            getPluginLoader().disablePlugin(this);
        } else {
            logger.fine("&aEl servidor esta en HARDCORE. Activando plugin...");
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
