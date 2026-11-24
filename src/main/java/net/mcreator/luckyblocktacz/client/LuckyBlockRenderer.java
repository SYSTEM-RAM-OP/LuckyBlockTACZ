package net.mcreator.luckyblocktacz.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.mcreator.luckyblocktacz.block.entity.LuckyBlockEntity;
import net.mcreator.luckyblocktacz.config.LuckyBlockConfig;

public class LuckyBlockRenderer implements BlockEntityRenderer<LuckyBlockEntity> {
	private final ItemRenderer itemRenderer;

	public LuckyBlockRenderer(ItemRenderer itemRenderer) {
		this.itemRenderer = itemRenderer;
	}

	@Override
	public void render(LuckyBlockEntity be, float partialTick, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
		if (!LuckyBlockConfig.boolOf(LuckyBlockConfig.RENDER_ENABLED))
			return;
		ItemStack stack = be.getRenderStack();
		if (stack.isEmpty())
			return;
		Level level = be.getLevel();
		float time = (level != null ? level.getGameTime() : 0L) + partialTick;
		double spin = LuckyBlockConfig.doubleOf(LuckyBlockConfig.RENDER_SPIN);
		double bobAmount = LuckyBlockConfig.doubleOf(LuckyBlockConfig.RENDER_BOB);
		float scale = (float) LuckyBlockConfig.doubleOf(LuckyBlockConfig.RENDER_SCALE);
		float bob = Mth.sin(time * 0.1F) * (float) bobAmount;
		pose.pushPose();
		pose.translate(0.5D, 0.5D + bob, 0.5D);
		pose.mulPose(Axis.YP.rotationDegrees((float) ((time * spin) % 360.0D)));
		pose.scale(scale, scale, scale);
		itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, light, overlay, pose, buffers, level, 0);
		pose.popPose();
	}
}
