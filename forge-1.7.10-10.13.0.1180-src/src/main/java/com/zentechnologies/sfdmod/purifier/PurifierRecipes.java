package com.zentechnologies.sfdmod.purifier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.zentechnologies.sfdmod.SFDMod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class PurifierRecipes
{
    private static final PurifierRecipes smeltingBase = new PurifierRecipes();
    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static PurifierRecipes smelting()
    {
        return smeltingBase;
    }

    private PurifierRecipes()
    {
        this.addItemRecipe(Items.spider_eye, new ItemStack(SFDMod.cleansedspidereye), 0.35F);
        this.addItemRecipe(SFDMod.snakeeye, new ItemStack(SFDMod.cleansedsnakeeye), 0.35F);

    }

    public void addBlockRecipe(Block input, ItemStack output, float time)
    {
        this.addItemRecipe(Item.getItemFromBlock(input), output, time);
    }

    public void addItemRecipe(Item input, ItemStack output, float time)
    {
        this.addStackRecipe(new ItemStack(input, 1, 32767), output, time);
    }

    public void addStackRecipe(ItemStack input, ItemStack output, float time)
    {
        this.smeltingList.put(input, output);
        this.experienceList.put(output, Float.valueOf(time));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack p_151395_1_)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151395_1_, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}