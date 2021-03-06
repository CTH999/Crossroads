package com.Da_Technomancer.crossroads.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.Da_Technomancer.crossroads.API.Properties;
import com.Da_Technomancer.crossroads.items.ModItems;
import com.Da_Technomancer.crossroads.tileentities.HamsterWheelTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HamsterWheel extends BlockContainer{

	private static final AxisAlignedBB[] BB = new AxisAlignedBB[]{new AxisAlignedBB(0, 0, .5D, 1, 1, 1), new AxisAlignedBB(0, 0, 0, .5D, 1, 1), new AxisAlignedBB(0, 0, 0, 1, 1, .5D), new AxisAlignedBB(.5D, 0, 0, 1, 1, 1)};
	
	protected HamsterWheel(){
		super(Material.IRON);
		String name = "hamster_wheel";
		setUnlocalizedName(name);
		setHardness(2);
		setRegistryName(name);
		setCreativeTab(ModItems.tabCrossroads);
		ModBlocks.toRegister.add(this);
		ModBlocks.blockAddQue(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		return new HamsterWheelTileEntity();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return BB[Math.max(state.getValue(Properties.FACING).getHorizontalIndex(), 0)];
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand){
		return getDefaultState().withProperty(Properties.FACING, placer.getHorizontalFacing());
	}

	@Override
	public int damageDropped(IBlockState state){
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] {Properties.FACING});
	}

	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(Properties.FACING, EnumFacing.getFront(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(Properties.FACING).getIndex();
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> list, @Nullable Entity entityIn, boolean p_185477_7_){
		addCollisionBoxToList(pos, entityBox, list, BB[Math.max(state.getValue(Properties.FACING).getHorizontalIndex(), 0)]);
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side){
		return side == world.getBlockState(pos).getValue(Properties.FACING);
	}
}
