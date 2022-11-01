package draylar.wishingwell.mixin;

import draylar.wishingwell.WishingWell;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DesertWellFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DesertWellFeature.class)
public class DesertWellFeatureMixin {

    @Inject(
            method = "generate",
            at = @At("RETURN")
    )
    private void addWishingAir(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        context.getWorld().setBlockState(context.getOrigin().up(), WishingWell.WISHING_AIR.getDefaultState(), 3);
    }
}
