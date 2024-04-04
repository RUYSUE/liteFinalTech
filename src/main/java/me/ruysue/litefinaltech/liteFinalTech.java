package me.ruysue.litefinaltech;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class liteFinalTech extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // 从 config.yml 中读取插件配置
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // 你可以在这里添加自动更新功能
            getLogger().info("已开启自动更新（虽然没用）");
        }
        ItemGroup lft = new ItemGroup(
                new NamespacedKey(this, "lft_category"),
                new CustomItemStack(Material.DIAMOND, "&4微"));
        FireCake cake = new FireCake(lft, FireCake.stack, RecipeType.ENHANCED_CRAFTING_TABLE, FireCake.recipe);
        cake.register(this);
        InfExpBook ieb = new InfExpBook(lft,InfExpBook.stack,RecipeType.ENHANCED_CRAFTING_TABLE,InfExpBook.recipe);
        ieb.register(this);
    }

    @Override
    public void onDisable() {
        // 禁用插件的逻辑...
    }

    @Override
    public String getBugTrackerURL() {
        // 你可以在这里返回你的问题追踪器的网址，而不是 null
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        // 你需要返回对你插件的引用。如果这是你插件的主类，只需要返回 "this" 即可。
        return this;
    }

}

class FireCake extends SlimefunItem {
    public FireCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    @Override
    public void preRegister(){
//        addItemHandler((BlockUseHandler) this::onBlockRightClick);
        addItemHandler((ItemConsumptionHandler) this::onItemConsumption);
    }
//    private void onBlockRightClick(PlayerRightClickEvent event){
//        if (event.getPlayer().getFoodLevel() > 19) {
//            event.cancel();
//        }else{
//            event.getPlayer().damage(5);
//            event.getPlayer().giveExpLevels(5);
//        }
//    }

    private void onItemConsumption(PlayerItemConsumeEvent event, Player player, ItemStack item){
        event.getPlayer().giveExpLevels(5);
    }

    static SlimefunItemStack stack = new SlimefunItemStack("FIRE_CAKE",Material.ROTTEN_FLESH, "&b&l烧焦的腐肉", "你真的要吃？");
    static ItemStack[] recipe = {
            null,new ItemStack(Material.FIRE_CHARGE),   null,
            null,new ItemStack(Material.ROTTEN_FLESH),  null,
            null,new ItemStack(Material.FIRE_CHARGE),   null,
    };
}

class InfExpBook extends SlimefunItem{
    public InfExpBook(ItemGroup itemGroup, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, itemStack, recipeType, recipe);
    }
    @Override
    public void preRegister(){
        addItemHandler((ItemUseHandler) this::onItemUse);
    }
    private void onItemUse(PlayerRightClickEvent event){
        event.getPlayer().giveExpLevels(1);
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
        event.cancel();
    }
    static SlimefunItemStack stack = new SlimefunItemStack("INF_EXP_BOOK",Material.WRITTEN_BOOK, "&b&l无尽知识之书", "每次阅读都能获得新体验");
    static ItemStack[] recipe = {
            new ItemStack(SlimefunItems.GOLD_24K), new ItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE), new ItemStack(SlimefunItems.GOLD_24K),
            new ItemStack(SlimefunItems.MAGIC_LUMP_3), new ItemStack(Material.BOOK), new ItemStack(SlimefunItems.MAGIC_LUMP_3),
            new ItemStack(Material.EXPERIENCE_BOTTLE),new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.EXPERIENCE_BOTTLE),
    };
}