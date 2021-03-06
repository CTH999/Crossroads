package com.Da_Technomancer.crossroads.tileentities.magic;

import java.awt.Color;

import javax.annotation.Nullable;

import com.Da_Technomancer.crossroads.API.magic.BeamRenderTE;
import com.Da_Technomancer.crossroads.API.magic.MagicUnit;
import com.Da_Technomancer.crossroads.API.packets.ModPackets;
import com.Da_Technomancer.crossroads.API.packets.SendIntToClient;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class BeaconHarnessTileEntity extends BeamRenderTE{

	public float[] renderOld = new float[8];
	public float[] renderNew = new float[8];
	public boolean renderSet = false;

	private boolean running;
	private int cycles;

	private boolean invalid(Color col, boolean colorSafe, @Nullable MagicUnit last){
		if(!colorSafe){
			if(last == null || last.getVoid() != 0 || (col.getRed() != 0 && last.getEnergy() != 0) || (col.getGreen() != 0 && last.getPotential() != 0) || (col.getBlue() != 0 && last.getStability() != 0)){
				return true;
			}
		}

		return world.getBlockState(pos.offset(EnumFacing.DOWN, 2)).getBlock() != Blocks.BEACON || !world.isAirBlock(pos.offset(EnumFacing.DOWN, 1));
	}

	public void trigger(){
		if(!running && !invalid(null, true, null)){
			running = true;
			ModPackets.network.sendToAllAround(new SendIntToClient(0, 0x1FFFFFF, pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 512));
		}
	}

	@Override
	public NBTTagCompound getUpdateTag(){
		NBTTagCompound nbt = super.getUpdateTag();
		nbt.setBoolean("runC", running);
		return nbt;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("run", running);
		nbt.setInteger("cycle", cycles);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		running = nbt.getBoolean("run");
		cycles = nbt.getInteger("cycle");
	}

	@Override
	protected void doEmit(MagicUnit toEmit){
		if(running){
			++cycles;
			cycles %= 120;
			Color col = Color.getHSBColor(((float) cycles) / 120F, 1, 1);
			if(invalid(col, cycles < 0 || cycles % 40 < 8, toEmit)){
				running = false;
				ModPackets.network.sendToAllAround(new SendIntToClient(0, 0, pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 512));
				cycles = -9;

				beamer[1].emit(null, world);
				return;
			}
			if(cycles >= 0){
				MagicUnit out = new MagicUnit(col.getRed(), col.getGreen(), col.getBlue(), 0);
				out = out.mult(512D / ((double) out.getPower()), false);

				beamer[1].emit(out, world);
			}
		}
	}

	@Override
	protected boolean[] inputSides(){
		return new boolean[]{false, false, true, true, true, true};
	}

	@Override
	protected boolean[] outputSides(){
		return new boolean[]{false, true, false, false, false, false};
	}
}
