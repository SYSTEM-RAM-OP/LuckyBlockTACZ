package net.mcreator.luckyblocktacz.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.mcreator.luckyblocktacz.LuckyBlockLoot;
import net.mcreator.luckyblocktacz.advancement.LuckyAdvancements;
import net.mcreator.luckyblocktacz.jackpot.JackpotTracker;

@Mod.EventBusSubscriber
public class LuckyBlockDropProcedure {
    
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof Level level)) {
            return;
        }
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(event.getState().getBlock());
        if (blockId == null || !blockId.toString().equals("luckyblocktacz:lucky_block_tacz")) {
            return;
        }
        ServerPlayer player = event.getPlayer() instanceof ServerPlayer serverPlayer ? serverPlayer : null;
        LuckyBlockLoot.dropRandomGunAndAmmo(level, event.getPos(), player);
        if (player != null) {
            LuckyAdvancements.onLuckyBlockBroken(player);
        }
        if (level instanceof ServerLevel serverLevel) {
            JackpotTracker.onLuckyBlockBroken(serverLevel, event.getPos(), player);
        }
    }

    public static void execute() {
    }
}
