package com.zentechnologies.sfdmod;

import com.zentechnologies.sfdmod.armory.ArmoryMultiBlock;
import com.zentechnologies.sfdmod.armory.ComputerBlock;
import com.zentechnologies.sfdmod.purifier.Purifier;
import com.zentechnologies.sfdmod.purifier.PurifierGuiHandler;
import com.zentechnologies.sfdmod.purifier.PurifierRender;
import com.zentechnologies.sfdmod.purifier.PurifierTileEntity;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = SFDMod.MODID, version = SFDMod.VERSION)
public class SFDMod
{
	//DECLARATIION OF ITEMS
    public static final String MODID = "sfdmod";
    public static final String VERSION = "0.0.1";
    
    @Instance("sfdmod")
	public static SFDMod instance;
    
	//Purifier
	private PurifierGuiHandler purifierGuiHandler = new PurifierGuiHandler();
	
	public static Block purifier;
	public static Block purifierActive;
	
	//Components
    public static final Item cybronic_eye = new Item().setMaxStackSize(1).setUnlocalizedName("cybroniceye").setTextureName(SFDMod.MODID+":"+"cybronicEye");
    public static final Item cleansed_spider_eye = new Item().setMaxStackSize(16).setUnlocalizedName("cleansedspidereye").setTextureName(SFDMod.MODID+":"+"cleansedSpiderEye");
    public static final Item cleansedsnakeeye = new Item().setMaxStackSize(16).setUnlocalizedName("cleansedsnakeeye").setTextureName(SFDMod.MODID+":"+"cleansedSnakeEye");
    public static final Item snakeeye = new Item().setMaxStackSize(16).setUnlocalizedName("snakeeye").setTextureName(SFDMod.MODID+":"+"SnakeEye");
    public static final Item compressedSteel = new Item().setMaxStackSize(64).setUnlocalizedName("compressedsteel").setTextureName(SFDMod.MODID+":"+"compressedSteel");
    public static final Item Copperwire = new Item().setMaxStackSize(64).setUnlocalizedName("copperwire").setTextureName(SFDMod.MODID+":"+"copperwire");
    public static final Item computerchip = new Item().setMaxStackSize(16).setUnlocalizedName("computerchip").setTextureName(SFDMod.MODID+":"+"computerChip");
    public static final Item Fabric = new Item().setMaxStackSize(64).setUnlocalizedName("fabric").setTextureName(SFDMod.MODID+":"+"fabric");
    public static final Item Hydraulic = new Item().setMaxStackSize(64).setUnlocalizedName("hydraulicpump").setTextureName(SFDMod.MODID+":"+"Hydraulicpump");
    public static final Item Metafabric = new Item().setMaxStackSize(64).setUnlocalizedName("metafabric").setTextureName(SFDMod.MODID+":"+"Metafabric");
    public static final Item Neuroplast = new Item().setMaxStackSize(64).setUnlocalizedName("neuroplast").setTextureName(SFDMod.MODID+":"+"Neuroplasts");
    public static final Item siliconBoule = new Item().setMaxStackSize(64).setUnlocalizedName("siliconboule").setTextureName(SFDMod.MODID+":"+"siliconBoule");
    public static final Item basicCore = new Item().setMaxStackSize(64).setUnlocalizedName("basiccore").setTextureName(SFDMod.MODID+":"+"basicCore");
    public static final Item MFcore = new Item().setMaxStackSize(64).setUnlocalizedName("mfcore").setTextureName(SFDMod.MODID+":"+"MFcore");
    public static final Item Invisacore = new Item().setMaxStackSize(64).setUnlocalizedName("invisacore").setTextureName(SFDMod.MODID+":"+"Invisacore");

    //DECLARATIION OF BLOCKS
    //public static final BlockPurifier purifier = (BlockPurifier) new BlockPurifier().setBlockName("purifier").setBlockTextureName("furnace");
    public static final Block manganeseOre = new Block(Material.iron){}.setBlockName("manganeseore").setBlockTextureName("sfdmod:manganeseOre");
    public static final Block copperOre = new Block(Material.iron){}.setBlockName("copperore").setBlockTextureName("sfdmod:copperOre");
    public static final Block computerBlock = new ComputerBlock(Material.iron).setBlockName("computerblock").setBlockTextureName("iron_block");
    
    public static CreativeTabs sfdTab = new CreativeTabs("sfdmod"){
    	@Override
    	@SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		return cybronic_eye;
    	}
    };

    public void addItem(Item... items){
    	for(Item i : items){
    		GameRegistry.registerItem(i, i.getUnlocalizedName());
    		i.setCreativeTab(this.sfdTab);
    	}
    }
    
    public void addBlock(Block... blocks){
    	for(Block b : blocks){
    		GameRegistry.registerBlock(b, b.getUnlocalizedName());
    		b.setCreativeTab(this.sfdTab);
    	}
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//SETTING CREATIVE TAB
    	addItem(cybronic_eye,cleansed_spider_eye, cleansedsnakeeye, compressedSteel,computerchip, snakeeye, Fabric, Copperwire, Hydraulic, Metafabric, Neuroplast, siliconBoule, basicCore, MFcore, Invisacore);
    	addBlock(manganeseOre, copperOre, computerBlock);
    	
    	//SETTING CRAFTING RECIPES
    	GameRegistry.addShapelessRecipe(new ItemStack(SFDMod.cybronic_eye, 1), SFDMod.cleansed_spider_eye, SFDMod.computerchip);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.basicCore,1), "gcg", "cdc","gcg",'c',SFDMod.computerchip,'g',Blocks.glass, 'd',Items.diamond);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.Hydraulic,1), "ccc", "cic","cic",'c',SFDMod.compressedSteel,'i',Items.iron_ingot);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.Invisacore,1), "fgf", "cbc","fgf",'c',SFDMod.computerchip,'g',Blocks.glass, 'b',SFDMod.basicCore,'f', SFDMod.Fabric);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.MFcore,1), "fnf", "cbc","fnf",'c',SFDMod.computerchip,'n',SFDMod.Neuroplast, 'b',SFDMod.basicCore, 'f',SFDMod.Metafabric);
    }
    
	@EventHandler
	public void load(FMLInitializationEvent event) {
    	//PURIFIER STUFF
    	purifier = new Purifier(3000, false).setHardness(4.0F).setResistance(15.0F).setBlockName("purifier").setCreativeTab(SFDMod.sfdTab);
		purifierActive = new Purifier(3001, true).setHardness(4.0F).setResistance(15.0F).setBlockName("purifierActive");
		
		GameRegistry.registerBlock(purifier, purifier.getUnlocalizedName());
		GameRegistry.registerBlock(purifierActive, purifierActive.getUnlocalizedName());
		
		GameRegistry.registerTileEntity(PurifierTileEntity.class, "PurifierTileEntity");
		GameRegistry.addRecipe(new ItemStack(purifier, 1), new Object[] {
			"AAA",
			"A A",
			"AAA",
			'A', Items.iron_ingot
		});
		
		RenderingRegistry.registerBlockHandler(2105, PurifierRender.INSTANCE);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new PurifierGuiHandler());
		
		GameRegistry.registerTileEntity(ArmoryMultiBlock.class, "sfdmod:armory_multi_block");
    }
    
}
