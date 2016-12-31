package com.Da_Technomancer.crossroads.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BasicItem extends Item{

	/*
	 * This class is capable of generating an item with no special properties.
	 * Either input a name for the item, or specify a name and an oreDict for
	 * the item.
	 */

	public BasicItem(String name){
		this(name, null);
	}

	public BasicItem(String name, String oreDict){
		this(name, oreDict, true);
	}
	
	public BasicItem(String name, String oreDict, boolean tab){
		setUnlocalizedName(name);
		setRegistryName(name);
		GameRegistry.register(this);
		if(tab){
			setCreativeTab(ModItems.tabCrossroads);
		}
		if(oreDict != null){
			OreDictionary.registerOre(oreDict, this);
		}
		ModItems.itemAddQue(this);
	}
}
