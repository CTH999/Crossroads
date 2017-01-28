package com.Da_Technomancer.crossroads.blocks;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;

import com.Da_Technomancer.crossroads.API.enums.HeatInsulators;
import com.Da_Technomancer.crossroads.blocks.fluid.FatCollector;
import com.Da_Technomancer.crossroads.blocks.fluid.FatCongealer;
import com.Da_Technomancer.crossroads.blocks.fluid.FatFeeder;
import com.Da_Technomancer.crossroads.blocks.fluid.FluidTank;
import com.Da_Technomancer.crossroads.blocks.fluid.FluidTube;
import com.Da_Technomancer.crossroads.blocks.fluid.FluidVoid;
import com.Da_Technomancer.crossroads.blocks.fluid.Radiator;
import com.Da_Technomancer.crossroads.blocks.fluid.RedstoneFluidTube;
import com.Da_Technomancer.crossroads.blocks.fluid.RotaryPump;
import com.Da_Technomancer.crossroads.blocks.fluid.SteamBoiler;
import com.Da_Technomancer.crossroads.blocks.fluid.SteamTurbine;
import com.Da_Technomancer.crossroads.blocks.fluid.WaterCentrifuge;
import com.Da_Technomancer.crossroads.blocks.heat.CoalHeater;
import com.Da_Technomancer.crossroads.blocks.heat.FluidCoolingChamber;
import com.Da_Technomancer.crossroads.blocks.heat.HeatCable;
import com.Da_Technomancer.crossroads.blocks.heat.HeatExchanger;
import com.Da_Technomancer.crossroads.blocks.heat.HeatingChamber;
import com.Da_Technomancer.crossroads.blocks.heat.HeatingCrucible;
import com.Da_Technomancer.crossroads.blocks.heat.RedstoneHeatCable;
import com.Da_Technomancer.crossroads.blocks.heat.SaltReactor;
import com.Da_Technomancer.crossroads.blocks.magic.ArcaneExtractor;
import com.Da_Technomancer.crossroads.blocks.magic.ArcaneReflector;
import com.Da_Technomancer.crossroads.blocks.magic.BeaconHarness;
import com.Da_Technomancer.crossroads.blocks.magic.BeamSplitter;
import com.Da_Technomancer.crossroads.blocks.magic.BeamSplitterBasic;
import com.Da_Technomancer.crossroads.blocks.magic.ColorChart;
import com.Da_Technomancer.crossroads.blocks.magic.CrystalMasterAxis;
import com.Da_Technomancer.crossroads.blocks.magic.CrystallinePrism;
import com.Da_Technomancer.crossroads.blocks.magic.LensHolder;
import com.Da_Technomancer.crossroads.blocks.magic.QuartzStabilizer;
import com.Da_Technomancer.crossroads.blocks.rotary.Axle;
import com.Da_Technomancer.crossroads.blocks.rotary.Grindstone;
import com.Da_Technomancer.crossroads.blocks.rotary.ItemChutePort;
import com.Da_Technomancer.crossroads.blocks.rotary.LargeGearMaster;
import com.Da_Technomancer.crossroads.blocks.rotary.LargeGearSlave;
import com.Da_Technomancer.crossroads.blocks.rotary.MasterAxis;
import com.Da_Technomancer.crossroads.blocks.rotary.RotaryDrill;
import com.Da_Technomancer.crossroads.blocks.rotary.SidedGearHolder;
import com.Da_Technomancer.crossroads.blocks.technomancy.ChunkUnlocker;
import com.Da_Technomancer.crossroads.blocks.technomancy.FluxManipulator;
import com.Da_Technomancer.crossroads.blocks.technomancy.FluxReaderAxis;
import com.Da_Technomancer.crossroads.blocks.technomancy.MultiplicationAxis;
import com.Da_Technomancer.crossroads.blocks.technomancy.RateManipulator;
import com.Da_Technomancer.crossroads.items.itemSets.HeatCableFactory;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ModBlocks{

	public static SidedGearHolder sidedGearHolder;
	public static MasterAxis masterAxis;
	public static FluidTube fluidTube;
	public static HeatingCrucible heatingCrucible;
	public static Grindstone grindstone;
	public static SteamBoiler steamBoiler;
	public static BlockSalt blockSalt;
	public static BlockBrazier brazier;
	public static RotaryPump rotaryPump;
	public static SteamTurbine steamTurbine;
	public static HeatExchanger heatExchanger;
	public static HeatExchanger insulHeatExchanger;
	public static FluidTank fluidTank;
	public static CoalHeater coalHeater;
	public static HeatingChamber heatingChamber;
	public static SaltReactor saltReactor;
	public static FluidCoolingChamber fluidCoolingChamber;
	public static SlottedChest slottedChest;
	public static SortingHopper sortingHopper;
	public static LargeGearMaster largeGearMaster;
	public static LargeGearSlave largeGearSlave;
	public static CandleLilyPad candleLilyPad;
	public static ItemChute itemChute;
	public static ItemChutePort itemChutePort;
	public static Radiator radiator;
	public static RotaryDrill rotaryDrill;
	public static FatCollector fatCollector;
	public static FatCongealer fatCongealer;
	public static RedstoneFluidTube redstoneFluidTube;
	public static WaterCentrifuge waterCentrifuge;
	public static ArcaneExtractor arcaneExtractor;
	public static QuartzStabilizer smallQuartzStabilizer;
	public static QuartzStabilizer largeQuartzStabilizer;
	public static CrystallinePrism crystallinePrism;
	public static ArcaneReflector arcaneReflector;
	public static LensHolder lensHolder;
	public static BasicBlock blockPureQuartz;
	public static BeamSplitter beamSplitter;
	public static ColorChart colorChart;
	public static GlowGlass glowGlass;
	public static FertileSoil fertileSoil;
	public static MultiPistonExtend multiPistonExtend;
	public static MultiPistonExtend multiPistonExtendSticky;
	public static MultiPistonBase multiPiston;
	public static MultiPistonBase multiPistonSticky;
	public static BeamSplitterBasic beamSplitterBasic;
	public static CrystalMasterAxis crystalMasterAxis;
	public static Axle axle;
	public static Ratiator ratiator;
	public static BasicBlock blockSaltTile;
	public static BeaconHarness beaconHarness;
	public static FatFeeder fatFeeder;
	public static ChunkUnlocker chunkUnlocker; 
	public static RateManipulator rateManipulator;
	public static FluxManipulator fluxManipulator;
	public static FluxReaderAxis fluxReaderAxis;
	public static MultiplicationAxis multiplicationAxis;

	private static final ArrayList<Block> modelQue = new ArrayList<Block>();
	private static final ArrayList<Pair<Block, Integer>> modelQuePair = new ArrayList<Pair<Block, Integer>>();

	public static void blockAddQue(Block block){
		modelQue.add(block);
	}
	
	/** The integer is the end metadata value*/
	public static void blockAddQueRange(Pair<Block, Integer> block){
		modelQuePair.add(block);
	}

	public static final void init(){
		blockAddQue(masterAxis = new MasterAxis());
		blockAddQue(grindstone = new Grindstone());
		sidedGearHolder = new SidedGearHolder();
		largeGearMaster = new LargeGearMaster();
		largeGearSlave = new LargeGearSlave();
		blockAddQue(heatingCrucible = new HeatingCrucible());
		blockAddQue(fluidTube = new FluidTube());
		blockAddQue(steamBoiler = new SteamBoiler());
		rotaryPump = new RotaryPump();
		steamTurbine = new SteamTurbine();
		blockAddQue(blockSalt = new BlockSalt());
		blockAddQue(brazier = new BlockBrazier());
		blockAddQue(new FluidVoid());
		blockAddQue(heatExchanger = new HeatExchanger(false));
		blockAddQue(insulHeatExchanger = new HeatExchanger(true));
		blockAddQue(fluidTank = new FluidTank());
		blockAddQue(coalHeater = new CoalHeater());
		blockAddQue(heatingChamber = new HeatingChamber());
		blockAddQue(saltReactor = new SaltReactor());
		blockAddQue(fluidCoolingChamber = new FluidCoolingChamber());
		blockAddQue(slottedChest = new SlottedChest());
		blockAddQue(sortingHopper = new SortingHopper());
		candleLilyPad = new CandleLilyPad();
		blockAddQue(itemChute = new ItemChute());
		blockAddQue(itemChutePort = new ItemChutePort());
		blockAddQue(radiator = new Radiator());
		blockAddQue(rotaryDrill = new RotaryDrill());
		blockAddQue(fatCollector = new FatCollector());
		blockAddQue(fatCongealer = new FatCongealer());
		blockAddQue(redstoneFluidTube = new RedstoneFluidTube());
		blockAddQue(waterCentrifuge = new WaterCentrifuge());
		blockAddQue(arcaneExtractor = new ArcaneExtractor());
		blockAddQue(smallQuartzStabilizer = new QuartzStabilizer(false));
		blockAddQue(largeQuartzStabilizer = new QuartzStabilizer(true));
		blockAddQue(crystallinePrism = new CrystallinePrism());
		blockAddQue(arcaneReflector = new ArcaneReflector());
		blockAddQue(lensHolder = new LensHolder());
		blockPureQuartz = new BasicBlock("blockPureQuartz", Material.ROCK, 1, "pickaxe", 4, null, "blockQuartz");
		blockAddQue(beamSplitter = new BeamSplitter());
		blockAddQue(colorChart = new ColorChart());
		blockAddQue(glowGlass = new GlowGlass());
		blockAddQueRange(Pair.of(fertileSoil = new FertileSoil(), 9));
		multiPistonExtend = new MultiPistonExtend(false);
		multiPistonExtendSticky = new MultiPistonExtend(true);
		blockAddQue(multiPiston = new MultiPistonBase(false));
		blockAddQue(multiPistonSticky = new MultiPistonBase(true));
		blockAddQue(beamSplitterBasic = new BeamSplitterBasic());
		blockAddQue(crystalMasterAxis = new CrystalMasterAxis());
		blockAddQue(axle = new Axle());
		blockAddQue(ratiator = new Ratiator());
		blockSaltTile = new BasicBlock("blockSaltTile", Material.SAND, 0, "shovel", .5F, SoundType.SAND, "blockSalt");
		blockAddQue(beaconHarness = new BeaconHarness());
		blockAddQue(fatFeeder = new FatFeeder());
		blockAddQue(chunkUnlocker = new ChunkUnlocker());
		blockAddQue(rateManipulator = new RateManipulator());
		blockAddQue(fluxManipulator = new FluxManipulator());
		blockAddQue(fluxReaderAxis = new FluxReaderAxis());
		blockAddQue(multiplicationAxis = new MultiplicationAxis());
	}

	@SideOnly(Side.CLIENT)
	public static void preInitModels(){

		for(Block modeling : modelQue){
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(modeling), 0, new ModelResourceLocation(modeling.getRegistryName(), "inventory"));

		}
		
		for(Pair<Block, Integer> modeling : modelQuePair){
			for(int i = 0; i <= modeling.getRight(); i++){
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(modeling.getLeft()), i, new ModelResourceLocation(modeling.getLeft().getRegistryName(), "inventory"));
			}
		}

		for(HashMap<HeatInsulators, HeatCable> map : HeatCableFactory.cableMap.values()){
			for(HeatCable cable : map.values()){
				cable.initModel();
			}
		}
		
		for(HashMap<HeatInsulators, RedstoneHeatCable> map : HeatCableFactory.rCableMap.values()){
			for(RedstoneHeatCable cable : map.values()){
				cable.initModel();
			}
		}

		fluidTube.initModel();
		redstoneFluidTube.initModel();
	}
}
