package ykkz000.mcmod.backpack.mixin;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import ykkz000.mcmod.backpack.world.PackableContainer;

@Mixin(SimpleContainer.class)
public abstract class SimpleContainerMixin implements PackableContainer {
    @Final
    @Shadow
    private int size;

    @Shadow
    protected abstract void moveItemsBetweenStacks(final ItemStack sourceStack, final ItemStack targetStack);

    @Shadow
    public abstract ItemStack getItem(final int slot);

    @Override
    public void ykkz000_sBackpack$pack() {
        for (int i = 1; i < this.size; i++) {
            ItemStack sourceStack = this.getItem(i);
            for (int j = 0; j < i; j++) {
                ItemStack targetStack = this.getItem(j);
                if (targetStack.isEmpty() || ItemStack.isSameItemSameComponents(targetStack, sourceStack)) {
                    this.moveItemsBetweenStacks(sourceStack, targetStack);
                    if (sourceStack.isEmpty()) {
                        break;
                    }
                }
            }
        }
    }
}
