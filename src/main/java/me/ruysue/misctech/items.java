package me.ruysue.misctech;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;


public class items {
    public static void register() {
        InfExpBook.ieb.register(miscTech.ins);
        RottenFlesh.flesh.register(miscTech.ins);
        mythicSword.sword.register(miscTech.ins);
    }
}

class InfExpBook extends SlimefunItem {
    public InfExpBook(ItemGroup itemGroup, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, itemStack, recipeType, recipe);
    }
    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onItemUse);
    }
    private void onItemUse(PlayerRightClickEvent event){
        event.getPlayer().giveExpLevels(3);
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
        event.cancel();
    }

    static SlimefunItemStack stack =
            new SlimefunItemStack("INF_EXP_BOOK",Material.WRITTEN_BOOK, "&b&l无尽知识之书", "每次阅读都能获得新体验");
    static ItemStack[] recipe = {
            new ItemStack(SlimefunItems.GOLD_24K),      new ItemStack(SlimefunItems.MAGICAL_BOOK_COVER),    new ItemStack(SlimefunItems.GOLD_24K),
            new ItemStack(SlimefunItems.MAGIC_LUMP_3),  new ItemStack(Material.BOOK),                       new ItemStack(SlimefunItems.MAGIC_LUMP_3),
            new ItemStack(Material.EXPERIENCE_BOTTLE),  new ItemStack(SlimefunItems.ESSENCE_OF_AFTERLIFE),  new ItemStack(Material.EXPERIENCE_BOTTLE),
    };
    static InfExpBook ieb = new InfExpBook(groups.mt_misc, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
}

class RottenFlesh extends SlimefunItem{
    public RottenFlesh(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    @Override
    public void preRegister(){
        addItemHandler((ItemConsumptionHandler) this::onItemConsumption);
    }
    private void onItemConsumption(PlayerItemConsumeEvent event, Player player, ItemStack item){
        player.damage(3);
        player.giveExpLevels(5);
        player.removePotionEffect(PotionEffectType.HUNGER);
    }
    static SlimefunItemStack stack = new SlimefunItemStack("ROTTEN_FLESH", Material.ROTTEN_FLESH, "&b&l烧焦的腐肉", "你真的要吃？");
    static ItemStack[] recipe = {
            null,new ItemStack(Material.FIRE_CHARGE),   null,
            null,new ItemStack(Material.ROTTEN_FLESH),  null,
            null,new ItemStack(Material.FIRE_CHARGE),   null,
    };
    static RottenFlesh flesh = new RottenFlesh(groups.mt_misc, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
}

class mythicSword extends SlimefunItem{
    public mythicSword(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    public void preRegister(){
        sword.addMeta();
    }
    private void addMeta(){
        ItemMeta meta = this.getItem().getItemMeta();

        meta.setUnbreakable(true);

        meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 55, true);
        meta.addEnchant(Enchantment.SWEEPING_EDGE, 10, true);

        Multimap<Attribute, AttributeModifier> attributeMap = ArrayListMultimap.create();
        attributeMap.put(Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier(UUID.randomUUID(), "移动速度", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeMap.put(Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "攻击伤害", 70047, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeMap.put(Attribute.GENERIC_ARMOR,
                new AttributeModifier(UUID.randomUUID(), "易伤", -20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeMap.put(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier(UUID.randomUUID(), "生命上限", -50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeMap.put(Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(UUID.randomUUID(), "攻击速度", -3.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        attributeMap.put(Attribute.GENERIC_MAX_HEALTH,
                new AttributeModifier(UUID.randomUUID(), "生命上限", 30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
        attributeMap.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE,
                new AttributeModifier(UUID.randomUUID(), "击退抗性", 0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
        meta.setAttributeModifiers(attributeMap);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        this.getItem().setItemMeta(meta);
    }
    static SlimefunItemStack stack = new SlimefunItemStack("MYTHIC_SWORD", Material.WOODEN_SWORD, "&7&l无", "&5为救天下而铸","","&0为了谎言而铸");
    static ItemStack[] recipe = {
            null, null, null,
            null, null, null,
            null, null, null,
    };
    static mythicSword sword = new mythicSword(groups.mt_weapon, stack, RecipeType.NULL, recipe);
}