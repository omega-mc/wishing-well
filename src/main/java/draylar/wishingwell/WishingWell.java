package draylar.wishingwell;

import draylar.wishingwell.config.WishingWellConfig;
import draylar.wishingwell.content.CloverItem;
import draylar.wishingwell.content.WishingAirBlock;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WishingWell implements ModInitializer {

    public static final WishingWellConfig CONFIG = AutoConfig.register(WishingWellConfig.class, GsonConfigSerializer::new).getConfig();
    public static final Item GOLD_LEAF_CLOVER = Registry.register(Registry.ITEM, new Identifier("wishingwell", "golden_leaf_clover"), new CloverItem(new Item.Settings().maxCount(1)));
    public static final Block WISHING_AIR = Registry.register(Registry.BLOCK, new Identifier("wishingwell", "wishing_air"), new WishingAirBlock());
    public static final Tag<Item> WISHING_WELL_PAYMENT = registerTag("wishing_well_payment");

    @Override
    public void onInitialize() {

    }

    private static Tag<Item> registerTag(String id) {
        return TagRegistry.item(new Identifier("wishingwell", id));
    }
}
