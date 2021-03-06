package com.Da_Technomancer.crossroads.API;

import java.util.UUID;

import javax.annotation.Nullable;

import com.Da_Technomancer.crossroads.Main;
import com.mojang.authlib.GameProfile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerProfileCache;

/**
 * 
 * A version of GameProfile with a looser {@link #equals(Object)} implementation.
 * This version will not immediately return false if only 1 of the two GameProfiles has a null ID or Name.
 * However, in order that equals be symmetric, this will return false if the other object is a normal GameProfile instance.
 */
public class GameProfileNonPicky extends GameProfile{

	private boolean newlyCompleted = false;
	
	public GameProfileNonPicky(UUID id, String name){
		super(id, name);
	}

	public GameProfileNonPicky(GameProfile prof){
		super(prof.getId(), prof.getName());
	}
	
	public boolean isNewlyCompleted(){
		return newlyCompleted;
	}
	
	@Override
	public boolean equals(Object other){
		if (this == other) {
			return true;
		}
		if (!(other instanceof GameProfileNonPicky)) {
			return false;
		}

		final GameProfileNonPicky that = (GameProfileNonPicky) other;

		if (getId() != null && that.getId() != null) {
			return getId().equals(that.getId());
		}
		if (getName() != null && that.getName() != null && !getName().equals(that.getName())) {
			return false;
		}

		return true;
	}
	
	public void writeToNBT(NBTTagCompound nbt, String name){
		String profName = getName();
		UUID id = getId();
		if(profName != null){
			nbt.setString(name + "_name", profName);
		}
		if(id != null){
			nbt.setUniqueId(name + "_id", id);
		}
	}
	
	/**
	 * If cache is not null and the loaded profile is missing a UUID, the cache will be used to attempt to complete the profile.
	 * 
	 * Returns null if no profile was stored to nbt. 
	 */
	@Nullable
	public static GameProfileNonPicky readFromNBT(NBTTagCompound nbt, String name, @Nullable PlayerProfileCache cache){
		String profName = nbt.getString(name + "_name");
		if(profName.isEmpty()){
			return null;
		}
		UUID id = nbt.hasKey(name + "_idMost") ? nbt.getUniqueId(name + "_id") : null;
		boolean loadedID = false;
		
		if(id == null && cache != null){
			GameProfile search = cache.getGameProfileForUsername(profName);
			if(search != null){
				id = search.getId();
				loadedID = true;
			}
			Main.logger.info("Attempting to complete player profile for " + profName + (loadedID ? ". Failed (not severe). " : ". Succeeded. UUID is " + id.toString()));
		}
		
		GameProfileNonPicky out = new GameProfileNonPicky(id, profName);
		out.newlyCompleted = loadedID;
		return out;
	}
}
