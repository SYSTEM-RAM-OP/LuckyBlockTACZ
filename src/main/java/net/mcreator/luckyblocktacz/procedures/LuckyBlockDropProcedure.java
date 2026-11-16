package net.mcreator.luckyblocktacz.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LuckyBlockDropProcedure {
    
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof Level level) {
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(event.getState().getBlock());
            if (blockId != null && blockId.toString().equals("luckyblocktacz:lucky_block_tacz")) {
                net.mcreator.luckyblocktacz.LuckyBlockLoot.dropRandomGunAndAmmo(level, event.getPos());
            }
        }
    }

    public static void execute() {
    }
}