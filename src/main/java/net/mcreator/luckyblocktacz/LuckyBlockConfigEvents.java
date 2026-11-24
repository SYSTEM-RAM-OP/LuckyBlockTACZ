package net.mcreator.luckyblocktacz;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import net.mcreator.luckyblocktacz.config.LuckyBlockConfig;

@Mod.EventBusSubscriber(modid = LuckyblocktaczMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LuckyBlockConfigEvents {
	@SubscribeEvent
	public static void onLoading(ModConfigEvent.Loading event) {
		handle(event);
	}

	@SubscribeEvent
	public static void onReloading(ModConfigEvent.Reloading event) {
		handle(event);
	}

	private static void handle(ModConfigEvent event) {
		if (event.getConfig().getSpec() != LuckyBlockConfig.SPEC)
			return;
		LuckyBlockLoot.rebuildCache();
	}

	private LuckyBlockConfigEvents() {
	}
}
