
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
    
    // 定义物品条目结构
    private static class LootEntry {
        String baseItem;
        String nbtString;
        int count;
        String requiredMod;

        public LootEntry(String baseItem, String nbtString, int count, String requiredMod) {
            this.baseItem = baseItem;
            this.nbtString = nbtString;
            this.count = count;
            this.requiredMod = requiredMod;
        }
    }

    // 存储所有物品的列表
    private static final List<LootEntry> LOOT_TABLE = new ArrayList<>();

    // 静态初始化块：将 Python 解析的数据填入列表
    static {
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:aa12\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ai_awp\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:338\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak12\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak47\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak74m\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:aug\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:b93r\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:cz75\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:db_long\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:db_short\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:deagle\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:50ae\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:deagle_golden\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:357mag\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:fn_evolys\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:fn_fal\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:g36k\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:glock_17\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hcar\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk416d\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_g3\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mk23\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mp5a5\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mp7a1\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:46x30\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hm50b\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:kar98\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:792x57\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:lonetrail\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1014\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m107\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m16a1\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m16a4\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1866\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1897\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1911\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m249\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m320\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:40mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m4a1\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m700\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m870\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m95\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m9a4\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mcx_spear_lt\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:300blk\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:minigun\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mk14\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mk18\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mrad\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:338\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:p320\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:p90\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:57x28\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:pkm\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x54\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qbz_191\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:58x42\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qbz_95\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:58x42\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qr_24\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rhino357\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:357mag\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rpg7\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:rpg_rocket\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rpk\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_h\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_l\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_mk20\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:sks_tactical\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:spas_12\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:spr15hb\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:springfield1873\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45_70\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:svd\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x54\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:taurus500\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:500mag\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:taurus943\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:22wmr\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:tec_9\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:timeless50\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:50ae\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:type_81\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ump45\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:urgi\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:uzi\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:vector45\"}", 1, null));
        LOOT_TABLE.add(new LootEntry("tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60, null));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:annihil\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:cobra_vanguard\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:executor\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:mercy\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:null_annihil\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:polaris\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:quenching\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:shadowkiller\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:silencefd\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:thieve_sea\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:treasure_kn\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:flash_grenade\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:m67\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:molotov\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:rgn\"}", 1, "lrtactical"));
        LOOT_TABLE.add(new LootEntry("lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:smoke_grenade\"}", 1, "lrtactical"));

    }

    public static void dropRandomGunAndAmmo(Level level, BlockPos pos) {
        if (level.isClientSide()) return;

        // 1. 过滤出当前环境已加载的模组物品
        List<LootEntry> validEntries = new ArrayList<>();
        for (LootEntry entry : LOOT_TABLE) {
            if (entry.requiredMod == null || net.minecraftforge.fml.ModList.get().isLoaded(entry.requiredMod)) {
                validEntries.add(entry);
            }
        }

        // 如果没有可用的物品，直接返回
        if (validEntries.isEmpty()) return;

        // 2. 从合法的物品池中随机抽取
        RandomSource random = level.random;
        LootEntry selected = validEntries.get(random.nextInt(validEntries.size()));

        // 3. 生成物品
        spawnItem(level, pos, selected.baseItem, selected.nbtString, selected.count);
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
