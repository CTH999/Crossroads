package com.Da_Technomancer.crossroads.API.effects.goggles;

import java.util.ArrayList;

import com.Da_Technomancer.crossroads.API.IInfoTE;
import com.Da_Technomancer.crossroads.API.MiscOp;
import com.Da_Technomancer.crossroads.API.enums.GoggleLenses;
import com.Da_Technomancer.crossroads.API.packets.ModPackets;
import com.Da_Technomancer.crossroads.API.packets.SendFieldsToClient;
import com.Da_Technomancer.crossroads.API.technomancy.FieldWorldSavedData;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RubyGoggleEffect implements IGoggleEffect{

	@Override
	public void armorTick(World world, EntityPlayer player, ArrayList<String> chat, RayTraceResult ray){
		//Notice that null side is used for finding the capability. This is because most blocks with heat support only have one side with the capability, which would normally be inaccessible. 
		if(ray != null){
			TileEntity te = world.getTileEntity(ray.getBlockPos());
			
			if(te instanceof IInfoTE){
				((IInfoTE) te).addInfo(chat, GoggleLenses.RUBY, player, ray.sideHit);
			}
		}

		if(world.getTotalWorldTime() % 5 == 1){
			if(FieldWorldSavedData.get(world).fieldNodes.containsKey(MiscOp.getLongFromChunkPos(new ChunkPos(player.getPosition())))){
				ModPackets.network.sendTo(new SendFieldsToClient(FieldWorldSavedData.get(world).fieldNodes.get(MiscOp.getLongFromChunkPos(new ChunkPos(player.getPosition())))[0], (byte) 0, MiscOp.getLongFromChunkPos(new ChunkPos(player.getPosition()))), (EntityPlayerMP) player);
			}else{
				ModPackets.network.sendTo(new SendFieldsToClient(new byte[1][1], (byte) -1, MiscOp.getLongFromChunkPos(new ChunkPos(player.getPosition()))), (EntityPlayerMP) player);
			}
		}
	}
}