package com.gmail.lJuanGBMinecraft.antique_maps.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Lang;

/**
 * Used to declare the items used by the plugin
 * 
 * @author lJuanGB
 */
public class AntiqueItems 
{
	/**
	 * Empty Antique Map
	 */
	public static ItemStack ANTIQUE_MAP;
	
	public static boolean isItem(ItemStack item1, ItemStack item2)
	{
		if (!item1.hasItemMeta() || !item2.hasItemMeta())
		{
			return false;
		}
		
		PersistentDataContainer cont1 = item1.getItemMeta().getPersistentDataContainer();
		PersistentDataContainer cont2 = item2.getItemMeta().getPersistentDataContainer();
		
		if (!cont1.has(itemKey, PersistentDataType.STRING) || !cont1.has(itemKey, PersistentDataType.STRING))
		{
			return false;
		}

		return cont1.get(itemKey, PersistentDataType.STRING).equals(cont2.get(itemKey, PersistentDataType.STRING));
	}
	
	public static void initialize()
	{
		ANTIQUE_MAP = antique_map_item();
	}
	
	
	
	
	private static NamespacedKey itemKey = new NamespacedKey(AntiqueMaps.getInstance(), "itemName");
			
	private static ItemStack antique_map_item()
	{
		ItemStack item = new ItemStack(Material.MAP);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName( Lang.text("empty_antique_map_item_name") );
		meta.getPersistentDataContainer().set(itemKey, PersistentDataType.STRING, "empty_antique_map");
		
		item.setItemMeta(meta);
		return item;
	}
}