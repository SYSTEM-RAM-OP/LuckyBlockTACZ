package net.mcreator.luckyblocktacz.jackpot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.mcreator.luckyblocktacz.LuckyBlockLoot;
import net.mcreator.luckyblocktacz.LuckyblocktaczMod;
import net.mcreator.luckyblocktacz.advancement.LuckyAdvancements;
import net.mcreator.luckyblocktacz.config.LuckyBlockConfig;

public final class JackpotTracker {
	private static volatile long tick;
	private static final Map<ResourceKey<Level>, List<Break>> RECENT = new HashMap<>();

	public static final class Break {
		final BlockPos pos;
		final long tick;

		Break(BlockPos pos, long tick) {
			this.pos = pos;
			this.tick = tick;
		}
	}

	public static synchronized void onLuckyBlockBroken(ServerLevel level, BlockPos pos, @Nullable ServerPlayer player) {
		if (level == null || pos == null)
			return;
		if (!LuckyBlockConfig.boolOf(LuckyBlockConfig.JACKPOT_ENABLED))
			return;
		ResourceKey<Level> dimension = level.dimension();
		List<Break> recent = RECENT.computeIfAbsent(dimension, key -> new ArrayList<>());
		long now = tick;
		long windowTicks = Math.max(1, LuckyBlockConfig.intOf(LuckyBlockConfig.JACKPOT_WINDOW_SECONDS)) * 20L;
		recent.removeIf(entry -> now - entry.tick > windowTicks);
		recent.add(new Break(pos.immutable(), now));
		int radius = Math.max(1, LuckyBlockConfig.intOf(LuckyBlockConfig.JACKPOT_RADIUS));
		int minBlocks = Math.max(2, LuckyBlockConfig.intOf(LuckyBlockConfig.JACKPOT_MIN_BLOCKS));
		long radiusSquared = (long) radius * (long) radius;
		int count = 0;
		for (Break entry : recent) {
			long dx = (long) entry.pos.getX() - (long) pos.getX();
			long dy = (long) entry.pos.getY() - (long) pos.getY();
			long dz = (long) entry.pos.getZ() - (long) pos.getZ();
			if (dx * dx + dy * dy + dz * dz <= radiusSquared)
				count++;
		}
		if (count < minBlocks)
			return;
		recent.clear();
		if (LuckyBlockLoot.cachedSetCount() <= 0)
			return;
		LuckyBlockLoot.dropJackpot(level, pos);
		if (player != null)
			LuckyAdvancements.award(player, "jackpot");
		if (LuckyBlockConfig.boolOf(LuckyBlockConfig.JACKPOT_ANNOUNCE)) {
			Component message = Component.translatable("luckyblocktacz.jackpot.announce", Integer.toString(pos.getX()), Integer.toString(pos.getY()), Integer.toString(pos.getZ()));
			for (ServerPlayer target : level.getServer().getPlayerList().getPlayers()) {
				target.sendSystemMessage(message);
			}
		}
		if (LuckyBlockConfig.boolOf(LuckyBlockConfig.JACKPOT_SOUND)) {
			level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
		}
	}

	public static synchronized void reset() {
		RECENT.clear();
		tick = 0L;
	}

	private JackpotTracker() {
	}

	@Mod.EventBusSubscriber(modid = LuckyblocktaczMod.MODID)
	public static final class Events {
		@SubscribeEvent
		public static void onServerTick(TickEvent.ServerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				tick++;
			}
		}

		@SubscribeEvent
		public static void onServerStopping(ServerStoppingEvent event) {
			reset();
			LuckyAdvancements.reset();
		}

		private Events() {
		}
	}
}
