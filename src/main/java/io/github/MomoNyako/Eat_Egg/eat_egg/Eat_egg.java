package io.github.MomoNyako.Eat_Egg.eat_egg;

import io.github.MomoNyako.Eat_Egg.eat_egg.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class Eat_egg implements ModInitializer {

    public static final String MOD_ID = "eat_egg";

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }
}
