package draylar.wishingwell;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class CloverItem extends Item {

    public CloverItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 5, 0, false, false));
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        return new TranslatableText(this.getTranslationKey(stack)).formatted(Formatting.GOLD);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        // description
        tooltip.add(new TranslatableText("wishingwell.golden_leaf_clover.description").formatted(Formatting.GRAY));
        tooltip.add(new LiteralText(""));

        // luck
        tooltip.add(new TranslatableText("wishingwell.golden_leaf_clover.effect").formatted(Formatting.BLUE));
    }
}
