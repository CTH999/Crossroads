package com.Da_Technomancer.crossroads.API.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnchantEffect implements IEffect{

	private static final Random RAND = new Random();
	
	@Override
	public void doEffect(World worldIn, BlockPos pos, double mult){
		mult = Math.min(mult, 45);
		ArrayList<EntityItem> items = (ArrayList<EntityItem>) worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.add(-mult, -mult, -mult), pos.add(mult, mult, mult)), EntitySelectors.IS_ALIVE);
		if(items != null && items.size() != 0){
			for(EntityItem ent : items){
				List<EnchantmentData> ench = EnchantmentHelper.buildEnchantmentList(RAND, ent.getItem(), (int) mult, mult >= 32);
				
				if(ench == null || ent.getItem().isItemEnchanted()){
					continue;
				}
				
				if(ent.getItem().getItem() == Items.BOOK){
					ent.setItem(new ItemStack(Items.ENCHANTED_BOOK, 1));
				}
				
				for(EnchantmentData datum : ench){
					if(ent.getItem().getItem() == Items.ENCHANTED_BOOK){
						//While vanilla behavior when enchanting books is to put on 1 fewer enchantments, for the EnchantEffect this does not occur. THIS IS NOT A BUG.
						ItemEnchantedBook.addEnchantment(ent.getItem(), datum);
					}else{
						ent.getItem().addEnchantment(datum.enchantment, datum.enchantmentLevel);
					}
				}
			}
		}
	}

	public static class DisenchantEffect implements IEffect{

		@Override
		public void doEffect(World worldIn, BlockPos pos, double mult){
			ArrayList<EntityItem> items = (ArrayList<EntityItem>) worldIn.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.add(-mult, -mult, -mult), pos.add(mult, mult, mult)), EntitySelectors.IS_ALIVE);
			if(items != null && items.size() != 0){
				for(EntityItem ent : items){
					if(ent.getItem().getTagCompound() != null && ent.getItem().getTagCompound().hasKey("ench")){
						ent.getItem().getTagCompound().removeTag("ench");
					}
				}
			}
		}	
	}
}
