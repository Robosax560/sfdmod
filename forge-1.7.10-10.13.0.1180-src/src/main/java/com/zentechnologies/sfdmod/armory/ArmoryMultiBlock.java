package com.zentechnologies.sfdmod.armory;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class ArmoryMultiBlock extends TileEntity {
	private boolean hasMaster, isMaster;
	private int masterX, masterY, masterZ;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (hasMaster()) { 
				if (isMaster()) {
					buildEntity();
				}
			} else {
				// Constantly check if structure is formed until it is.
				if (checkMultiBlockForm())
					setupStructure();
			}
		}
	}
	
	public void buildEntity(){
		
	}

	/** Check that structure is properly formed */
	public boolean checkMultiBlockForm() {
		int i = 0;
		// Scan a 2x2x3 area, starting with the bottom left corner
		for (int x = xCoord - 1; x < xCoord + 1; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 1; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					// Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock (if ours is already part of one)
					if (tile != null && (tile instanceof ArmoryMultiBlock)) {
						if (this.isMaster()) {
							if (((ArmoryMultiBlock)tile).hasMaster())
								i++;
						} else if (!((ArmoryMultiBlock)tile).hasMaster())
							i++;
					}
				}
		return i > 11;
	}

	/** Setup all the blocks in the structure*/
	public void setupStructure() {
		for (int x = xCoord - 1; x < xCoord + 2; x++){
			for (int y = yCoord; y < yCoord + 3; y++){
				for (int z = zCoord - 1; z < zCoord + 2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					// Check if block is bottom center block
					boolean master = (x == xCoord && y == yCoord && z == zCoord);
					if (tile != null && (tile instanceof ArmoryMultiBlock)) {
						((ArmoryMultiBlock) tile).setMasterCoords(xCoord, yCoord, zCoord);
						((ArmoryMultiBlock) tile).setHasMaster(true);
						((ArmoryMultiBlock) tile).setIsMaster(master);
						worldObj.setBlockToAir(x, y, z);
						if(master){
							EntityPig pig = new EntityPig(worldObj);
							pig.setLocationAndAngles(x, y, z, 0, 0);
							worldObj.spawnEntityInWorld(pig);
						}
					}
				}
			}
		}
	}

	/** Reset method to be run when the master is gone or tells them to */
	public void reset() {
		masterX = 0;
		masterY = 0;
		masterZ = 0;
		hasMaster = false;
		isMaster = false;
	}

	/** Check that the master exists */
	public boolean checkForMaster() {
		TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
		return (tile != null && (tile instanceof ArmoryMultiBlock));
	}

	/** Reset all the parts of the structure */
	public void resetStructure() {
		for (int x = xCoord - 1; x < xCoord + 2; x++)
			for (int y = yCoord; y < yCoord + 3; y++)
				for (int z = zCoord - 1; z < zCoord + 2; z++) {
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if (tile != null && (tile instanceof ArmoryMultiBlock))
						((ArmoryMultiBlock) tile).reset();
				}
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("masterX", masterX);
		data.setInteger("masterY", masterY);
		data.setInteger("masterZ", masterZ);
		data.setBoolean("hasMaster", hasMaster);
		data.setBoolean("isMaster", isMaster);
		if (hasMaster() && isMaster()) {
			// Any other values should ONLY BE SAVED TO THE MASTER
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		masterX = data.getInteger("masterX");
		masterY = data.getInteger("masterY");
		masterZ = data.getInteger("masterZ");
		hasMaster = data.getBoolean("hasMaster");
		isMaster = data.getBoolean("isMaster");
		if (hasMaster() && isMaster()) {
			// Any other values should ONLY BE READ BY THE MASTER
		}
	}

	public boolean hasMaster() {
		return hasMaster;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public int getMasterX() {
		return masterX;
	}

	public int getMasterY() {
		return masterY;
	}

	public int getMasterZ() {
		return masterZ;
	}

	public void setHasMaster(boolean bool) {
		hasMaster = bool;
	}

	public void setIsMaster(boolean bool) {
		isMaster = bool;
	}

	public void setMasterCoords(int x, int y, int z) {
		masterX = x;
		masterY = y;
		masterZ = z;
	}
}