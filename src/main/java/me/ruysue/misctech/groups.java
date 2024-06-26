package me.ruysue.misctech;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class groups {
    static NestedItemGroup mt = new NestedItemGroup(
            new NamespacedKey(miscTech.ins, "mt_main"),
            new CustomItemStack(Material.END_CRYSTAL, "&k&9杂项统合")
    );
    static SubItemGroup mt_weapon = new SubItemGroup(
            new NamespacedKey(miscTech.ins, "mt_weapon"),
            mt,
            new CustomItemStack(Material.NETHERITE_SWORD, "&kl&b装备")
    );
    static SubItemGroup mt_misc = new SubItemGroup(
            new NamespacedKey(miscTech.ins, "mt_misc"),
            mt,
            new CustomItemStack(Material.STICK, "&kl&b杂项")
    );
    static SubItemGroup mt_machine = new SubItemGroup(
            new NamespacedKey(miscTech.ins, "mt_machine"),
            mt,
            new CustomItemStack(Material.CAULDRON, "&k&c机器")
    );
}
