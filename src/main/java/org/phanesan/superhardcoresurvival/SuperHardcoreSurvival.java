package org.phanesan.superhardcoresurvival;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.phanesan.superhardcoresurvival.commands.CommandMain;
import org.phanesan.superhardcoresurvival.commands.TabCompleter;
import org.phanesan.superhardcoresurvival.listeners.*;
import org.phanesan.superhardcoresurvival.utils.ColorText;

import java.util.Collection;
import java.util.logging.Logger;

public final class SuperHardcoreSurvival extends JavaPlugin {

    public SuperHardcoreSurvival superHardcoreSurvival = this;
    public static final String NAME_PLUGIN = "SuperHardcoreSurvival";
    public static final String VERSION = "1.4-RELEASE";
    public static final int HEAVY_RAIN_TIME = 60*50;
    public static final NamespacedKey PLAYER_DEATH_LEVEL = new NamespacedKey("super_hardcore_survival","player_death_level");
    public static final NamespacedKey ELAPSED_TIME = new NamespacedKey("super_hardcore_survival","elapsed_time");
    public static final NamespacedKey MAX_TIME = new NamespacedKey("super_hardcore_survival","max_time");
    public static final NamespacedKey HEAVYRAIN_ON = new NamespacedKey("super_hardcore_survival","heavyrain_on");
    public Logger logger;
    public Data data;
    public HeavyRain heavyRain;
    public ConfigFile configFile;

    @Override
    public void onLoad() {
        configFile = new ConfigFile(this);
        logger = Bukkit.getLogger();
        data = new Data();

        if(!getServer().isHardcore()) {
            logger.info(ColorText.translate("&cEl servidor no esta en modo HARDCORE. Desactivando plugin..."));
            getPluginLoader().disablePlugin(this);
        } else {
            logger.info(ColorText.translate("&aEl servidor esta en HARDCORE. Activando plugin..."));
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        heavyRain = new HeavyRain(this);

        getServer().getPluginManager().registerEvents(new onDeath(this),this);
        getServer().getPluginManager().registerEvents(new onHeavyRain(this),this);
        getServer().getPluginManager().registerEvents(new onPlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new onServerLoad(this),this);
        getServer().getPluginManager().registerEvents(new onSpawn(),this);
        getServer().getPluginManager().registerEvents(new onPlayerWithLava(),this);

        getCommand("superhardcoresurvival").setExecutor(new CommandMain(this));
        getCommand("superhardcoresurvival").setTabCompleter(new TabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        setPersistentData(findArmorStand(this),data.ELAPSED_TIME,ELAPSED_TIME);
        setPersistentData(findArmorStand(this),data.MAX_TIME,MAX_TIME);
        setPersistentData(findArmorStand(this),data.isHeavyRain ? 1 : 0,HEAVYRAIN_ON);
        logger.info(ColorText.translate("&aDatos guardados."));
    }

    public void setPersistentData(Entity entity, int value, NamespacedKey namespacedKey) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.INTEGER, value);
    }

    public int getPersistentData(Entity entity,NamespacedKey namespacedKey) {
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        if (dataContainer.has(namespacedKey, PersistentDataType.INTEGER)) {
            return dataContainer.get(namespacedKey, PersistentDataType.INTEGER);
        }
        return 0; // Valor predeterminado si el dato no está establecido
    }

    public ArmorStand findArmorStand(SuperHardcoreSurvival plugin) {
        World world = plugin.getServer().getWorld(plugin.configFile.getConfigFile().getString("heavyrain_world"));
        if(world != null) {
            Collection<ArmorStand> entities = world.getEntitiesByClass(ArmorStand.class);

            for(ArmorStand armorStand : entities) {
                if(armorStand.getScoreboardTags().contains("armor_stand_hardcore")) {
                    return armorStand;
                }
            }
        }
        return null;
    }
}
