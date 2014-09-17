package com.zentechnologies.sfdmod.armory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
 
public class ComputerBlock extends BlockContainer {
    public ComputerBlock(Material material) {
        super(material);
    }
 
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof ArmoryMultiBlock) {
        	ArmoryMultiBlock multiBlock = (ArmoryMultiBlock) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        multiBlock.reset();
                        world.markBlockForUpdate(x, y, z);
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }
 
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ArmoryMultiBlock();
    }
}