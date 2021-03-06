package com.Da_Technomancer.crossroads.client.bakedModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import com.Da_Technomancer.crossroads.Main;
import com.Da_Technomancer.crossroads.API.enums.PrototypePortTypes;
import com.google.common.collect.ImmutableSet;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class PrototypeModel implements IModel{

	@Override
	public Collection<ResourceLocation> getDependencies(){
		return Collections.emptySet();
	}

	@Override
	public Collection<ResourceLocation> getTextures(){
		ArrayList<ResourceLocation> textures = new ArrayList<>();
		for(PrototypePortTypes type : PrototypePortTypes.values()){
			textures.add(type.getTexture());
		}
		textures.add(new ResourceLocation(Main.MODID, "blocks/prototype/blank"));
		return ImmutableSet.copyOf(textures);
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter){
		return new PrototypeBakedModel(format, bakedTextureGetter);
	}

	@Override
	public IModelState getDefaultState(){
		return TRSRTransformation.identity();
	}

}
