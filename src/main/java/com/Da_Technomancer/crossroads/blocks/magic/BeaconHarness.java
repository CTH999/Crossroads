package com.Da_Technomancer.crossroads.blocks.magic;

import com.Da_Technomancer.crossroads.API.magic.BeamRenderTE;
import com.Da_Technomancer.crossroads.blocks.ModBlocks;
import com.Da_Technomancer.crossroads.items.ModItems;
import com.Da_Technomancer.crossroads.tileentities.magic.BeaconHarnessTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BeaconHarness extends BlockContainer{

	public BeaconHarness(){
		super(Material.GLASS);
		String name = "beacon_harness";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.tabCrossroads);
		setHardness(3);
		ModBlocks.toRegister.add(this);
		ModBlocks.blockAddQue(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		return new BeaconHarnessTileEntity();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
		if(worldIn.isBlockPowered(pos) && worldIn.getTileEntity(pos) instanceof BeaconHarnessTileEntity){
			((BeaconHarnessTileEntity) worldIn.getTileEntity(pos)).trigger();
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof BeamRenderTE){
			((BeamRenderTE) te).refresh();
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
	
	@Override
	public int getLightOpacity(IBlockState state){
		return 15;
	}
}
