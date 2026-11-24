package net.mcreator.luckyblocktacz.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.mcreator.luckyblocktacz.init.LuckyBlockEntityTypes;
import net.mcreator.luckyblocktacz.init.LuckyblocktaczModBlocks;

public class LuckyBlockEntity extends BlockEntity {
	private ItemStack renderStack = ItemStack.EMPTY;

	public LuckyBlockEntity(BlockPos pos, BlockState state) {
		super(LuckyBlockEntityTypes.LUCKY_BLOCK.get(), pos, state);
	}

	public ItemStack getRenderStack() {
		if (renderStack.isEmpty()) {
			renderStack = new ItemStack(LuckyblocktaczModBlocks.LUCKY_BLOCK_TACZ.get().asItem());
		}
		return renderStack;
	}
}
