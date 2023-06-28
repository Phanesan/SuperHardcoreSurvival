package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.AsyncStructureSpawnEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.phanesan.superhardcoresurvival.Items;

import java.util.Collection;

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

                //  INICIO CAMBIO 3
                if(e.getEntityType() == EntityType.PIGLIN_BRUTE && randomBoolean(7)) {
                    LivingEntity piglinBrute = (LivingEntity) e.getEntity();
                    piglinBrute.setGlowing(true);

                    piglinBrute.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,PotionEffect.INFINITE_DURATION,3,false,false));
                    piglinBrute.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,PotionEffect.INFINITE_DURATION,3,false,false));

                    piglinBrute.getEquipment().setItemInOffHand(Items.getBookLife());
                    piglinBrute.getEquipment().setItemInOffHandDropChance(0.6f);

                }
                //  CIERRE CAMBIO 3

                break;

                //  CAMBIO 3
            case VINDICATOR:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                break;
            case VEX:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                break;

            case SPIDER:
            case CAVE_SPIDER:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999*20, 1, false, false));
                break;
        }
    }

    //  CAMBIO 3
    @EventHandler
    public void onGenerateChunk(ChunkPopulateEvent e) {
        Entity[] entities = e.getChunk().getEntities();
        for(Entity entity : entities) {
            if(entity.getType() == EntityType.PIGLIN_BRUTE && randomBoolean(7)) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.setGlowing(true);

                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,PotionEffect.INFINITE_DURATION,3,false,false));
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,PotionEffect.INFINITE_DURATION,3,false,false));

                livingEntity.getEquipment().setItemInOffHand(Items.getBookLife());
                livingEntity.getEquipment().setItemInOffHandDropChance(0.6f);
            }
        }
    }

    public boolean randomBoolean(double probability) {
        return (Math.random()) < (probability/100);
    }

}
