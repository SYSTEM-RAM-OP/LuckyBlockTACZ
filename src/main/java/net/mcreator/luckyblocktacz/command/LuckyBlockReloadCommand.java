package net.mcreator.luckyblocktacz.command;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.mcreator.luckyblocktacz.LuckyBlockLoot;
import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

@Mod.EventBusSubscriber(modid = LuckyblocktaczMod.MODID)
public final class LuckyBlockReloadCommand {
	@SubscribeEvent
	public static void register(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("lbtacz").then(Commands.literal("reload").requires(s -> s.hasPermission(2)).executes(ctx -> {
			LuckyBlockLoot.rebuildCache();
			int sets = LuckyBlockLoot.cachedSetCount();
			ctx.getSource().sendSuccess(() -> Component.literal("LuckyBlockTACZ loot cache rebuilt (" + sets + " sets)"), true);
			return 1;
		})));
	}

	private LuckyBlockReloadCommand() {
	}
}
