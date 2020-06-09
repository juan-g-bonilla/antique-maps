package com.gmail.lJuanGBMinecraft.antique_maps.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;

import com.gmail.lJuanGBMinecraft.antique_maps.maps.AntiqueRenderer;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Lang;

public class PlayerInteractItemFrame implements Listener{

	@EventHandler
	public void onPlayerInteractFrame(PlayerInteractEntityEvent e)
	{
		if (!e.getRightClicked().getType().equals(EntityType.ITEM_FRAME))
		{
			return;
		}
		
		ItemFrame frame = (ItemFrame) e.getRightClicked();
		
		if (!frame.isEmpty()) return;
		
		ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
		
		if (!isAntiqueMap(hand)) return;
		
		ItemMeta meta = hand.getItemMeta();

		if (!meta.hasDisplayName()) return;
		if (!meta.getDisplayName().equals( Lang.text("antique_map_item_name") )) return;
		
		meta.setDisplayName(null);
		hand.setItemMeta(meta);
	}
	
	@EventHandler
	public void onPlayerHitFrame(EntityDamageByEntityEvent  e)
	{
		if (!e.getEntity().getType().equals(EntityType.ITEM_FRAME))
		{
			return;
		}
		
		ItemFrame frame = (ItemFrame) e.getEntity();

		ItemStack itemInFrame = frame.getItem();
		
		if (!isAntiqueMap(itemInFrame)) return;

		ItemMeta meta = itemInFrame.getItemMeta();

		if ( meta.hasDisplayName() ) return;
		
		meta.setDisplayName(Lang.text("antique_map_item_name"));
		itemInFrame.setItemMeta(meta);
		frame.setItem(itemInFrame, false);
	}
	
	private boolean isAntiqueMap(ItemStack item)
	{
		if (!item.hasItemMeta()) return false;
		
		ItemMeta meta = item.getItemMeta();
		
		if (!(meta instanceof MapMeta)) return false;
		
		if (!((MapMeta) meta).hasMapView()) return false;
		
		boolean isAntique = false;
		for (MapRenderer rend : ((MapMeta) meta).getMapView().getRenderers() )
		{
			if (rend instanceof AntiqueRenderer) isAntique = true;
		}
		
		return isAntique;
	}
}
