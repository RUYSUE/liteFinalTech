package me.ruysue.misctech;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class groups {
    static ItemGroup lft = new ItemGroup(
            new NamespacedKey(miscTech.ins, "mt_category"),
            new CustomItemStack(Material.END_CRYSTAL, "&4杂项统合"));
}
