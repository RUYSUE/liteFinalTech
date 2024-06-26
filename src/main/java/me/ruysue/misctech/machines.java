package me.ruysue.misctech;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class machines {
    public static void register(){
        machine.dm.register(miscTech.ins);
    }
}

class machine extends AContainer implements RecipeDisplayItem {
    protected machine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public ItemStack getProgressBar() {return new ItemStack(Material.REDSTONE);}

    @Nonnull
    @Override
    public String getMachineIdentifier() {return "DEFAULT_MAC";}

    @Override
    public int getSpeed() {return 1;}

    @Override
    public int getEnergyConsumption() {return 1;}

    @Override
    public int getCapacity() {return 10;}

    public void registerDefaultRecipes(){
        this.registerRecipe(1, new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND, 5));
        this.registerRecipe(1, new ItemStack(Material.CAKE), new ItemStack(Material.DIAMOND, 3));
    }
    @Override
    public List<ItemStack> getDisplayRecipes(){
        List<ItemStack> displayRecipes = new ArrayList<>();
        displayRecipes.add(new ItemStack(Material.DIAMOND));
        displayRecipes.add(new ItemStack(Material.DIAMOND, 5));
        displayRecipes.add(new ItemStack(Material.CAKE));
        displayRecipes.add(new ItemStack(Material.DIAMOND, 3));
        return displayRecipes;
    }

    static SlimefunItemStack stack = new SlimefunItemStack("MACHINE_D", Material.BEDROCK, "&7&l实例机器");
    static ItemStack[] recipe = {new ItemStack(Material.DIAMOND_SWORD),null,null,new ItemStack(Material.DIAMOND_SWORD),null,null,new ItemStack(Material.DIAMOND_SWORD),null,null};
    static machine dm = new machine(groups.mt_machine, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
}