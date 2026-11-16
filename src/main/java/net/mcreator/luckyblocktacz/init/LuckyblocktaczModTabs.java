/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.luckyblocktacz.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

public class LuckyblocktaczModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LuckyblocktaczMod.MODID);
	public static final RegistryObject<CreativeModeTab> LUCKY_BLOCK_TACZ_TAB = REGISTRY.register("lucky_block_tacz_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.luckyblocktacz.lucky_block_tacz_tab")).icon(() -> new ItemStack(LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ.get())).displayItems((parameters, tabData) -> {
				tabData.accept(LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ.get().asItem());
			}).build());
}