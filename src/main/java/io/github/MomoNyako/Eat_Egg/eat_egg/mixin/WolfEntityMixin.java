package io.github.MomoNyako.Eat_Egg.eat_egg.mixin;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void onInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        WolfEntity wolf = (WolfEntity) (Object) this;
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        Identifier id = Registries.ITEM.getId(item);
        if (!"eat_egg".equals(id.getNamespace())) {
            return;
        }

        if (!wolf.isTamed()) {
            return;
        }

        FoodComponent food = item.getFoodComponent();
        if (food == null) {
            return;
        }

        if (wolf.getHealth() < wolf.getMaxHealth()) {
            if (!wolf.getWorld().isClient) {
                wolf.heal(food.getHunger());
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
            cir.setReturnValue(ActionResult.success(wolf.getWorld().isClient));
        } else if (wolf.isBaby()) {
            if (!wolf.getWorld().isClient) {
                int age = wolf.getBreedingAge();
                int ageUp = (int) ((float) (-age / 20) * 5.0);
                wolf.setBreedingAge(age + ageUp);
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
            cir.setReturnValue(ActionResult.success(wolf.getWorld().isClient));
        } else if (!wolf.isInLove()) {
            if (!wolf.getWorld().isClient) {
                wolf.lovePlayer(player);
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
            cir.setReturnValue(ActionResult.success(wolf.getWorld().isClient));
        }
    }
}
