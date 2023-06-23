package org.phanesan.superhardcoresurvival;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private final Plugin plugin;
    // Config file
    private FileConfiguration config;
    private File configFile;

    public ConfigFile(Plugin plugin){
        this.plugin = plugin;

        configFile = new File(plugin.getDataFolder(), "config.yml");

        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfigFile(){
        return config;
    }

}
