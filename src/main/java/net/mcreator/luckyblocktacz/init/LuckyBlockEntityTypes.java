package net.mcreator.luckyblocktacz.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.mcreator.luckyblocktacz.block.entity.LuckyBlockEntity;
import net.mcreator.luckyblocktacz.LuckyblocktaczMod;

public class LuckyBlockEntityTypes {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LuckyblocktaczMod.MODID);
	public static final RegistryObject<BlockEntityType<LuckyBlockEntity>> LUCKY_BLOCK = REGISTRY.register("lucky_block_tacz",
			() -> BlockEntityType.Builder.of(LuckyBlockEntity::new, LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ.get()).build(null));
}
