package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.phanesan.superhardcoresurvival.Items;


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

            case WITHER:
                ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 3, false, false));
                break;

            case HOGLIN:
            case ZOGLIN:
                ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,PotionEffect.INFINITE_DURATION,1,false,false));
                ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,PotionEffect.INFINITE_DURATION,2,false,false));
                break;
            case PIG:
                if(randomBoolean(70)) {
                    Entity hoglin = e.getEntity().getWorld().spawnEntity(e.getLocation(),EntityType.HOGLIN);
                    hoglin.setPersistent(true);
                    e.getEntity().teleport(e.getLocation().subtract(0,400,0));
                }
                break;
        }
    }

    //  CAMBIO 3
    @EventHandler
    public void onGenerateChunk(ChunkPopulateEvent e) {
        Entity[] entities = e.getChunk().getEntities();
        for(Entity entity : entities) {
            EntityType entityType = entity.getType();
            switch(entityType) {
                case PIGLIN_BRUTE:
                    if(randomBoolean(7)) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.setGlowing(true);

                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 3, false, false));
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, PotionEffect.INFINITE_DURATION, 3, false, false));

                        livingEntity.getEquipment().setItemInOffHand(Items.getBookLife());
                        livingEntity.getEquipment().setItemInOffHandDropChance(0.6f);
                    }
                    break;
                case PIG:
                    if(randomBoolean(70)) {
                        Entity hoglin = entity.getWorld().spawnEntity(entity.getLocation(),EntityType.HOGLIN);
                        hoglin.setPersistent(true);
                        entity.teleport(entity.getLocation().subtract(0,400,0));
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void transformEntity(EntityTransformEvent e) {
        if(e.getTransformedEntity() instanceof Zoglin) {
            Zoglin zoglin = (Zoglin) e.getTransformedEntity();
            zoglin.setGlowing(true);
            zoglin.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,PotionEffect.INFINITE_DURATION,1,false,false));
            zoglin.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,PotionEffect.INFINITE_DURATION,12,false,false));
            zoglin.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,PotionEffect.INFINITE_DURATION,1,false,false));
        }
    }

    public boolean randomBoolean(double probability) {
        return (Math.random()) < (probability/100);
    }

}
