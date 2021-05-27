package xyz.namutree0345.hardwild.java;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShareInventory implements Listener {

    public YamlConfiguration yamlConfiguration;
    public File file;

    public ShareInventory() throws IOException {
        file = new File("plugins\\hard_wild\\sharedInventory.yml");
        if(!file.exists())
            file.createNewFile();
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @EventHandler
    public void interactInv(InventoryCloseEvent event) throws IOException {
        if(PlainComponentSerializer.plain().serialize(event.getView().title()).contains("공유상자")) {
            yamlConfiguration.set("items", event.getInventory().getContents());
            yamlConfiguration.save(file);
        }
    }

    @EventHandler
    public void onInteract(PlayerSwapHandItemsEvent event) throws IOException, InvalidConfigurationException {
        if(event.getPlayer().isSneaking()) {
            event.setCancelled(true);
            Inventory RewardEq = Bukkit.createInventory(
                    null, 54, Component.text("공유상자 - 당신의 Y 좌표: " + ChatColor.GOLD + event.getPlayer().getLocation().getBlockY(), NamedTextColor.AQUA, TextDecoration.BOLD));
            yamlConfiguration.load(file);
            List<?> Items = yamlConfiguration.getList("items");
            System.out.println(Items);
            if(Items != null) {
                for (int i = 0; i < Items.size(); i++) {
                    ItemStack Item = (ItemStack) Items.get(i);
                    if (Item == null) {
                        continue;
                    }
                    RewardEq.addItem(Item);
                }
            }
            event.getPlayer().openInventory(RewardEq);
        }
    }

}
