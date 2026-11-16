/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.luckyblocktacz.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

public class LuckyblocktaczModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, LuckyblocktaczMod.MODID);
	public static final RegistryObject<Item> LUCKY_BLOCK_TACZ;
	static {
		LUCKY_BLOCK_TACZ = block(LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ, new Item.Properties().fireResistant());
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}