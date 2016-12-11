package com.Da_Technomancer.crossroads.tileentities.rotary;

import com.Da_Technomancer.crossroads.API.Capabilities;
import com.Da_Technomancer.crossroads.API.Properties;
import com.Da_Technomancer.crossroads.API.rotary.IAxleHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class RotaryDrillTileEntity extends TileEntity implements ITickable{

	private static final DamageSource DRILL = new DamageSource("drill").setDamageBypassesArmor();
	
	private int ticksExisted = 0;
	private final double ENERGYUSE = .5D;
	private final double SPEEDPERHARDNESS = .1D;

	private float angle = 0;

	@Override
	public void update(){
		if(worldObj.isRemote){
			EnumFacing facing = worldObj.getBlockState(pos).getValue(Properties.FACING);
			if(worldObj.getTileEntity(pos.offset(facing.getOpposite())) != null && worldObj.getTileEntity(pos.offset(facing.getOpposite())).hasCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing)){
				angle = (float) worldObj.getTileEntity(pos.offset(facing.getOpposite())).getCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing).getAngle();
			}

			return;
		}

		EnumFacing facing = worldObj.getBlockState(pos).getValue(Properties.FACING);
		IAxleHandler handler;
		if(worldObj.getTileEntity(pos.offset(facing.getOpposite())) != null && worldObj.getTileEntity(pos.offset(facing.getOpposite())).hasCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing) && Math.abs((handler = worldObj.getTileEntity(pos.offset(facing.getOpposite())).getCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing)).getMotionData()[1]) >= ENERGYUSE){
			handler.addEnergy(-ENERGYUSE, false, false);
			if(++ticksExisted % 10 == 0){
				worldObj.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.BLOCKS, .2F, .5F);
				if(!worldObj.isAirBlock(pos.offset(facing))){
					if(Math.abs(handler.getMotionData()[0]) >= worldObj.getBlockState(pos.offset(facing)).getBlockHardness(worldObj, pos.offset(facing)) * SPEEDPERHARDNESS){
						worldObj.destroyBlock(pos.offset(facing), true);
					}
				}else if(worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.offset(facing)), EntitySelectors.IS_ALIVE) != null){
					for(EntityLivingBase ent : worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.offset(facing)), EntitySelectors.IS_ALIVE)){
						ent.attackEntityFrom(DRILL, (float) Math.abs(handler.getMotionData()[0] / SPEEDPERHARDNESS));
					}
				}
			}
		}
	}

	public float getAngle(){
		return angle;
	}
}
