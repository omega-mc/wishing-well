package draylar.wishingwell;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WishingWell implements ModInitializer {

    public static final Item GOLD_LEAF_CLOVER = Registry.register(Registry.ITEM, new Identifier("wishingwell", "golden_leaf_clover"), new CloverItem(new Item.Settings().maxCount(1)));
    public static final Block WISHING_AIR = Registry.register(Registry.BLOCK, new Identifier("wishingwell", "wishing_air"), new WishingAirBlock());

    @Override
    public void onInitialize() {

    }
}
