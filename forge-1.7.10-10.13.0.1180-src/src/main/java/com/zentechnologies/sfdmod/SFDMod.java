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
	
	//Items
    public static final Item basic_core = new Item().setMaxStackSize(64).setUnlocalizedName("basic_core").setTextureName(SFDMod.MODID+":"+"basic_core");
    public static final Item cleansed_snake_eye = new Item().setMaxStackSize(16).setUnlocalizedName("cleansed_snake_eye").setTextureName(SFDMod.MODID+":"+"cleansed_snake_eye");
    public static final Item cleansed_spider_eye = new Item().setMaxStackSize(16).setUnlocalizedName("cleansed_spider_eye").setTextureName(SFDMod.MODID+":"+"cleansed_spider_eye");
    public static final Item compressed_steel = new Item().setMaxStackSize(64).setUnlocalizedName("compressed_steel").setTextureName(SFDMod.MODID+":"+"compressed_steel");
    public static final Item computer_chip = new Item().setMaxStackSize(16).setUnlocalizedName("computer_chip").setTextureName(SFDMod.MODID+":"+"computer_chip");
    public static final Item copper_wire = new Item().setMaxStackSize(64).setUnlocalizedName("copper_wire").setTextureName(SFDMod.MODID+":"+"copper_wire");
    public static final Item cybronic_eye = new Item().setMaxStackSize(1).setUnlocalizedName("cybronic_eye").setTextureName(SFDMod.MODID+":"+"cybronic_eye");
    public static final Item fabric = new Item().setMaxStackSize(64).setUnlocalizedName("fabric").setTextureName(SFDMod.MODID+":"+"fabric");
    public static final Item hydraulic_pump = new Item().setMaxStackSize(64).setUnlocalizedName("hydraulic_pump").setTextureName(SFDMod.MODID+":"+"hydraulic_pump");
    public static final Item invisacore = new Item().setMaxStackSize(64).setUnlocalizedName("invisacore").setTextureName(SFDMod.MODID+":"+"invisacore");
    public static final Item metafabric = new Item().setMaxStackSize(64).setUnlocalizedName("metafabric").setTextureName(SFDMod.MODID+":"+"metafabric");
    public static final Item mfcore = new Item().setMaxStackSize(64).setUnlocalizedName("mfcore").setTextureName(SFDMod.MODID+":"+"mfcore");
    public static final Item neuroplast = new Item().setMaxStackSize(64).setUnlocalizedName("neuroplast").setTextureName(SFDMod.MODID+":"+"neuroplast");
    public static final Item silicon_boule = new Item().setMaxStackSize(64).setUnlocalizedName("silicon_boule").setTextureName(SFDMod.MODID+":"+"silicon_boule");
    public static final Item snake_eye = new Item().setMaxStackSize(16).setUnlocalizedName("snake_eye").setTextureName(SFDMod.MODID+":"+"snake_eye");

    //Blocks
    public static final Block manganese_ore = new Block(Material.iron){}.setBlockName("manganese_ore").setBlockTextureName("sfdmod:manganese_ore");
    public static final Block copper_ore = new Block(Material.iron){}.setBlockName("copper_ore").setBlockTextureName("sfdmod:copper_ore");
    public static final Block computer_block = new ComputerBlock(Material.iron).setBlockName("computer_block").setBlockTextureName("iron_block");
    
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
    	addItem(cybronic_eye,cleansed_spider_eye, cleansed_snake_eye, compressed_steel,computer_chip, snake_eye, fabric, 
    			copper_wire, hydraulic_pump, metafabric, neuroplast, silicon_boule, basic_core, mfcore, invisacore);
    	addBlock(manganese_ore, copper_ore, computer_block);
    	
    	//SETTING CRAFTING RECIPES
    	GameRegistry.addShapelessRecipe(new ItemStack(SFDMod.cybronic_eye, 1), SFDMod.cleansed_spider_eye, SFDMod.computer_chip);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.basic_core,1), "gcg", "cdc","gcg",'c',SFDMod.computer_chip,'g',Blocks.glass, 'd',Items.diamond);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.hydraulic_pump,1), "ccc", "cic","cic",'c',SFDMod.compressed_steel,'i',Items.iron_ingot);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.invisacore,1), "fgf", "cbc","fgf",'c',SFDMod.computer_chip,'g',Blocks.glass, 'b',SFDMod.basic_core,'f', SFDMod.fabric);
    	GameRegistry.addShapedRecipe(new ItemStack(SFDMod.mfcore,1), "fnf", "cbc","fnf",'c',SFDMod.computer_chip,'n',SFDMod.neuroplast, 'b',SFDMod.basic_core, 'f',SFDMod.metafabric);
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
