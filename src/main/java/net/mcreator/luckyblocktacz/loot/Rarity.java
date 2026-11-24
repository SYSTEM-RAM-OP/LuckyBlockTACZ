package net.mcreator.luckyblocktacz.loot;

public enum Rarity {
	COMMON,
	UNCOMMON,
	RARE,
	EPIC,
	LEGENDARY;

	public static final Rarity[] ORDERED = values();

	public int index() {
		return ordinal();
	}
}
