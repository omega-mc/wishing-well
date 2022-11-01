package draylar.wishingwell.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class WishingWellConfig implements Config {

    @Comment(value = "Whether Wishing Wells are permanently disabled after being used once.")
    public boolean disableAfterUse = true;

    @Comment(value = "The count decremented from a valid stack of items thrown into an unactivated Wishing Well.")
    public int paymentPerActivation = 1;

    @Override
    public String getName() {
        return "wishingwell";
    }
}
