package io.github.MomoNyako.Eat_Egg.eat_egg.item;

import io.github.MomoNyako.Eat_Egg.eat_egg.EatEgg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;

public class ModItems {

    public static Item BACON_EGGS;
    public static Item COOKED_EGG;
    public static Item FULL_ENG_BREAKFAST;
    public static Item OMELETTE;

    public static Item baconEggs() {
        return new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(8).saturationMod(1.6f)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 200), 0.5f)
                .build()));
    }

    public static Item cookedEgg() {
        return new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(5).saturationMod(0.5f).build()));
    }

    public static Item fullEngBreakfast() {
        return new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(10).saturationMod(2.0f)
                .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200), 1.0f)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200), 1.0f)
                .effect(new MobEffectInstance(MobEffects.REGENERATION, 200), 1.0f)
                .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800), 1.0f)
                .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800), 1.0f)
                .build()));
    }

    public static Item omelette() {
        return new Item(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(6).saturationMod(1.26f)
                .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200), 0.4f)
                .build()));
    }

    public static void registerItems() {
        BACON_EGGS = registerItem("bacon_eggs", baconEggs());
        COOKED_EGG = registerItem("cooked_egg", cookedEgg());
        FULL_ENG_BREAKFAST = registerItem("full_eng_breakfast", fullEngBreakfast());
        OMELETTE = registerItem("omelette", omelette());
        registerCompostables();
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EatEgg.MOD_ID, name), item);
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(COOKED_EGG, 0.4f);
        ComposterBlock.COMPOSTABLES.put(BACON_EGGS, 0.5f);
        ComposterBlock.COMPOSTABLES.put(OMELETTE, 0.6f);
        ComposterBlock.COMPOSTABLES.put(FULL_ENG_BREAKFAST, 0.8f);
    }
}
