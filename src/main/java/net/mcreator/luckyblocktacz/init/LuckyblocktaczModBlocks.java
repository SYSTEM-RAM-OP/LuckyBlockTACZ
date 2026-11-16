/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.luckyblocktacz.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.luckyblocktacz.block.LuckyBlockTACZBlock;
import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

public class LuckyblocktaczModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, LuckyblocktaczMod.MODID);
	public static final RegistryObject<Block> LUCKY_BLOCK_TACZ;
	static {
		LUCKY_BLOCK_TACZ = REGISTRY.register("lucky_block_tacz", LuckyBlockTACZBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}