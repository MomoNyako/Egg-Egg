package io.github.MomoNyako.Eat_Egg.eat_egg.fabric;

import io.github.MomoNyako.Eat_Egg.eat_egg.EatEgg;
import io.github.MomoNyako.Eat_Egg.eat_egg.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class EatEggFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        EatEgg.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(ModItems.BACON_EGGS);
            entries.accept(ModItems.COOKED_EGG);
            entries.accept(ModItems.FULL_ENG_BREAKFAST);
            entries.accept(ModItems.OMELETTE);
        });
    }
}
