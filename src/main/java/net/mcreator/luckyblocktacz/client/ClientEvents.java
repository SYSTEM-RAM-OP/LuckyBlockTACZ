package net.mcreator.luckyblocktacz.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.mcreator.luckyblocktacz.init.LuckyBlockEntityTypes;
import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

@Mod.EventBusSubscriber(modid = LuckyblocktaczMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(LuckyBlockEntityTypes.LUCKY_BLOCK.get(), ctx -> new LuckyBlockRenderer(ctx.getItemRenderer()));
	}

	private ClientEvents() {
	}
}
