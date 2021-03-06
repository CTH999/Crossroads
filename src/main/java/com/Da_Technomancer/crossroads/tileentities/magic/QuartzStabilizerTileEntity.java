package com.Da_Technomancer.crossroads.tileentities.magic;

import com.Da_Technomancer.crossroads.API.Properties;
import com.Da_Technomancer.crossroads.API.magic.BeamRenderTE;
import com.Da_Technomancer.crossroads.API.magic.MagicUnit;
import com.Da_Technomancer.crossroads.API.magic.MagicUnitStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class QuartzStabilizerTileEntity extends BeamRenderTE{

	private boolean large;
	private static final int[] LIMIT = new int[] {30, 150};
	private static final int[] RATE = new int[] {6, 15};
	private MagicUnitStorage storage = new MagicUnitStorage();

	public QuartzStabilizerTileEntity(){
		super();
	}

	public QuartzStabilizerTileEntity(boolean large){
		super();
		this.large = large;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("large", large);
		storage.writeToNBT("stabMag", nbt);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		large = nbt.getBoolean("large");
		storage = MagicUnitStorage.readFromNBT("stabMag", nbt);
	}

	@Override
	protected void doEmit(MagicUnit toEmit){
		if(toEmit != null){
			if(storage.getOutput() == null){
				storage.addMagic(toEmit.mult(Math.min((double) ((LIMIT[large ? 1 : 0])) / ((double) toEmit.getPower()), 1D), true));
			}else{
				storage.addMagic(toEmit.mult(Math.min((double) ((LIMIT[large ? 1 : 0] - storage.getOutput().getPower())) / ((double) toEmit.getPower()), 1D), true));
			}
		}

		EnumFacing dir = world.getBlockState(pos).getValue(Properties.FACING);

		if(!storage.isEmpty()){
			double mult = Math.min(1, ((double) RATE[large ? 1 : 0]) / ((double) (storage.getOutput().getPower())));
			MagicUnit mag = storage.getOutput().mult(mult, true);
			storage.subtractMagic(mag);
			beamer[dir.getIndex()].emit(mag, world);

		}else{
			beamer[dir.getIndex()].emit(null, world);

		}
	}

	@Override
	protected boolean[] inputSides(){
		boolean[] out = {true, true, true, true, true, true};
		out[world.getBlockState(pos).getValue(Properties.FACING).getIndex()] = false;
		return out;
	}

	@Override
	protected boolean[] outputSides(){
		boolean[] out = new boolean[6];
		out[world.getBlockState(pos).getValue(Properties.FACING).getIndex()] = true;
		return out;
	}
} 
