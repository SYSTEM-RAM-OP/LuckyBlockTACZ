package net.mcreator.luckyblocktacz;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;

import javax.annotation.Nullable;

import net.mcreator.luckyblocktacz.advancement.LuckyAdvancements;
import net.mcreator.luckyblocktacz.config.LuckyBlockConfig;
import net.mcreator.luckyblocktacz.loot.Rarity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LuckyBlockLoot {

    private enum Kind {
        GUN,
        GUN_WITH_ATTACHMENT,
        ATTACHMENT,
        CONSUMABLE,
        MELEE,
        THROWABLE,
        SHIELD
    }

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
        String secondaryMod;
        String key;
        Kind kind;
        Rarity rarity;

        LootSet(List<LootEntry> entries, String requiredMod) {
            this(entries, requiredMod, null);
        }

        LootSet(List<LootEntry> entries, String requiredMod, String secondaryMod) {
            this.entries = entries;
            this.requiredMod = requiredMod;
            this.secondaryMod = secondaryMod;
        }

        LootSet tag(String key, Kind kind) {
            this.key = key;
            this.kind = kind;
            return this;
        }

        boolean isAvailable() {
            if (requiredMod != null && !net.minecraftforge.fml.ModList.get().isLoaded(requiredMod)) return false;
            if (secondaryMod != null && !net.minecraftforge.fml.ModList.get().isLoaded(secondaryMod)) return false;
            return true;
        }
    }

    private static final class Snapshot {
        final List<LootSet> all;
        final Map<Rarity, List<LootSet>> byRarity;
        final int[] tierChances;
        final int[][] tierWeights;

        Snapshot(List<LootSet> all, Map<Rarity, List<LootSet>> byRarity, int[] tierChances, int[][] tierWeights) {
            this.all = all;
            this.byRarity = byRarity;
            this.tierChances = tierChances;
            this.tierWeights = tierWeights;
        }

        List<LootSet> bucket(Rarity rarity) {
            List<LootSet> list = byRarity.get(rarity);
            return list != null ? list : List.of();
        }
    }

    private static final int[] DEFAULT_TIER_CHANCES = {45, 30, 18, 7};

    private static final int[][] DEFAULT_TIER_WEIGHTS = {
        {100, 40, 8, 0, 0},
        {60, 70, 25, 5, 0},
        {20, 50, 60, 20, 3},
        {0, 15, 50, 45, 12}
    };

    private static volatile Snapshot CACHE;

    private static final List<LootSet> LOOT_TABLE = new ArrayList<>();

    private static LootSet gun(String gunId, String ammoId) {
        return new LootSet(List.of(
            new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:" + gunId + "\"}", 1, null),
            new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:" + ammoId + "\"}", 60, null)
        ), null).tag("gun:" + gunId, Kind.GUN);
    }

    private static LootSet lrMelee(String id) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"lrtactical:" + id + "\"}", 1, "lrtactical")
        ), "lrtactical").tag("melee:" + id, Kind.MELEE);
    }

    private static LootSet flashShield() {
        return new LootSet(List.of(
            new LootEntry("lrtactical:flash_shield", null, 1, "lrtactical")
        ), "lrtactical").tag("shield:flash", Kind.SHIELD);
    }

    private static LootSet deltaMelee(String id) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:" + id + "\"}", 1, "lrtactical")
        ), "lrtactical", "delta_wt").tag("delta:" + id, Kind.MELEE);
    }

    private static LootSet throwable(String id) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:" + id + "\"}", 1, "lrtactical")
        ), "lrtactical").tag("throwable:" + id, Kind.THROWABLE);
    }

    private static LootSet attachment(String id) {
        return new LootSet(List.of(
            new LootEntry("tacz:attachment", "{\"AttachmentId\":\"tacz:" + id + "\"}", 1, null)
        ), null).tag("attachment:" + id, Kind.ATTACHMENT);
    }

    private static LootSet gunWithAttachment(String gunId, String ammoId, String attachmentId, int ammoCount) {
        return new LootSet(List.of(
            new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:" + gunId + "\"}", 1, null),
            new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:" + ammoId + "\"}", ammoCount, null),
            new LootEntry("tacz:attachment", "{\"AttachmentId\":\"tacz:" + attachmentId + "\"}", 1, null)
        ), null).tag("gunatt:" + gunId, Kind.GUN_WITH_ATTACHMENT);
    }

    private static LootSet consumable(String id, int count) {
        return new LootSet(List.of(
            new LootEntry("lrtactical:consumable", "{\"ConsumableId\":\"lrtactical:" + id + "\"}", count, "lrtactical")
        ), "lrtactical").tag("consumable:" + id, Kind.CONSUMABLE);
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
        LOOT_TABLE.add(deltaMelee("annihil"));
        LOOT_TABLE.add(deltaMelee("cobra_vanguard"));
        LOOT_TABLE.add(deltaMelee("executor"));
        LOOT_TABLE.add(deltaMelee("mercy"));
        LOOT_TABLE.add(deltaMelee("null_annihil"));
        LOOT_TABLE.add(deltaMelee("polaris"));
        LOOT_TABLE.add(deltaMelee("quenching"));
        LOOT_TABLE.add(deltaMelee("shadowkiller"));
        LOOT_TABLE.add(deltaMelee("silencefd"));
        LOOT_TABLE.add(deltaMelee("thieve_sea"));
        LOOT_TABLE.add(deltaMelee("treasure_kn"));
        LOOT_TABLE.add(throwable("c4"));
        LOOT_TABLE.add(throwable("flash_grenade"));
        LOOT_TABLE.add(throwable("m67"));
        LOOT_TABLE.add(throwable("molotov"));
        LOOT_TABLE.add(throwable("rgn"));
        LOOT_TABLE.add(throwable("smoke_grenade"));

        LOOT_TABLE.add(gunWithAttachment("ak47", "762x39", "scope_pso_1", 90));
        LOOT_TABLE.add(gunWithAttachment("ak74m", "545x39", "sight_okp7", 90));
        LOOT_TABLE.add(gunWithAttachment("m4a1", "556x45", "scope_acog_ta31", 90));
        LOOT_TABLE.add(gunWithAttachment("hk416d", "556x45", "sight_t2", 90));
        LOOT_TABLE.add(gunWithAttachment("scar_l", "556x45", "scope_elcan_4x", 90));
        LOOT_TABLE.add(gunWithAttachment("aug", "556x45", "scope_aug_default", 90));
        LOOT_TABLE.add(gunWithAttachment("m16a4", "556x45", "sight_acro_rifle", 90));
        LOOT_TABLE.add(gunWithAttachment("mk18", "556x45", "sight_uh1", 90));
        LOOT_TABLE.add(gunWithAttachment("hk_g3", "308", "scope_retro_2x", 80));
        LOOT_TABLE.add(gunWithAttachment("scar_h", "308", "scope_vudu", 80));
        LOOT_TABLE.add(gunWithAttachment("mk14", "308", "scope_mk5hd", 80));
        LOOT_TABLE.add(gunWithAttachment("svd", "762x54", "scope_pso_1", 80));
        LOOT_TABLE.add(gunWithAttachment("m700", "30_06", "scope_standard_8x", 60));
        LOOT_TABLE.add(gunWithAttachment("ai_awp", "338", "scope_amg_10", 60));
        LOOT_TABLE.add(gunWithAttachment("mrad", "338", "scope_mk4ert", 60));
        LOOT_TABLE.add(gunWithAttachment("m107", "50bmg", "scope_xm157", 40));
        LOOT_TABLE.add(gunWithAttachment("kar98", "792x57", "scope_98k", 60));
        LOOT_TABLE.add(gunWithAttachment("scar_mk20", "308", "scope_lpvo_1_6", 80));
        LOOT_TABLE.add(gunWithAttachment("sks_tactical", "762x39", "scope_hamr", 90));
        LOOT_TABLE.add(gunWithAttachment("qbz_95", "58x42", "sight_exp3", 90));
        LOOT_TABLE.add(gunWithAttachment("p90", "57x28", "sight_p90", 120));
        LOOT_TABLE.add(gunWithAttachment("hk_mp5a5", "9mm", "sight_t1", 120));
        LOOT_TABLE.add(gunWithAttachment("hk_mp7a1", "46x30", "sight_rmr_dot", 120));
        LOOT_TABLE.add(gunWithAttachment("uzi", "9mm", "sight_sro_dot", 120));
        LOOT_TABLE.add(gunWithAttachment("vector45", "45acp", "sight_deltapoint_rifle", 120));
        LOOT_TABLE.add(gunWithAttachment("ump45", "45acp", "sight_coyote", 120));
        LOOT_TABLE.add(gunWithAttachment("ak12", "545x39", "sight_1p87", 90));
        LOOT_TABLE.add(gunWithAttachment("g36k", "556x45", "sight_exp3", 90));
        LOOT_TABLE.add(gunWithAttachment("m249", "556x45", "sight_apm4s", 150));
        LOOT_TABLE.add(gunWithAttachment("rpk", "762x39", "sight_okp7", 150));
        LOOT_TABLE.add(gunWithAttachment("pkm", "762x54", "sight_pk06_rifle", 150));
        LOOT_TABLE.add(gunWithAttachment("m870", "12g", "sight_fastfire_rifle", 48));
        LOOT_TABLE.add(gunWithAttachment("spas_12", "12g", "sight_fastfire_rifle", 48));
        LOOT_TABLE.add(gunWithAttachment("m1014", "12g", "sight_fastfire_rifle", 48));
        LOOT_TABLE.add(gunWithAttachment("aa12", "12g", "sight_fastfire_rifle", 60));
        LOOT_TABLE.add(gunWithAttachment("glock_17", "9mm", "sight_rmr_dot", 120));
        LOOT_TABLE.add(gunWithAttachment("m1911", "45acp", "sight_deltapoint_pistol", 120));
        LOOT_TABLE.add(gunWithAttachment("deagle", "50ae", "sight_acro_pistol", 90));
        LOOT_TABLE.add(gunWithAttachment("rhino357", "357mag", "sight_pk06_pistol", 90));
        LOOT_TABLE.add(gunWithAttachment("taurus500", "500mag", "sight_fastfire_pistol", 60));
        LOOT_TABLE.add(gunWithAttachment("hm50b", "50bmg", "scope_xl50", 40));
        LOOT_TABLE.add(gunWithAttachment("rpg7", "rpg_rocket", "sight_okp7", 8));
        LOOT_TABLE.add(gunWithAttachment("m320", "40mm", "sight_okp7", 24));
        LOOT_TABLE.add(gunWithAttachment("minigun", "308", "sight_acro_rifle", 300));
        LOOT_TABLE.add(gunWithAttachment("fn_fal", "308", "scope_elcan_4x", 80));
        LOOT_TABLE.add(gunWithAttachment("fn_evolys", "308", "sight_t2", 90));
        LOOT_TABLE.add(gunWithAttachment("mcx_spear_lt", "300blk", "sight_uh1", 120));
        LOOT_TABLE.add(gunWithAttachment("springfield1873", "45_70", "scope_1873_6x", 60));
        LOOT_TABLE.add(gunWithAttachment("m1866", "45acp", "scope_1866_4x", 90));
        LOOT_TABLE.add(gunWithAttachment("type_81", "762x39", "sight_okp7", 120));

        LOOT_TABLE.add(attachment("scope_standard_8x"));
        LOOT_TABLE.add(attachment("scope_acog_ta31"));
        LOOT_TABLE.add(attachment("scope_pso_1"));
        LOOT_TABLE.add(attachment("scope_elcan_4x"));
        LOOT_TABLE.add(attachment("scope_lpvo_1_6"));
        LOOT_TABLE.add(attachment("scope_vudu"));
        LOOT_TABLE.add(attachment("scope_mk5hd"));
        LOOT_TABLE.add(attachment("scope_reap_ir"));
        LOOT_TABLE.add(attachment("scope_hamr"));
        LOOT_TABLE.add(attachment("scope_xm157"));
        LOOT_TABLE.add(attachment("sight_t1"));
        LOOT_TABLE.add(attachment("sight_t2"));
        LOOT_TABLE.add(attachment("sight_552"));
        LOOT_TABLE.add(attachment("sight_uh1"));
        LOOT_TABLE.add(attachment("sight_coyote"));
        LOOT_TABLE.add(attachment("sight_rmr_dot"));
        LOOT_TABLE.add(attachment("sight_sro_dot"));
        LOOT_TABLE.add(attachment("sight_acro_rifle"));
        LOOT_TABLE.add(attachment("sight_acro_pistol"));
        LOOT_TABLE.add(attachment("sight_okp7"));
        LOOT_TABLE.add(attachment("sight_1p87"));
        LOOT_TABLE.add(attachment("sight_exp3"));
        LOOT_TABLE.add(attachment("sight_apm4s"));
        LOOT_TABLE.add(attachment("sight_deltapoint_rifle"));
        LOOT_TABLE.add(attachment("sight_deltapoint_pistol"));
        LOOT_TABLE.add(attachment("sight_fastfire_rifle"));
        LOOT_TABLE.add(attachment("sight_fastfire_pistol"));
        LOOT_TABLE.add(attachment("sight_pk06_rifle"));
        LOOT_TABLE.add(attachment("sight_pk06_pistol"));
        LOOT_TABLE.add(attachment("sight_srs_02"));
        LOOT_TABLE.add(attachment("sight_p90"));
        LOOT_TABLE.add(attachment("muzzle_silencer_ursus"));
        LOOT_TABLE.add(attachment("muzzle_silencer_mirage"));
        LOOT_TABLE.add(attachment("muzzle_silencer_phantom_s1"));
        LOOT_TABLE.add(attachment("muzzle_silencer_knight_qd"));
        LOOT_TABLE.add(attachment("muzzle_silencer_socom_rc1"));
        LOOT_TABLE.add(attachment("muzzle_silencer_vulture"));
        LOOT_TABLE.add(attachment("muzzle_silencer_wraith"));
        LOOT_TABLE.add(attachment("muzzle_silencer_ptilopsis"));
        LOOT_TABLE.add(attachment("muzzle_silencer_sg"));
        LOOT_TABLE.add(attachment("muzzle_brake_cthulhu"));
        LOOT_TABLE.add(attachment("muzzle_brake_cyclone_d2"));
        LOOT_TABLE.add(attachment("muzzle_brake_pioneer"));
        LOOT_TABLE.add(attachment("muzzle_brake_trex"));
        LOOT_TABLE.add(attachment("muzzle_brake_timeless50"));
        LOOT_TABLE.add(attachment("muzzle_brake_mastiff_sg"));
        LOOT_TABLE.add(attachment("muzzle_compensator_trident"));
        LOOT_TABLE.add(attachment("muzzle_choke_sg"));
        LOOT_TABLE.add(attachment("muzzle_duckbill_sg"));
        LOOT_TABLE.add(attachment("grip_vertical_military"));
        LOOT_TABLE.add(attachment("grip_vertical_ranger"));
        LOOT_TABLE.add(attachment("grip_vertical_talon"));
        LOOT_TABLE.add(attachment("grip_cobra"));
        LOOT_TABLE.add(attachment("grip_cqr"));
        LOOT_TABLE.add(attachment("grip_magpul_afg"));
        LOOT_TABLE.add(attachment("grip_magpul_afg_2"));
        LOOT_TABLE.add(attachment("grip_osovets_black"));
        LOOT_TABLE.add(attachment("grip_rk0"));
        LOOT_TABLE.add(attachment("grip_rk1_b25u"));
        LOOT_TABLE.add(attachment("grip_rk6"));
        LOOT_TABLE.add(attachment("grip_se_5"));
        LOOT_TABLE.add(attachment("grip_td"));
        LOOT_TABLE.add(attachment("grip_troy"));
        LOOT_TABLE.add(attachment("laser_compact"));
        LOOT_TABLE.add(attachment("laser_lopro"));
        LOOT_TABLE.add(attachment("laser_ngal"));
        LOOT_TABLE.add(attachment("laser_nightstick"));
        LOOT_TABLE.add(attachment("laser_peq15"));
        LOOT_TABLE.add(attachment("laser_peq6"));
        LOOT_TABLE.add(attachment("stock_ak12"));
        LOOT_TABLE.add(attachment("stock_carbon_bone_c5"));
        LOOT_TABLE.add(attachment("stock_heavy_ctr"));
        LOOT_TABLE.add(attachment("stock_heavy_spas_12"));
        LOOT_TABLE.add(attachment("stock_hk_slim_line"));
        LOOT_TABLE.add(attachment("stock_m4ss"));
        LOOT_TABLE.add(attachment("stock_militech_b5"));
        LOOT_TABLE.add(attachment("stock_moe"));
        LOOT_TABLE.add(attachment("stock_psg"));
        LOOT_TABLE.add(attachment("stock_ripstock"));
        LOOT_TABLE.add(attachment("stock_sba3"));
        LOOT_TABLE.add(attachment("stock_tactical_ar"));
        LOOT_TABLE.add(attachment("stock_tactical_spas_12"));
        LOOT_TABLE.add(attachment("extended_mag_1"));
        LOOT_TABLE.add(attachment("extended_mag_2"));
        LOOT_TABLE.add(attachment("extended_mag_3"));
        LOOT_TABLE.add(attachment("light_extended_mag_1"));
        LOOT_TABLE.add(attachment("light_extended_mag_2"));
        LOOT_TABLE.add(attachment("light_extended_mag_3"));
        LOOT_TABLE.add(attachment("shotgun_extended_mag_1"));
        LOOT_TABLE.add(attachment("shotgun_extended_mag_2"));
        LOOT_TABLE.add(attachment("shotgun_extended_mag_3"));
        LOOT_TABLE.add(attachment("sniper_extended_mag_1"));
        LOOT_TABLE.add(attachment("sniper_extended_mag_2"));
        LOOT_TABLE.add(attachment("sniper_extended_mag_3"));
        LOOT_TABLE.add(attachment("bayonet_m9"));
        LOOT_TABLE.add(attachment("bayonet_6h3"));
        LOOT_TABLE.add(attachment("ammo_mod_fmj"));
        LOOT_TABLE.add(attachment("ammo_mod_hp"));
        LOOT_TABLE.add(attachment("ammo_mod_he"));
        LOOT_TABLE.add(attachment("ammo_mod_i"));
        LOOT_TABLE.add(attachment("ammo_mod_slug"));
        LOOT_TABLE.add(attachment("ammo_mod_overpressure_recoil"));
        LOOT_TABLE.add(attachment("oem_stock_light"));
        LOOT_TABLE.add(attachment("oem_stock_tactical"));
        LOOT_TABLE.add(attachment("oem_stock_heavy"));
        LOOT_TABLE.add(attachment("oem_sight_1"));
        LOOT_TABLE.add(attachment("oem_sight_2"));
        LOOT_TABLE.add(attachment("oem_sight_aug"));
        LOOT_TABLE.add(attachment("oem_sight_hk"));
        LOOT_TABLE.add(attachment("oem_barrel_cannon"));
        LOOT_TABLE.add(attachment("oem_grip_cover"));
        LOOT_TABLE.add(attachment("oem_side_cover"));
        LOOT_TABLE.add(attachment("oem_m16a1_handguard_a2"));
        LOOT_TABLE.add(attachment("oem_m1897_bayonet"));
        LOOT_TABLE.add(attachment("oem_stock_removed"));
        LOOT_TABLE.add(attachment("oem_stock_unfolded"));
        LOOT_TABLE.add(attachment("deagle_golden_long_barrel"));

        LOOT_TABLE.add(consumable("ai2", 2));
        LOOT_TABLE.add(consumable("carfak", 1));
        LOOT_TABLE.add(consumable("cms", 1));
        LOOT_TABLE.add(consumable("surv12", 1));
        LOOT_TABLE.add(consumable("blood_pack", 2));
        LOOT_TABLE.add(consumable("condensed_milk", 3));
        LOOT_TABLE.add(consumable("ibuprofen", 2));
        LOOT_TABLE.add(consumable("amoxycillin", 2));
        LOOT_TABLE.add(consumable("goldenstar", 2));
        LOOT_TABLE.add(consumable("vaseline", 2));
        LOOT_TABLE.add(lrMelee("dagger"));
        LOOT_TABLE.add(lrMelee("baseball_bat"));
        LOOT_TABLE.add(lrMelee("karambit"));
        LOOT_TABLE.add(flashShield());
    }

    public static synchronized void rebuildCache() {
        CACHE = buildSnapshot();
    }

    public static int cachedSetCount() {
        return snapshot().all.size();
    }

    private static Snapshot snapshot() {
        Snapshot current = CACHE;
        if (current == null) {
            rebuildCache();
            current = CACHE;
        }
        return current;
    }

    private static Snapshot buildSnapshot() {
        Map<Rarity, List<LootSet>> buckets = new EnumMap<>(Rarity.class);
        for (Rarity rarity : Rarity.ORDERED) {
            buckets.put(rarity, new ArrayList<>());
        }
        List<LootSet> available = new ArrayList<>();
        for (LootSet set : LOOT_TABLE) {
            if (!set.isAvailable()) continue;
            Rarity rarity = classify(set);
            set.rarity = rarity;
            available.add(set);
            buckets.get(rarity).add(set);
        }
        Map<Rarity, List<LootSet>> frozen = new EnumMap<>(Rarity.class);
        for (Rarity rarity : Rarity.ORDERED) {
            frozen.put(rarity, List.copyOf(buckets.get(rarity)));
        }
        return new Snapshot(List.copyOf(available), frozen, readTierChances(), readTierWeights());
    }

    private static Rarity classify(LootSet set) {
        Rarity override = overrideFor(set);
        if (override != null) return override;
        Kind kind = set.kind;
        if (kind == null) return Rarity.COMMON;
        switch (kind) {
            case GUN:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.GUN_RARITY));
            case GUN_WITH_ATTACHMENT:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.GUN_WITH_ATTACHMENT_RARITY));
            case ATTACHMENT:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.ATTACHMENT_RARITY));
            case CONSUMABLE:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.CONSUMABLE_RARITY));
            case MELEE:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.MELEE_RARITY));
            case THROWABLE:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.THROWABLE_RARITY));
            case SHIELD:
            default:
                return fallback(LuckyBlockConfig.enumOf(LuckyBlockConfig.SHIELD_RARITY));
        }
    }

    private static Rarity fallback(Rarity rarity) {
        return rarity != null ? rarity : Rarity.COMMON;
    }

    private static Rarity overrideFor(LootSet set) {
        if (set.key == null) return null;
        List<String> overrides = LuckyBlockConfig.listOf(LuckyBlockConfig.ID_OVERRIDES);
        for (String line : overrides) {
            if (line == null) continue;
            int split = line.indexOf('=');
            if (split <= 0 || split >= line.length() - 1) continue;
            String rawKey = line.substring(0, split).trim();
            if (!matchesKey(rawKey, set.key)) continue;
            String name = line.substring(split + 1).trim().toUpperCase(Locale.ROOT);
            try {
                Rarity parsed = Rarity.valueOf(name);
                if (parsed != null) return parsed;
            } catch (IllegalArgumentException ignored) {
            }
        }
        return null;
    }

    private static boolean matchesKey(String overrideKey, String setKey) {
        if (overrideKey.equals(setKey)) return true;
        String bare = overrideKey;
        if (bare.startsWith("tacz:")) bare = bare.substring(5);
        else if (bare.startsWith("minecraft:")) bare = bare.substring(10);
        else if (bare.startsWith("lrtactical:")) bare = bare.substring(11);
        if (bare.indexOf(':') >= 0) return false;
        int colon = setKey.indexOf(':');
        String id = colon >= 0 ? setKey.substring(colon + 1) : setKey;
        return bare.equals(id);
    }

    private static int[] readTierChances() {
        int[] result = DEFAULT_TIER_CHANCES.clone();
        List<Integer> raw = LuckyBlockConfig.listOf(LuckyBlockConfig.TIER_CHANCES);
        if (raw.size() != result.length) return DEFAULT_TIER_CHANCES.clone();
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            Integer value = raw.get(i);
            if (value == null || value < 0) return DEFAULT_TIER_CHANCES.clone();
            result[i] = value;
            sum += value;
        }
        return sum > 0 ? result : DEFAULT_TIER_CHANCES.clone();
    }

    private static int[][] readTierWeights() {
        int[][] result = new int[DEFAULT_TIER_WEIGHTS.length][];
        result[0] = readWeights(LuckyBlockConfig.listOf(LuckyBlockConfig.TIER1_WEIGHTS), DEFAULT_TIER_WEIGHTS[0]);
        result[1] = readWeights(LuckyBlockConfig.listOf(LuckyBlockConfig.TIER2_WEIGHTS), DEFAULT_TIER_WEIGHTS[1]);
        result[2] = readWeights(LuckyBlockConfig.listOf(LuckyBlockConfig.TIER3_WEIGHTS), DEFAULT_TIER_WEIGHTS[2]);
        result[3] = readWeights(LuckyBlockConfig.listOf(LuckyBlockConfig.TIER4_WEIGHTS), DEFAULT_TIER_WEIGHTS[3]);
        return result;
    }

    private static int[] readWeights(List<Integer> raw, int[] defaults) {
        int[] result = defaults.clone();
        if (raw.size() != result.length) return defaults.clone();
        for (int i = 0; i < result.length; i++) {
            Integer value = raw.get(i);
            if (value == null || value < 0) return defaults.clone();
            result[i] = value;
        }
        return result;
    }

    private static int pickTier(Snapshot snapshot, RandomSource random) {
        int[] chances = snapshot.tierChances;
        int total = 0;
        for (int chance : chances) total += chance;
        if (total <= 0) return random.nextInt(chances.length);
        int roll = random.nextInt(total);
        for (int i = 0; i < chances.length; i++) {
            if (roll < chances[i]) return i;
            roll -= chances[i];
        }
        return 0;
    }

    private static LootSet pick(Snapshot snapshot, RandomSource random, int forcedTier) {
        if (snapshot.all.isEmpty()) return null;
        int tier = forcedTier >= 0 && forcedTier < snapshot.tierWeights.length ? forcedTier : pickTier(snapshot, random);
        int[] weights = snapshot.tierWeights[tier];
        int availableWeight = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > 0 && !snapshot.bucket(Rarity.ORDERED[i]).isEmpty()) availableWeight += weights[i];
        }
        if (availableWeight > 0) {
            int roll = random.nextInt(availableWeight);
            for (int i = 0; i < weights.length; i++) {
                List<LootSet> bucket = snapshot.bucket(Rarity.ORDERED[i]);
                if (weights[i] <= 0 || bucket.isEmpty()) continue;
                if (roll < weights[i]) return bucket.get(random.nextInt(bucket.size()));
                roll -= weights[i];
            }
        }
        for (Rarity rarity : Rarity.ORDERED) {
            List<LootSet> bucket = snapshot.bucket(rarity);
            if (!bucket.isEmpty()) return bucket.get(random.nextInt(bucket.size()));
        }
        return snapshot.all.get(random.nextInt(snapshot.all.size()));
    }

    public static void dropRandomGunAndAmmo(Level level, BlockPos pos) {
        dropRandomGunAndAmmo(level, pos, null);
    }

    public static void dropRandomGunAndAmmo(Level level, BlockPos pos, @Nullable ServerPlayer player) {
        if (level.isClientSide()) return;
        if (!LuckyBlockConfig.boolOf(LuckyBlockConfig.DROP_ENABLED)) return;

        Snapshot snapshot = snapshot();
        if (snapshot.all.isEmpty()) return;

        RandomSource random = level.random;
        int wanted = Math.max(1, LuckyBlockConfig.intOf(LuckyBlockConfig.DROP_SETS_PER_BREAK));
        double scatter = Math.max(0.0D, LuckyBlockConfig.doubleOf(LuckyBlockConfig.SCATTER_RADIUS));

        List<LootSet> chosen = new ArrayList<>();
        int attempts = 0;
        int maxAttempts = Math.max(8, wanted * 8);
        while (chosen.size() < wanted && attempts < maxAttempts) {
            attempts++;
            LootSet candidate = pick(snapshot, random, -1);
            if (candidate == null) break;
            boolean duplicate = false;
            for (LootSet picked : chosen) {
                if (picked == candidate) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) chosen.add(candidate);
            if (chosen.size() >= snapshot.all.size()) break;
        }

        for (LootSet set : chosen) {
            for (LootEntry entry : set.entries) {
                spawnItem(level, pos, entry, scatter);
            }
        }
        if (player != null) {
            awardDropAdvancements(player, chosen);
        }
    }

    public static void dropJackpot(Level level, BlockPos pos) {
        if (level.isClientSide()) return;
        Snapshot snapshot = snapshot();
        if (snapshot.all.isEmpty()) return;
        int sets = Math.max(1, LuckyBlockConfig.intOf(LuckyBlockConfig.JACKPOT_REWARD_SETS));
        int tier = Math.max(1, LuckyBlockConfig.intOf(LuckyBlockConfig.JACKPOT_REWARD_TIER)) - 1;
        double scatter = Math.max(0.5D, LuckyBlockConfig.doubleOf(LuckyBlockConfig.SCATTER_RADIUS));
        for (int i = 0; i < sets; i++) {
            LootSet set = pick(snapshot, level.random, tier);
            if (set == null) break;
            for (LootEntry entry : set.entries) {
                spawnItem(level, pos, entry, scatter);
            }
        }
    }

    private static void awardDropAdvancements(ServerPlayer player, List<LootSet> chosen) {
        boolean gun = false;
        boolean epic = false;
        for (LootSet set : chosen) {
            if (set.kind == Kind.GUN || set.kind == Kind.GUN_WITH_ATTACHMENT) gun = true;
            Rarity rarity = set.rarity;
            if (rarity != null && rarity.index() >= Rarity.EPIC.index()) epic = true;
        }
        if (gun) LuckyAdvancements.award(player, "first_gun");
        if (epic) LuckyAdvancements.award(player, "first_epic");
    }

    private static void spawnItem(Level level, BlockPos pos, LootEntry entry, double scatter) {
        spawnItem(level, pos, entry.baseItem, entry.nbtString, entry.count, scatter);
    }

    private static void spawnItem(Level level, BlockPos pos, String itemId, String nbtString, int count) {
        spawnItem(level, pos, itemId, nbtString, count, 0.0D);
    }

    private static void spawnItem(Level level, BlockPos pos, String itemId, String nbtString, int count, double scatter) {
        ItemStack stack = new ItemStack(net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue(net.minecraft.resources.ResourceLocation.tryParse(itemId)));
        stack.setCount(count);
        if (nbtString != null && !nbtString.isEmpty()) {
            try {
                CompoundTag tag = net.minecraft.nbt.TagParser.parseTag(nbtString);
                stack.setTag(tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        double offsetX = 0.0D;
        double offsetZ = 0.0D;
        if (scatter > 0.0D) {
            offsetX = (level.random.nextDouble() * 2.0D - 1.0D) * scatter;
            offsetZ = (level.random.nextDouble() * 2.0D - 1.0D) * scatter;
        }
        ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5D + offsetX, pos.getY() + 0.5D, pos.getZ() + 0.5D + offsetZ, stack);
        level.addFreshEntity(itemEntity);
    }
}
