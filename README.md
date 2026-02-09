# Ykkz000's Backpack
English|[简体中文](README.zh_cn.md)
## Features
Add a **9×3 backpack** that **will not drop** (upon death or similar events).  
Press the **B** key to open the backpack.  
Press the **C** key to pack the backpack.
## Interfaces
- Inject the `ykkz000.mcmod.backpack.world.entity.player.BackpackPlayer` interface into the `net.minecraft.world.entity.player.Player` class, enabling mods that depend on this mod to directly access the backpack.
- Inject the `ykkz000.mcmod.backpack.world.PackableContainer` interface into the `net.minecraft.world.SimpleContainer` class, enabling mods that depend on this mod to organize the inventory for `SimpleContainer` and its subclasses.
