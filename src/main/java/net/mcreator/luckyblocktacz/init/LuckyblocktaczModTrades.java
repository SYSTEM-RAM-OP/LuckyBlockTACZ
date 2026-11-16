/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.luckyblocktacz.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.common.BasicItemListing;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LuckyblocktaczModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.TOOLSMITH) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.GRASS_BLOCK, 64), new ItemStack(LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ.get(), 64), 1000, 5, 0.05f));
		}
	}
}