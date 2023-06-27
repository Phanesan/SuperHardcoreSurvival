package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onSpawn implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        e.getEntity().addScoreboardTag("overpowered");
        switch(e.getEntityType()) {
            //  CAMBIO 1
            case SKELETON:
            case DROWNED:
            case HUSK:
            case ZOMBIE_VILLAGER:
            case ZOMBIE:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                break;

            //  CAMBIO 2
            case PIGLIN:
            case PIGLIN_BRUTE:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999*20, 1, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                break;
        }
    }

}
