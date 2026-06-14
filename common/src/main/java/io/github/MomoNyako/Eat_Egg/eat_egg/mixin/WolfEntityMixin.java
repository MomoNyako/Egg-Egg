package io.github.MomoNyako.Eat_Egg.eat_egg.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Wolf.class)
public class WolfEntityMixin {

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void onInteractMob(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Wolf wolf = (Wolf) (Object) this;
        ItemStack itemStack = player.getItemInHand(hand);

        ResourceLocation id = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        if (!"eat_egg".equals(id.getNamespace())) {
            return;
        }

        if (!wolf.isTame()) {
            return;
        }

        FoodProperties food = itemStack.get(DataComponents.FOOD);
        if (food == null) {
            return;
        }

        if (wolf.getHealth() < wolf.getMaxHealth()) {
            if (!wolf.level().isClientSide) {
                wolf.heal(food.saturation());
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }
            cir.setReturnValue(InteractionResult.sidedSuccess(wolf.level().isClientSide));
        } else if (wolf.isBaby()) {
            if (!wolf.level().isClientSide) {
                int age = wolf.getAge();
                int ageUp = (int) ((float) (-age / 20) * 5.0);
                wolf.setAge(age + ageUp);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }
            cir.setReturnValue(InteractionResult.sidedSuccess(wolf.level().isClientSide));
        } else if (!wolf.isInLove()) {
            if (!wolf.level().isClientSide) {
                wolf.setInLove(player);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }
            cir.setReturnValue(InteractionResult.sidedSuccess(wolf.level().isClientSide));
        }
    }
}
