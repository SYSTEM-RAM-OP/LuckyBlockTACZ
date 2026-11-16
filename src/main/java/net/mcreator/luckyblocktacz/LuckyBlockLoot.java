
package net.mcreator.luckyblocktacz;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.RandomSource;

public class LuckyBlockLoot {
    public static void dropRandomGunAndAmmo(Level level, BlockPos pos) {
        if (level.isClientSide()) return;

        RandomSource random = level.random;
        int choice = random.nextInt(86);

        switch (choice) {

            case 0:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:aa12\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 1:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ai_awp\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:338\"}", 60);
                break;

            case 2:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak12\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60);
                break;

            case 3:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak47\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60);
                break;

            case 4:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ak74m\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60);
                break;

            case 5:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:aug\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 6:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:b93r\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 7:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:cz75\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 8:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:db_long\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 9:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:db_short\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 10:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:deagle\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:50ae\"}", 60);
                break;

            case 11:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:deagle_golden\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:357mag\"}", 60);
                break;

            case 12:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:fn_evolys\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 13:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:fn_fal\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 14:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:g36k\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 15:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:glock_17\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 16:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hcar\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60);
                break;

            case 17:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk416d\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 18:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_g3\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 19:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mk23\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 20:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mp5a5\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 21:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hk_mp7a1\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:46x30\"}", 60);
                break;

            case 22:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:hm50b\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60);
                break;

            case 23:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:kar98\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:792x57\"}", 60);
                break;

            case 24:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:lonetrail\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60);
                break;

            case 25:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1014\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 26:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m107\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60);
                break;

            case 27:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m16a1\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 28:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m16a4\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 29:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1866\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 30:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1897\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 31:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m1911\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 32:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m249\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 33:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m320\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:40mm\"}", 60);
                break;

            case 34:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m4a1\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 35:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m700\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:30_06\"}", 60);
                break;

            case 36:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m870\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 37:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m95\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:50bmg\"}", 60);
                break;

            case 38:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:m9a4\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 39:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mcx_spear_lt\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:300blk\"}", 60);
                break;

            case 40:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:minigun\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 41:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mk14\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 42:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mk18\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 43:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:mrad\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:338\"}", 60);
                break;

            case 44:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:p320\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 45:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:p90\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:57x28\"}", 60);
                break;

            case 46:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:pkm\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x54\"}", 60);
                break;

            case 47:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qbz_191\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:58x42\"}", 60);
                break;

            case 48:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qbz_95\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:58x42\"}", 60);
                break;

            case 49:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:qr_24\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:545x39\"}", 60);
                break;

            case 50:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rhino357\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:357mag\"}", 60);
                break;

            case 51:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rpg7\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:rpg_rocket\"}", 60);
                break;

            case 52:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:rpk\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60);
                break;

            case 53:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_h\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 54:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_l\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 55:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:scar_mk20\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:308\"}", 60);
                break;

            case 56:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:sks_tactical\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60);
                break;

            case 57:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:spas_12\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:12g\"}", 60);
                break;

            case 58:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:spr15hb\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 59:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:springfield1873\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45_70\"}", 60);
                break;

            case 60:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:svd\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x54\"}", 60);
                break;

            case 61:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:taurus500\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:500mag\"}", 60);
                break;

            case 62:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:taurus943\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:22wmr\"}", 60);
                break;

            case 63:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:tec_9\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 64:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:timeless50\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:50ae\"}", 60);
                break;

            case 65:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:type_81\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:762x39\"}", 60);
                break;

            case 66:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:ump45\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 67:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:urgi\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:556x45\"}", 60);
                break;

            case 68:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:uzi\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:9mm\"}", 60);
                break;

            case 69:
                spawnItem(level, pos, "tacz:modern_kinetic_gun", "{\"GunId\":\"tacz:vector45\"}", 1);
                spawnItem(level, pos, "tacz:ammo", "{\"AmmoId\":\"tacz:45acp\"}", 60);
                break;

            case 70:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:annihil\"}", 1);
                break;

            case 71:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:cobra_vanguard\"}", 1);
                break;

            case 72:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:executor\"}", 1);
                break;

            case 73:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:mercy\"}", 1);
                break;

            case 74:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:null_annihil\"}", 1);
                break;

            case 75:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:polaris\"}", 1);
                break;

            case 76:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:quenching\"}", 1);
                break;

            case 77:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:shadowkiller\"}", 1);
                break;

            case 78:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:silencefd\"}", 1);
                break;

            case 79:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:thieve_sea\"}", 1);
                break;

            case 80:
                spawnItem(level, pos, "lrtactical:melee", "{\"MeleeWeaponId\":\"delta_wt:treasure_kn\"}", 1);
                break;

            case 81:
                spawnItem(level, pos, "lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:flash_grenade\"}", 1);
                break;

            case 82:
                spawnItem(level, pos, "lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:m67\"}", 1);
                break;

            case 83:
                spawnItem(level, pos, "lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:molotov\"}", 1);
                break;

            case 84:
                spawnItem(level, pos, "lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:rgn\"}", 1);
                break;

            case 85:
                spawnItem(level, pos, "lrtactical:throwable", "{\"ThrowableId\":\"lrtactical:smoke_grenade\"}", 1);
                break;

            default:
                break;
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
