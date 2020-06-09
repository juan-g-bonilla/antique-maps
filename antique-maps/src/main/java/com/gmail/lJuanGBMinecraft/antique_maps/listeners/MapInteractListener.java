package com.gmail.lJuanGBMinecraft.antique_maps.listeners;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.biomes.BiomeDetector;
import com.gmail.lJuanGBMinecraft.antique_maps.biomes.EndBiomeDetector;
import com.gmail.lJuanGBMinecraft.antique_maps.biomes.OverworldBiomeDetector;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.MapsManager;
import com.gmail.lJuanGBMinecraft.antique_maps.maps.PlayerMarkerRenderer;
import com.gmail.lJuanGBMinecraft.antique_maps.util.AntiqueItems;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Config;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Lang;

/**
 * Handler when players right click map
 * 
 * @author lJuanGB
 */
public class MapInteractListener implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void onMapOpen(PlayerInteractEvent event)
	{		
		ItemStack item = event.getItem();
		
		if (item == null)
		{
			return;
		}
			
		Player player = event.getPlayer();

		if (AntiqueItems.isItem(AntiqueItems.ANTIQUE_MAP, item)) // If empty map
		{
			final BiomeDetector det;
			switch (player.getWorld().getEnvironment())
			{
			//case NETHER:
				//break;
			case NORMAL:
				det = new OverworldBiomeDetector();
				break;
			case THE_END:
				det = new EndBiomeDetector();
				break;
			default:
				player.sendMessage(ChatColor.AQUA + Lang.text("incorrect_dimension_message"));
				event.setCancelled(true);
				return;			
			}
			
			boolean creative = player.getGameMode().equals(GameMode.CREATIVE);
			PlayerInventory inv = player.getInventory();
			int firstEmpty = inv.firstEmpty();
			
			if (creative && firstEmpty < 0)
			{
				event.setCancelled(true);
				return;
			}
			
			boolean mainhand = event.getHand().equals(EquipmentSlot.HAND);

			ItemStack toGive = item.clone();
			toGive.setAmount( toGive.getAmount() - 1);
			
			/*
			 * Set amount of empty maps in hand to ensure the map is generated on
			 * the hand
			 */
			if (!creative)
			{
				item.setAmount(1); 
			}

			// Run task on next tick, when player has filled map in hand
			Bukkit.getScheduler().runTaskLater(AntiqueMaps.getInstance(), (bt) ->
			{			
				ItemStack map = null;
				
				if (creative)
				{
					map = player.getInventory().getItem(firstEmpty);
				}
				else
				{
					map = mainhand ? inv.getItemInMainHand() : inv.getItemInOffHand(); 
				}
				
				if (map == null || !map.hasItemMeta())
				{
					return;
				}
				
				ItemMeta meta = map.getItemMeta();
				
				if (!(meta instanceof MapMeta))
				{
					return;
				}
				
				meta.setDisplayName(Lang.text("antique_map_item_name"));
				
				map.setItemMeta(meta);
				
				MapView view = ((MapMeta) meta).getMapView();
				
				if (Config.async.get())
				{
					MapsManager.asyncRender(view.getId(), 
							view.getCenterX(), view.getCenterZ(), view.getWorld(), det);
				}
				else
				{
					MapsManager.render(view.getId(), 
							view.getCenterX(), view.getCenterZ(), view.getWorld(), det);
				}

				if (!creative)
				{
					// Since we set items in hand to 1, give back items removed
					Map<Integer, ItemStack> toDrop = inv.addItem(toGive); 
					Location loc = player.getLocation();
					
					// Drop items that did not fit
					for (Integer i : toDrop.keySet())
					{
						if (i == null) continue;
						
						loc.getWorld().dropItem(loc, toDrop.get(i));
					}
				}

			},0);
		}
		
		if (item.getType().equals(Material.FILLED_MAP))
		{
			if (!Config.tracking_allowChange.get()) return;
			
			if (!player.isSneaking()) return;
				
			MapMeta meta = (MapMeta) item.getItemMeta();	
			MapView view = meta.getMapView();

			for (MapRenderer renderer : view.getRenderers())
			{
				if (renderer instanceof PlayerMarkerRenderer)
				{
					PlayerMarkerRenderer mark = (PlayerMarkerRenderer) renderer;
					MapsManager.update(view.getId(), null, !mark.isTracking());
					return;
				}
			}
		}		
	}
}
