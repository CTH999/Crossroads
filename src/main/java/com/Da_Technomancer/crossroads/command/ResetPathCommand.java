package com.Da_Technomancer.crossroads.command;

import com.Da_Technomancer.crossroads.API.MiscOp;
import com.Da_Technomancer.crossroads.API.packets.StoreNBTToClient;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * This command resets the player's chosen path(s), if any. 
 */
public class ResetPathCommand extends CommandBase{

	@Override
	public String getName(){
		return "resetPath";
	}

	@Override
	public String getUsage(ICommandSender sender){
		return "/resetPath [player]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException{
		if(args == null || args.length > 1){
			sender.sendMessage(new TextComponentString("Invalid # of arguments!"));
			return;
		}
		
		EntityPlayerMP target = args.length == 1 ? server.getPlayerList().getPlayerByUsername(args[0]) : sender instanceof EntityPlayerMP ? ((EntityPlayerMP) sender) : null;
		
		if(target != null){
			MiscOp.getPlayerTag(target).removeTag("path");
			StoreNBTToClient.syncNBTToClient(target, false);
			target.sendMessage(new TextComponentString("Your path has been reset."));
		}else{
			sender.sendMessage(new TextComponentString("Target player does not exist!"));
		}
	}

	@Override
	public int getRequiredPermissionLevel(){
		return 2;
	}
}
