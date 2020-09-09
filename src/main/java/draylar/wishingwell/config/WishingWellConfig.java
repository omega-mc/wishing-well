package draylar.wishingwell.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "wishingwell")
public class WishingWellConfig implements ConfigData {

    @Comment(value = "Whether Wishing Wells are permanently disabled after being used once.")
    public boolean disableAfterUse = true;

    @Comment(value = "The count decremented from a valid stack of items thrown into an unactivated Wishing Well.")
    public int paymentPerActivation = 1;
}
