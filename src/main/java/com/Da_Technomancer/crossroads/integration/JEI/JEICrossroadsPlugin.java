package com.Da_Technomancer.crossroads.integration.JEI;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.Da_Technomancer.crossroads.blocks.ModBlocks;
import com.Da_Technomancer.crossroads.gui.container.DetailedCrafterContainer;
import com.Da_Technomancer.crossroads.items.crafting.RecipeHolder;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEICrossroadsPlugin implements IModPlugin{

	@Override
	public void register(@Nonnull IModRegistry registry){

		registry.addRecipeCatalyst(new ItemStack(ModBlocks.grindstone, 1), GrindstoneCategory.ID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.fluidCoolingChamber, 1), FluidCoolingCategory.ID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.heatingCrucible, 1), HeatingCrucibleCategory.ID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.heatExchanger, 1), HeatExchangerCategory.ID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.insulHeatExchanger, 1), HeatExchangerCategory.ID);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.detailedCrafter, 1), DetailedCrafterCategory.ID);
		
		for(Entry<String, ArrayList<IRecipeWrapper>> recipeGroup : RecipeHolder.JEIWrappers.entrySet()){
			registry.addRecipes(recipeGroup.getValue(), recipeGroup.getKey());
		}
		
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(DetailedCrafterContainer.class, DetailedCrafterCategory.ID, 0, 9, 10, 36);
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(DetailedCrafterContainer.class, VanillaRecipeCategoryUid.CRAFTING, 0, 9, 10, 36);
	}

	@Override
	public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime){

	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry){
		
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry){
		
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry){
		registry.addRecipeCategories(new GrindstoneCategory(registry.getJeiHelpers().getGuiHelper()), new FluidCoolingCategory(registry.getJeiHelpers().getGuiHelper()), new HeatingCrucibleCategory(registry.getJeiHelpers().getGuiHelper()), new HeatExchangerCategory(registry.getJeiHelpers().getGuiHelper()), new DetailedCrafterCategory(registry.getJeiHelpers().getGuiHelper()));	
	}
}
