package fr.fartunruss.additionalmod.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;

public class WandItem extends SwordItem implements IAnimatable {
    public WandItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("text.additionalmod.wanddesc"));
    }

    public boolean idle=true;
    public void Fly(PlayerEntity player, double yadd, boolean levitateOnPoint) {
        Vector3d motion = player.getMotion();
        if (!levitateOnPoint) {
            player.setMotion(
                    motion.getCoordinate(Direction.Axis.X),
                    yadd,
                    motion.getCoordinate(Direction.Axis.Z)
            );
        } else {
            player.setMotion(
                    motion.getCoordinate(Direction.Axis.X),
                    0,
                    motion.getCoordinate(Direction.Axis.Z)
            );

        }


        player.fallDistance = 0.0F;
        if (idle)
            idle=false;
    }
    //GeckoLib animation

    public AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event){
        if(idle)
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magical_wand.idle", true));
        else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magical_wand.flying", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
