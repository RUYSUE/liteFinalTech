package me.ruysue.misctech;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class miscTech extends JavaPlugin implements SlimefunAddon {

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
//        FireCake cake = new FireCake(lft, FireCake.stack, RecipeType.ENHANCED_CRAFTING_TABLE, FireCake.recipe);
//        cake.register(this);
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