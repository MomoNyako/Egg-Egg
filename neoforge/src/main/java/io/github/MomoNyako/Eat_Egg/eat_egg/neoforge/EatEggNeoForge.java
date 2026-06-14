package io.github.MomoNyako.Eat_Egg.eat_egg.neoforge;

import io.github.MomoNyako.Eat_Egg.eat_egg.EatEgg;
import net.neoforged.fml.common.Mod;

@Mod(EatEgg.MOD_ID)
public class EatEggNeoForge {

    public EatEggNeoForge() {
        // TODO: User will rewrite NeoForge-specific registration code
        EatEgg.init();
    }
}
