package ykkz000.mcmod.backpack.world;

/**
 * This interface is for mixin interface injection so that {@link net.minecraft.world.SimpleContainer} can have a pack method.
 *
 * @author ykkz000
 * @apiNote This interface injection can be used in other mods.
 * @see ykkz000.mcmod.backpack.mixin.SimpleContainerMixin
 */
public interface PackableContainer {
    default void ykkz000_sBackpack$pack() {
        throw new UnsupportedOperationException("This default implementation is for mixin interface injection and must be overridden.");
    }
}
