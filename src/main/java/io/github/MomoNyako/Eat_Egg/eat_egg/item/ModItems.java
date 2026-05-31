package io.github.MomoNyako.Eat_Egg.eat_egg.item;

import io.github.MomoNyako.Eat_Egg.eat_egg.Eat_egg;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item BACON_EGGS = registerItem("bacon_eggs",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .hunger(8).saturationModifier(0.8f).build())));

    public static final Item COOKED_EGG = registerItem("cooked_egg",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .hunger(3).saturationModifier(0.4f).build())));

    public static final Item FULL_ENG_BREAKFAST = registerItem("full_eng_breakfast",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .hunger(14).saturationModifier(1.0f).build())));

    public static final Item OMELETTE = registerItem("omelette",
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .hunger(6).saturationModifier(0.7f).build())));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier(Eat_egg.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(BACON_EGGS);
            entries.add(COOKED_EGG);
            entries.add(FULL_ENG_BREAKFAST);
            entries.add(OMELETTE);
        });
    }
}
