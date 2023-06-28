package org.phanesan.superhardcoresurvival;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Items {

    public static ItemStack getBookLife() {
        ItemStack item = new ItemStack(Material.BOOK);
        item.setAmount(1);
        ItemMeta meta = item.getItemMeta();

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&b&lLibro Arcano: &6&lVida"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&',"&bAl tener este libro en tu mano izquierda"));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&bobtienes otra barra de corazones"));
        meta.setLore(lore);

        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),"generic.max_health",20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
        item.setItemMeta(meta);

        return item;
    }

}
