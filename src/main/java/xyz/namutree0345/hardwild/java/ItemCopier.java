package xyz.namutree0345.hardwild.java;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Nameable;
import org.bukkit.block.EnchantingTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ItemCopier implements Listener {

    private ItemStack getItemStack(Material material, String name) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(Component.text(name));
        stack.setItemMeta(meta);
        return stack;
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        if(PlainComponentSerializer.plain().serialize(event.getView().title()).contains("사람이 많을수록 더 복사가 되는 아이템 복사기 (x")) {
            if(event.getCurrentItem() != null) {
                if(PlainComponentSerializer.plain().serialize(Objects.requireNonNull(event.getCurrentItem().getItemMeta().displayName())).equals(" "))
                    event.setCancelled(true);
                else if(PlainComponentSerializer.plain().serialize(Objects.requireNonNull(event.getCurrentItem().getItemMeta().displayName())).equals("여기를 클릭해 아이템을 복사하세요!")) {
                    if(event.getView().getItem(23) != null && Objects.requireNonNull(event.getView().getItem(22)).getType() != Material.AIR) {
                        ItemStack is = event.getView().getItem(22);
                        int goppagui = Bukkit.getOnlinePlayers().size() == 1 ? 2 : Bukkit.getOnlinePlayers().size();
                        assert is != null;
                        is.setAmount((is.getAmount() * goppagui != 64 ? is.getAmount() * goppagui : 64));

                        event.setCancelled(true);
                        event.getView().setItem(22, is);
                        event.getView().getPlayer().sendMessage("복사를 완료했습니다.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onOpen(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.ENCHANTING_TABLE && event.getClickedBlock() != null) {
                if(Objects.equals(((EnchantingTable) event.getClickedBlock().getState()).getCustomName(), ChatColor.YELLOW + "" + ChatColor.BOLD + "아이템 복사기")) {
                    event.setCancelled(true);
                    Inventory i = Bukkit.createInventory(null, 27, Component.text("사람이 많을수록 더 복사가 되는 아이템 복사기 (x" + (Bukkit.getOnlinePlayers().size() == 1 ? 2 : Bukkit.getOnlinePlayers().size()) + ")", NamedTextColor.AQUA, TextDecoration.BOLD));
                    i.setItem(0, getItemStack(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(1, getItemStack(Material.GREEN_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(2, getItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(3, getItemStack(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(4, getItemStack(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(5, getItemStack(Material.GREEN_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(6, getItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(7, getItemStack(Material.YELLOW_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(8, getItemStack(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + " "));

                    for(int ia = 9; ia < 22; ia++) {
                        i.setItem(ia, getItemStack(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    }

                    i.setItem(24, getItemStack(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(25, getItemStack(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RED + " "));
                    i.setItem(26, getItemStack(Material.GRAY_STAINED_GLASS_PANE, ChatColor.RED + " "));


                    i.setItem(23, getItemStack(Material.BLUE_STAINED_GLASS_PANE, ChatColor.GREEN + "여기를 클릭해 아이템을 복사하세요!"));

                    event.getPlayer().openInventory(i);
                }
            }
        }
    }
