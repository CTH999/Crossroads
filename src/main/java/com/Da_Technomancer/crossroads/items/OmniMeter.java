package com.Da_Technomancer.crossroads.items;

import java.util.ArrayList;

import com.Da_Technomancer.crossroads.API.Capabilities;
import com.Da_Technomancer.crossroads.API.IInfoDevice;
import com.Da_Technomancer.crossroads.API.IInfoTE;
import com.Da_Technomancer.crossroads.API.MiscOp;
import com.Da_Technomancer.crossroads.API.enums.MagicElements;
import com.Da_Technomancer.crossroads.API.magic.BeamRenderTEBase;
import com.Da_Technomancer.crossroads.API.magic.MagicUnit;
import com.Da_Technomancer.crossroads.API.packets.ModPackets;
import com.Da_Technomancer.crossroads.API.packets.SendChatToClient;
import com.Da_Technomancer.crossroads.API.packets.StoreNBTToClient;
import com.Da_Technomancer.crossroads.API.rotary.IAxleHandler;
import com.Da_Technomancer.crossroads.tileentities.RatiatorTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class OmniMeter extends Item implements IInfoDevice{

	public OmniMeter(){
		String name = "omnimeter";
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.tabCrossroads);
		ModItems.toRegister.add(this);
		ModItems.itemAddQue(this);
	}
	
	/**
	 * Value chosen at random.
	 */
	private static final int CHAT_ID = 279478;

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		TileEntity te = worldIn.getTileEntity(pos);
		
		if(!worldIn.isRemote){
			if(te != null){
				ArrayList<String> chat = new ArrayList<String>();

				if(te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)){
					IFluidHandler pipe = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
					chat.add(pipe.getTankProperties().length + " internal tank" + (pipe.getTankProperties().length == 1 ? "." : "s."));
					for(IFluidTankProperties tank : pipe.getTankProperties()){
						chat.add("Amount: " + (tank.getContents() == null ? 0 : tank.getContents().amount) + ", Type: " + (tank.getContents() == null ? "None" : tank.getContents().getLocalizedName()) + ", Capacity: " + tank.getCapacity());
					}
				}

				if(te.hasCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing.getOpposite())){
					IAxleHandler gear = te.getCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing.getOpposite());
					chat.add("Speed: " + MiscOp.betterRound(gear.getMotionData()[0], 3) + ", Energy: " + MiscOp.betterRound(gear.getMotionData()[1], 3) + ", Power: " + MiscOp.betterRound(gear.getMotionData()[2], 3) + ", Mass: " + gear.getPhysData()[0] + ", I: " + gear.getPhysData()[1]);
				}else if(te.hasCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing)){
					IAxleHandler gear = te.getCapability(Capabilities.AXLE_HANDLER_CAPABILITY, facing);
					chat.add("Speed: " + MiscOp.betterRound(gear.getMotionData()[0], 3) + ", Energy: " + MiscOp.betterRound(gear.getMotionData()[1], 3) + ", Power: " + MiscOp.betterRound(gear.getMotionData()[2], 3) + ", Mass: " + gear.getPhysData()[0] + ", I: " + gear.getPhysData()[1]);
				}

				if(te instanceof BeamRenderTEBase){
					MagicUnit[] mag = ((BeamRenderTEBase) te).getLastFullSent();
					if(mag != null){
						for(int i = 0; i < mag.length; i++){
							MagicUnit check = mag[i];
							if(check != null){
								NBTTagCompound nbt = MiscOp.getPlayerTag(playerIn);
								if(!nbt.hasKey("elements")){
									nbt.setTag("elements", new NBTTagCompound());
								}
								nbt = nbt.getCompoundTag("elements");

								if(!nbt.hasKey(MagicElements.getElement(check).name())){
									nbt.setBoolean(MagicElements.getElement(check).name(), true);
									playerIn.sendMessage(new TextComponentString(TextFormatting.BOLD.toString() + "New Element Discovered: " + MagicElements.getElement(check).toString()));
									StoreNBTToClient.syncNBTToClient((EntityPlayerMP) playerIn, false);
								}

								chat.add(EnumFacing.getFront(i).toString() + ": " + check.toString());
							}
						}
					}
				}
				
				if(te instanceof IInfoTE){
					((IInfoTE) te).addInfo(chat, this, playerIn, facing);
				}
				if(!chat.isEmpty()){
					String out = "";
					for(String line : chat){
						if(!out.equals("")){
							out += "\n";
						}
						out += line;
					}
					ModPackets.network.sendTo(new SendChatToClient(out, CHAT_ID), (EntityPlayerMP) playerIn);
				}
			}
		}
		
		return te instanceof IInfoTE || te instanceof RatiatorTileEntity ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
	}
}
