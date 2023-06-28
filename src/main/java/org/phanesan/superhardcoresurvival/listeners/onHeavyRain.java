package org.phanesan.superhardcoresurvival.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
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
        if(main.data.isHeavyRain && !e.getEntity().getScoreboardTags().contains("overpowered")) {
            switch(e.getEntityType()) {
                case WITHER_SKELETON:
                case STRAY:
                case ENDERMAN:
                case SKELETON:
                case DROWNED:
                case HUSK:
                case ZOMBIE_VILLAGER:
                case ZOMBIE:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));

                    if(e.getEntityType() != EntityType.ENDERMAN && randomBoolean(30)) {
                        LivingEntity entity = (LivingEntity) e.getEntity();
                        entity.setGlowing(true);

                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,9999*20,2,false,false));

                        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                        helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE,4);
                        ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);

                        entity.getEquipment().setHelmet(helmet);
                        entity.getEquipment().setBoots(boots);

                        if(randomBoolean(3)) {
                            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED,8);
                            entity.getEquipment().setItemInMainHand(pickaxe);
                            entity.getEquipment().setItemInMainHandDropChance(1f);
                        }
                    }
                    break;

                case SPIDER:
                case CAVE_SPIDER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999*20, 1, false, false));
                    break;

                case CREEPER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    ((Creeper) e.getEntity()).setExplosionRadius(6);
                    ((Creeper) e.getEntity()).setPowered(true);
                    break;

                case MAGMA_CUBE:
                case SLIME:
                    ((Slime) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    ((Slime) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 3, false, false));
                    break;

                case WITCH:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                    break;

                case PHANTOM:
                    ((Phantom) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    ((Phantom) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 9999*20, 1, false, false));
                    break;

                case ENDERMITE:
                case SILVERFISH:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 3, false, false));
                    break;

                case ZOMBIFIED_PIGLIN:
                case PIGLIN_BRUTE:
                case PIGLIN:
                case GHAST:
                case BLAZE:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 2, false, false));
                    break;

                case PILLAGER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    break;

                case VINDICATOR:
                case RAVAGER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 1, false, false));
                    break;

                case VEX:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 1, false, false));
                    break;

                case HOGLIN:
                    ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,PotionEffect.INFINITE_DURATION,1,false,false));
                    break;

                case SHULKER:
                case ZOGLIN:
                case EVOKER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 1, false, false));
                    break;

                case GUARDIAN:
                case WITHER:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 3, false, false));
                    break;

                case ELDER_GUARDIAN:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999*20, 2, false, false));
                    break;

                case WARDEN:
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 2, false, false));
                    ((Monster) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999*20, 3, false, false));
                    break;
            }
        }
    }

    public boolean randomBoolean(double probability) {
        return (Math.random()) < (probability/100);
    }

}
