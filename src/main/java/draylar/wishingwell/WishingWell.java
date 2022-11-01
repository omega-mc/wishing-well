package draylar.wishingwell;

import draylar.omegaconfig.OmegaConfig;
import draylar.wishingwell.config.WishingWellConfig;
import draylar.wishingwell.content.CloverItem;
import draylar.wishingwell.content.WishingAirBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WishingWell implements ModInitializer {

    public static final WishingWellConfig CONFIG = OmegaConfig.register(WishingWellConfig.class);
    public static final Item GOLD_LEAF_CLOVER = Registry.register(Registry.ITEM, new Identifier("wishingwell", "golden_leaf_clover"), new CloverItem(new Item.Settings().maxCount(1)));
    public static final Item CLOVER_BLADE = Registry.register(Registry.ITEM, new Identifier("wishingwell", "clover_blade"), new SwordItem(ToolMaterials.GOLD, 1, 4.0f, new Item.Settings().maxCount(1)));
    public static final Block WISHING_AIR = Registry.register(Registry.BLOCK, new Identifier("wishingwell", "wishing_air"), new WishingAirBlock());
    public static final TagKey<Item> WISHING_WELL_PAYMENT = registerTag("wishing_well_payment");

    @Override
    public void onInitialize() {

    }

    private static TagKey<Item> registerTag(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier("wishingwell", id));
    }
}
