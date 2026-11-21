package net.mcreator.luckyblocktacz;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

public class LuckyBlockLoot {

    private static class LootEntry {
        String baseItem;
        String nbtString;
        int count;
        String requiredMod;

        LootEntry(String baseItem, String nbtString, int count, String requiredMod) {
            this.baseItem = baseItem;
            this.nbtString = nbtString;
            this.count = count;
            this.requiredMod = requiredMod;
        }
    }

    private static class LootSet {
        List<LootEntry> entries;
        String requiredMod;

        LootSet(List<LootEntry> entries, String requiredMod) {
            this.entries = entries;
            this.requiredMod = requiredMod;
        }
    }

    private static final List<LootSet> LOOT_TABLE = new ArrayList<>();

    private static LootSet gun(String gunId, String ammoId) {
        return new LootSet(List.of(
            new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:" + gunId + "\"}", 1, null),
            new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:" + ammoId + "\"}", 60, null)
        ), null);
    }

    private static LootSet melee(String id) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:" + id + "\"}", 1, "lrtactical")
        ), "lrtactical");
    }

    private static LootSet throwable(String id) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:" + id + "\"}", 1, "lrtactical")
        ), "lrtactical");
    }

    static {
        LOOT_TABLE.add(gun("aa12", "12g"));
        LOOT_TABLE.add(gun("ai_awp", "338"));
        LOOT_TABLE.add(gun("ak12", "545x39"));
        LOOT_TABLE.add(gun("ak47", "762x39"));
        LOOT_TABLE.add(gun("ak74m", "545x39"));
        LOOT_TABLE.add(gun("aug", "556x45"));
        LOOT_TABLE.add(gun("b93r", "9mm"));
        LOOT_TABLE.add(gun("cz75", "9mm"));
        LOOT_TABLE.add(gun("db_long", "12g"));
        LOOT_TABLE.add(gun("db_short", "12g"));
        LOOT_TABLE.add(gun("deagle", "50ae"));
        LOOT_TABLE.add(gun("deagle_golden", "357mag"));
        LOOT_TABLE.add(gun("fn_evolys", "308"));
        LOOT_TABLE.add(gun("fn_fal", "308"));
        LOOT_TABLE.add(gun("g36k", "556x45"));
        LOOT_TABLE.add(gun("glock_17", "9mm"));
        LOOT_TABLE.add(gun("hcar", "30_06"));
        LOOT_TABLE.add(gun("hk416d", "556x45"));
        LOOT_TABLE.add(gun("hk_g3", "308"));
        LOOT_TABLE.add(gun("hk_mk23", "45acp"));
        LOOT_TABLE.add(gun("hk_mp5a5", "9mm"));
        LOOT_TABLE.add(gun("hk_mp7a1", "46x30"));
        LOOT_TABLE.add(gun("hm50b", "50bmg"));
        LOOT_TABLE.add(gun("kar98", "792x57"));
        LOOT_TABLE.add(gun("lonetrail", "30_06"));
        LOOT_TABLE.add(gun("m1014", "12g"));
        LOOT_TABLE.add(gun("m107", "50bmg"));
        LOOT_TABLE.add(gun("m16a1", "556x45"));
        LOOT_TABLE.add(gun("m16a4", "556x45"));
        LOOT_TABLE.add(gun("m1866", "45acp"));
        LOOT_TABLE.add(gun("m1897", "12g"));
        LOOT_TABLE.add(gun("m1911", "45acp"));
        LOOT_TABLE.add(gun("m249", "556x45"));
        LOOT_TABLE.add(gun("m320", "40mm"));
        LOOT_TABLE.add(gun("m4a1", "556x45"));
        LOOT_TABLE.add(gun("m700", "30_06"));
        LOOT_TABLE.add(gun("m870", "12g"));
        LOOT_TABLE.add(gun("m95", "50bmg"));
        LOOT_TABLE.add(gun("m9a4", "9mm"));
        LOOT_TABLE.add(gun("mcx_spear_lt", "300blk"));
        LOOT_TABLE.add(gun("minigun", "308"));
        LOOT_TABLE.add(gun("mk14", "308"));
        LOOT_TABLE.add(gun("mk18", "556x45"));
        LOOT_TABLE.add(gun("mrad", "338"));
        LOOT_TABLE.add(gun("p320", "45acp"));
        LOOT_TABLE.add(gun("p90", "57x28"));
        LOOT_TABLE.add(gun("pkm", "762x54"));
        LOOT_TABLE.add(gun("qbz_191", "58x42"));
        LOOT_TABLE.add(gun("qbz_95", "58x42"));
        LOOT_TABLE.add(gun("qr_24", "545x39"));
        LOOT_TABLE.add(gun("rhino357", "357mag"));
        LOOT_TABLE.add(gun("rpg7", "rpg_rocket"));
        LOOT_TABLE.add(gun("rpk", "762x39"));
        LOOT_TABLE.add(gun("scar_h", "308"));
        LOOT_TABLE.add(gun("scar_l", "556x45"));
        LOOT_TABLE.add(gun("scar_mk20", "308"));
        LOOT_TABLE.add(gun("sks_tactical", "762x39"));
        LOOT_TABLE.add(gun("spas_12", "12g"));
        LOOT_TABLE.add(gun("spr15hb", "556x45"));
        LOOT_TABLE.add(gun("springfield1873", "45_70"));
        LOOT_TABLE.add(gun("svd", "762x54"));
        LOOT_TABLE.add(gun("taurus500", "500mag"));
        LOOT_TABLE.add(gun("taurus943", "22wmr"));
        LOOT_TABLE.add(gun("tec_9", "9mm"));
        LOOT_TABLE.add(gun("timeless50", "50ae"));
        LOOT_TABLE.add(gun("type_81", "762x39"));
        LOOT_TABLE.add(gun("ump45", "45acp"));
        LOOT_TABLE.add(gun("urgi", "556x45"));
        LOOT_TABLE.add(gun("uzi", "9mm"));
        LOOT_TABLE.add(gun("vector45", "45acp"));
        LOOT_TABLE.add(melee("annihil"));
        LOOT_TABLE.add(melee("cobra_vanguard"));
        LOOT_TABLE.add(melee("executor"));
        LOOT_TABLE.add(melee("mercy"));
        LOOT_TABLE.add(melee("null_annihil"));
        LOOT_TABLE.add(melee("polaris"));
        LOOT_TABLE.add(melee("quenching"));
        LOOT_TABLE.add(melee("shadowkiller"));
        LOOT_TABLE.add(melee("silencefd"));
        LOOT_TABLE.add(melee("thieve_sea"));
        LOOT_TABLE.add(melee("treasure_kn"));
        LOOT_TABLE.add(throwable("flash_grenade"));
        LOOT_TABLE.add(throwable("m67"));
        LOOT_TABLE.add(throwable("molotov"));
        LOOT_TABLE.add(throwable("rgn"));
        LOOT_TABLE.add(throwable("smoke_grenade"));
    }

    public static void dropRandomGunAndAmmo(Level level, BlockPos pos) {
        if (level.isClientSide()) return;

        List<LootSet> validSets = new ArrayList<>();
        for (LootSet set : LOOT_TABLE) {
            if (set.requiredMod == null || net.minecraftforge.fml.ModList.get().isLoaded(set.requiredMod)) {
                validSets.add(set);
            }
        }

        if (validSets.isEmpty()) return;

        RandomSource random = level.random;
        LootSet selected = validSets.get(random.nextInt(validSets.size()));

        for (LootEntry entry : selected.entries) {
            spawnItem(level, pos, entry.baseItem, entry.nbtString, entry.count);
        }
    }

    private static void spawnItem(Level level, BlockPos pos, String itemId, String nbtString, int count) {
        ItemStack stack = new ItemStack(net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(new net.minecraft.resources.ResourceLocation(itemId)));
        stack.setCount(count);
        if (nbtString != null && !nbtString.isEmpty()) {
            try {
                CompoundTag tag = net.minecraft.nbt.TagParser.parseTag(nbtString);
                stack.setTag(tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
        level.addFreshEntity(itemEntity);
    }
}
