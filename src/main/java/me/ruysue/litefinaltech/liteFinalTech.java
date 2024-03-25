package me.ruysue.litefinaltech;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
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
        }
        // 设定物品组ID
        NamespacedKey itemGroupId = new NamespacedKey(this, "addon_category");
        // 设定物品组外观
        ItemStack itemGroupItem = new CustomItemStack(Material.DIAMOND, "&4附属分类");
        // 注册物品组
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);

        // 设定物品外观
        SlimefunItemStack slimefunItem = new SlimefunItemStack("COOL_DIAMOND", Material.DIAMOND, "&4炫酷的钻石", "&c+20% 炫酷");
        // 创建配方
        ItemStack[] recipe = {
                new ItemStack(Material.EMERALD),    null,                               new ItemStack(Material.EMERALD),
                null,                               new ItemStack(Material.DIAMOND),    null,
                new ItemStack(Material.EMERALD),    null,                               new ItemStack(Material.EMERALD)};
        // 设定物品合成属性
        SlimefunItem item = new SlimefunItem(itemGroup, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        // 注册物品
        item.register(this);

        SlimefunItemStack CAKE = new SlimefunItemStack("FIRE_CAKE",Material.CAKE, "火焰蛋糕", "暖洋洋的");
        ItemStack[] recipeCAKE = {null,null,null,null,new ItemStack(Material.CAKE),null,null,null,};
        FireCake cake = new FireCake(itemGroup, CAKE, RecipeType.ENHANCED_CRAFTING_TABLE, recipeCAKE);
        cake.register(this);
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
        /*
         * 你需要返回对你插件的引用。
         * 如果这是你插件的主类，只需要返回 "this" 即可。
         */
        return this;
    }

}

class FireCake extends SlimefunItem {
    public FireCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }
    @Override
    public void preRegister(){
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
    }
    private void onBlockRightClick(PlayerRightClickEvent event){
        if (event.getPlayer().getFoodLevel() > 19) {
            event.cancel();
        }else{
            event.getPlayer().damage(5);
            event.getPlayer().giveExpLevels(5);
        }
    }
}