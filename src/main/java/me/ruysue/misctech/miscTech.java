package me.ruysue.misctech;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;

public class miscTech extends JavaPlugin implements SlimefunAddon {
    public static miscTech ins;
    @Override
    public void onEnable() {
        ins = this;
        items.register();
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