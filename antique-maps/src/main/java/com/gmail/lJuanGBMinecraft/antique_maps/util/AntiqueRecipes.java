package com.gmail.lJuanGBMinecraft.antique_maps.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.gmail.lJuanGBMinecraft.antique_maps.AntiqueMaps;
import com.gmail.lJuanGBMinecraft.antique_maps.util.data.Config;

/**
 * Used to declare the recipes used by the plugin
 * 
 * @author lJuanGB
 */
public class AntiqueRecipes 
{
	public static Recipe ANTIQUE_MAP;
	
	public static void initialize()
	{
		ANTIQUE_MAP = addMapIngredients(new ShapelessRecipe( 
					new NamespacedKey(AntiqueMaps.getInstance(), "antique_map"),
					AntiqueItems.ANTIQUE_MAP) );
	}

	
	private static Recipe addMapIngredients(ShapelessRecipe recipe) 
	{		
		List<Material> materials = getMaterials( Config.map_recipe.get() );
		
		if (materials.contains(null))
		{
			materials = getMaterials( Config.map_recipe.getDefault() );
		}
		
		for (Material mat : materials)
		{
			recipe.addIngredient(mat);
		}
		
		return recipe;
	}
	
	private static List<Material> getMaterials(List<String> list)
	{
		List<Material> result = new ArrayList<>();
		
		for (String s : list)
		{
			if (s == null)
			{
				result.add(null);
			}
			else
			{
				result.add( Material.getMaterial(s.toUpperCase()) );
			}
		}
		
		return result;
	}
}
