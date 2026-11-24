package net.mcreator.luckyblocktacz.advancement;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.luckyblocktacz.LuckyblocktaczMod;
import net.mcreator.luckyblocktacz.config.LuckyBlockConfig;

public final class LuckyAdvancements {
	public static final int COLLECTOR_TARGET = 100;
	private static final Map<UUID, Integer> BROKEN = new ConcurrentHashMap<>();

	public static void award(ServerPlayer player, String path) {
		if (player == null || path == null)
			return;
		if (!LuckyBlockConfig.boolOf(LuckyBlockConfig.ADVANCEMENTS_ENABLED))
			return;
		MinecraftServer server = player.getServer();
		if (server == null)
			return;
		Advancement adv = server.getAdvancements().getAdvancement(new ResourceLocation(LuckyblocktaczMod.MODID, path));
		if (adv != null)
			player.getAdvancements().award(adv, "code_trigger");
	}

	public static void onLuckyBlockBroken(ServerPlayer player) {
		if (player == null)
			return;
		if (!LuckyBlockConfig.boolOf(LuckyBlockConfig.ADVANCEMENTS_ENABLED))
			return;
		award(player, "root");
		int broken = BROKEN.merge(player.getUUID(), 1, Integer::sum);
		if (broken >= COLLECTOR_TARGET)
			award(player, "collector");
	}

	public static void reset() {
		BROKEN.clear();
	}

	private LuckyAdvancements() {
	}
}
