package draylar.wishingwell;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class WishingAirBlock extends Block {

    public WishingAirBlock() {
        super(FabricBlockSettings.of(Material.AIR).nonOpaque().air());
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;

            if(entity instanceof ItemEntity) {
                ItemEntity itemEntity = (ItemEntity) entity;

                // Ensure stack passing through is golden nugget
                if(itemEntity.getStack().getItem().equals(Items.GOLD_NUGGET)) {
                    itemEntity.getStack().decrement(1);

                    // Set chest
//                    world.setBlockState(pos, Blocks.CHEST.getDefaultState());
//                    ((ChestBlockEntity) world.getBlockEntity(pos)).setLootTable(new Identifier("chests/shipwreck_treasure"), world.random.nextInt(1000));
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());

                    // generate loot
                    LootTable table = serverWorld.getServer().getLootManager().getTable(new Identifier("wishingwell", "wishing_well"));
                    List<ItemStack> itemStacks = table.generateLoot(new LootContext.Builder(serverWorld)
                            .parameter(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()))
                            .parameter(LootContextParameters.THIS_ENTITY, entity)
                            .build(LootContextTypes.GIFT));

                    // drop loot
                    itemStacks.forEach(stack -> ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), stack));

                    // Spawn firework particles
                    serverWorld.spawnParticles(ParticleTypes.FIREWORK, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, 50, 0, 0, 0, .25);
                    world.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1, 1);
                    world.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, SoundCategory.BLOCKS, 1, 1);

                    // Grant advancement
                    if(itemEntity.getThrower() != null) {
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) serverWorld.getPlayerByUuid(itemEntity.getThrower());
                        Advancement advancement = serverWorld.getServer().getAdvancementLoader().get(new Identifier("wishingwell", "adventure/wishing_well"));
                        serverPlayer.getAdvancementTracker().grantCriterion(advancement, "used_wishing_well");
                    }

                    return;
                }
            }
        }

        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
