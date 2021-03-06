package com.Da_Technomancer.crossroads.fluids;

import com.Da_Technomancer.crossroads.Main;
import com.Da_Technomancer.crossroads.blocks.ModBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockMoltenCopshowium extends BlockFluidClassic{

	protected static final FluidMoltenCopshowium MOLTEN_COPSHOWIUM = new FluidMoltenCopshowium();

	public BlockMoltenCopshowium(){
		super(MOLTEN_COPSHOWIUM, Material.LAVA);
		MOLTEN_COPSHOWIUM.setBlock(this);
		String name = "molten_copshowium";
		setUnlocalizedName(name);
		setRegistryName(name);
		ModBlocks.toRegister.add(this);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos){
		return 15;
	}

	/**
	 * For normal use.
	 */
	public static Fluid getMoltenCopshowium(){
		return FluidRegistry.getFluid("copshowium");
	}

	private static class FluidMoltenCopshowium extends Fluid{

		private FluidMoltenCopshowium(){
			super("copshowium", new ResourceLocation(Main.MODID, "blocks/moltencopshowium_still"), new ResourceLocation(Main.MODID, "blocks/moltencopshowium_flow"));
			setDensity(3000);
			setTemperature(6000);
			setViscosity(1300);
		}
	}
}
