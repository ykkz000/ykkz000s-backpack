# Ykkz000的背包
[English](README.md)|简体中文
## 特性
添加一个**9×3背包**，该背包**不会掉落**（死亡等情况）。  
按**B**键打开背包。  
按**C**键收起背包。
## 接口
- 对`net.minecraft.world.entity.player.Player`类注入`ykkz000.mcmod.backpack.world.entity.player.BackpackPlayer`接口使依赖于此模组的模组可以直接访问背包。
- 对`net.minecraft.world.SimpleContainer`类注入`ykkz000.mcmod.backpack.world.PackableContainer`接口使依赖于此模组的模组可以对`SimpleContainer`以及其子类进行整理背包。
