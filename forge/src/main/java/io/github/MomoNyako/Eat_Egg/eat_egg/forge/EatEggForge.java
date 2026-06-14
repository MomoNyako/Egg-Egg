package io.github.MomoNyako.Eat_Egg.eat_egg.forge;

import io.github.MomoNyako.Eat_Egg.eat_egg.EatEgg;
import io.github.MomoNyako.Eat_Egg.eat_egg.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(EatEgg.MOD_ID)
public class EatEggForge {

    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EatEgg.MOD_ID);

    public static final RegistryObject<Item> BACON_EGGS = ITEMS.register("bacon_eggs", ModItems::baconEggs);
    public static final RegistryObject<Item> COOKED_EGG = ITEMS.register("cooked_egg", ModItems::cookedEgg);
    public static final RegistryObject<Item> FULL_ENG_BREAKFAST = ITEMS.register("full_eng_breakfast", ModItems::fullEngBreakfast);
    public static final RegistryObject<Item> OMELETTE = ITEMS.register("omelette", ModItems::omelette);

    public EatEggForge() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCreativeTab);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(COOKED_EGG.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(BACON_EGGS.get(), 0.5f);
            ComposterBlock.COMPOSTABLES.put(OMELETTE.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(FULL_ENG_BREAKFAST.get(), 0.8f);
        });
    }

    private void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(BACON_EGGS.get());
            event.accept(COOKED_EGG.get());
            event.accept(FULL_ENG_BREAKFAST.get());
            event.accept(OMELETTE.get());
        }
    }
}
