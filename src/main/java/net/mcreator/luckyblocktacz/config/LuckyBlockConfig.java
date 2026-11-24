package net.mcreator.luckyblocktacz.config;

import net.minecraftforge.common.ForgeConfigSpec;

import net.mcreator.luckyblocktacz.loot.Rarity;

import java.util.List;

public final class LuckyBlockConfig {
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.BooleanValue DROP_ENABLED;
	public static final ForgeConfigSpec.IntValue DROP_SETS_PER_BREAK;
	public static final ForgeConfigSpec.DoubleValue SCATTER_RADIUS;
	public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> TIER_CHANCES;
	public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> TIER1_WEIGHTS;
	public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> TIER2_WEIGHTS;
	public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> TIER3_WEIGHTS;
	public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> TIER4_WEIGHTS;
	public static final ForgeConfigSpec.EnumValue<Rarity> GUN_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> GUN_WITH_ATTACHMENT_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> ATTACHMENT_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> CONSUMABLE_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> MELEE_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> THROWABLE_RARITY;
	public static final ForgeConfigSpec.EnumValue<Rarity> SHIELD_RARITY;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ID_OVERRIDES;
	public static final ForgeConfigSpec.BooleanValue JACKPOT_ENABLED;
	public static final ForgeConfigSpec.IntValue JACKPOT_MIN_BLOCKS;
	public static final ForgeConfigSpec.IntValue JACKPOT_RADIUS;
	public static final ForgeConfigSpec.IntValue JACKPOT_WINDOW_SECONDS;
	public static final ForgeConfigSpec.IntValue JACKPOT_REWARD_SETS;
	public static final ForgeConfigSpec.IntValue JACKPOT_REWARD_TIER;
	public static final ForgeConfigSpec.BooleanValue JACKPOT_ANNOUNCE;
	public static final ForgeConfigSpec.BooleanValue JACKPOT_SOUND;
	public static final ForgeConfigSpec.BooleanValue ADVANCEMENTS_ENABLED;
	public static final ForgeConfigSpec.BooleanValue RENDER_ENABLED;
	public static final ForgeConfigSpec.DoubleValue RENDER_SCALE;
	public static final ForgeConfigSpec.DoubleValue RENDER_SPIN;
	public static final ForgeConfigSpec.DoubleValue RENDER_BOB;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("general");
		DROP_ENABLED = builder.define("drop_enabled", true);
		DROP_SETS_PER_BREAK = builder.defineInRange("drop_sets_per_break", 1, 1, 4);
		SCATTER_RADIUS = builder.defineInRange("scatter_radius", 0.5D, 0.0D, 3.0D);
		builder.pop();

		builder.push("tiers");
		TIER_CHANCES = builder.defineList("tier_chances", List.of(45, 30, 18, 7), o -> o instanceof Integer);
		TIER1_WEIGHTS = builder.defineList("tier1_weights", List.of(100, 40, 8, 0, 0), o -> o instanceof Integer);
		TIER2_WEIGHTS = builder.defineList("tier2_weights", List.of(60, 70, 25, 5, 0), o -> o instanceof Integer);
		TIER3_WEIGHTS = builder.defineList("tier3_weights", List.of(20, 50, 60, 20, 3), o -> o instanceof Integer);
		TIER4_WEIGHTS = builder.defineList("tier4_weights", List.of(0, 15, 50, 45, 12), o -> o instanceof Integer);
		builder.pop();

		builder.push("rarity");
		GUN_RARITY = builder.defineEnum("gun_rarity", Rarity.UNCOMMON);
		GUN_WITH_ATTACHMENT_RARITY = builder.defineEnum("gun_with_attachment_rarity", Rarity.EPIC);
		ATTACHMENT_RARITY = builder.defineEnum("attachment_rarity", Rarity.COMMON);
		CONSUMABLE_RARITY = builder.defineEnum("consumable_rarity", Rarity.COMMON);
		MELEE_RARITY = builder.defineEnum("melee_rarity", Rarity.RARE);
		THROWABLE_RARITY = builder.defineEnum("throwable_rarity", Rarity.RARE);
		SHIELD_RARITY = builder.defineEnum("shield_rarity", Rarity.LEGENDARY);
		ID_OVERRIDES = builder.defineList("id_overrides", List.of(), o -> o instanceof String);
		builder.pop();

		builder.push("jackpot");
		JACKPOT_ENABLED = builder.define("jackpot_enabled", true);
		JACKPOT_MIN_BLOCKS = builder.defineInRange("min_blocks", 5, 2, 64);
		JACKPOT_RADIUS = builder.defineInRange("radius", 16, 1, 128);
		JACKPOT_WINDOW_SECONDS = builder.defineInRange("window_seconds", 10, 1, 600);
		JACKPOT_REWARD_SETS = builder.defineInRange("reward_sets", 6, 1, 16);
		JACKPOT_REWARD_TIER = builder.defineInRange("reward_tier", 4, 1, 4);
		JACKPOT_ANNOUNCE = builder.define("announce", true);
		JACKPOT_SOUND = builder.define("sound", true);
		builder.pop();

		builder.push("advancements");
		ADVANCEMENTS_ENABLED = builder.define("advancements_enabled", true);
		builder.pop();

		builder.push("rendering");
		RENDER_ENABLED = builder.define("render_enabled", true);
		RENDER_SCALE = builder.defineInRange("render_scale", 0.6D, 0.05D, 4.0D);
		RENDER_SPIN = builder.defineInRange("render_spin", 90.0D, 0.0D, 3600.0D);
		RENDER_BOB = builder.defineInRange("render_bob", 0.15D, 0.0D, 2.0D);
		builder.pop();

		SPEC = builder.build();
	}

	public static int intOf(ForgeConfigSpec.IntValue value) {
		try {
			Integer result = value.get();
			return result != null ? result : value.getDefault();
		} catch (IllegalStateException e) {
			return value.getDefault();
		}
	}

	public static boolean boolOf(ForgeConfigSpec.BooleanValue value) {
		try {
			Boolean result = value.get();
			return result != null ? result : value.getDefault();
		} catch (IllegalStateException e) {
			return value.getDefault();
		}
	}

	public static double doubleOf(ForgeConfigSpec.DoubleValue value) {
		try {
			Double result = value.get();
			return result != null ? result : value.getDefault();
		} catch (IllegalStateException e) {
			return value.getDefault();
		}
	}

	public static Rarity enumOf(ForgeConfigSpec.EnumValue<Rarity> value) {
		try {
			Rarity result = value.get();
			return result != null ? result : value.getDefault();
		} catch (IllegalStateException e) {
			return value.getDefault();
		}
	}

	public static <T> List<T> listOf(ForgeConfigSpec.ConfigValue<List<? extends T>> value) {
		List<? extends T> raw;
		try {
			raw = value.get();
		} catch (IllegalStateException e) {
			raw = value.getDefault();
		}
		if (raw == null) {
			return List.of();
		}
		List<T> copy = new java.util.ArrayList<>();
		for (T element : raw) {
			if (element != null) {
				copy.add(element);
			}
		}
		return copy;
	}

	private LuckyBlockConfig() {
	}
}
