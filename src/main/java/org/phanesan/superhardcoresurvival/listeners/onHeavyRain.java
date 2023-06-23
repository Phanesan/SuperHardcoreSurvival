package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.phanesan.superhardcoresurvival.SuperHardcoreSurvival;

public class onHeavyRain implements Listener {

    private final SuperHardcoreSurvival main;

    public onHeavyRain(SuperHardcoreSurvival main) {
        this.main = main;
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(main.data.isHeavyRain) {
            switch(e.getEntityType()) {
                case WITHER_SKELETON:
                case STRAY:
                case ENDERMAN:
                case SKELETON:
                case DROWNED:
                case HUSK:
                case ZOMBIE_VILLAGER:
                case ZOMBIE:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;

                case SPIDER:
                case CAVE_SPIDER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999*20, 1, false, false));
                    break;

                case CREEPER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    ((Creeper) e.getEntity()).setExplosionRadius(6);
                    ((Creeper) e.getEntity()).setPowered(true);
                    break;

                case MAGMA_CUBE:
                case SLIME:
                    ((Slime) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 2, false, false));
                    ((Slime) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 3, false, false));
                    break;

                case WITCH:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;

                case PHANTOM:
                    ((Phantom) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 2, false, false));
                    ((Phantom) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999*20, 1, false, false));
                    break;

                case ENDERMITE:
                case SILVERFISH:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999*20, 1, false, false));
                    break;

                case ZOMBIFIED_PIGLIN:
                case PIGLIN_BRUTE:
                case PIGLIN:
                case GHAST:
                case BLAZE:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;

                case PILLAGER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    break;

                case VINDICATOR:
                case RAVAGER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    break;

                case VEX:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                    break;

                case SHULKER:
                case ZOGLIN:
                case HOGLIN:
                case EVOKER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;

                case GUARDIAN:
                case WITHER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                    break;

                case ELDER_GUARDIAN:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                    break;

                case WARDEN:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;
            }
        }
    }

}
