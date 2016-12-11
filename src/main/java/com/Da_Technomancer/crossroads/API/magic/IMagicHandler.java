package com.Da_Technomancer.crossroads.API.magic;

import javax.annotation.Nullable;

/** This capability is to be placed on things that RECIEVE magic, not for things that send magic.*/
public interface IMagicHandler{
	
	public final int MAX_DISTANCE = 16;
	public final int BEAM_TIME = 5;
	
	/**
	 * Handlers should assume that they receive the last magic unit sent with this method continuously for the next BEAM_TIME ticks.
	 */
	public void setMagic(@Nullable MagicUnit mag);
}
