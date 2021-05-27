package xyz.namutree0345.hardwild

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minecraft.server.v1_16_R3.EntityAnimal
import net.minecraft.server.v1_16_R3.EntityTypes
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.entity.Mule
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin
import xyz.namutree0345.hardwild.java.ItemCopier
import xyz.namutree0345.hardwild.java.ShareInventory

class HardWild : JavaPlugin() {

    companion object {
        val animalNmsMap = HashMap<EntityType, EntityTypes<out EntityAnimal>>()

        init {
            animalNmsMap[EntityType.BEE] = EntityTypes.BEE
            animalNmsMap[EntityType.CHICKEN] = EntityTypes.CHICKEN
            animalNmsMap[EntityType.COW] = EntityTypes.COW
            animalNmsMap[EntityType.PIG] = EntityTypes.PIG
            animalNmsMap[EntityType.SHEEP] = EntityTypes.SHEEP
        }
    }

    override fun onEnable() {

        val nsKey = NamespacedKey(this, "item_copier")
        val icRecipe = ShapedRecipe(nsKey, ItemStack(Material.ENCHANTING_TABLE).let {
            it.itemMeta = it.itemMeta.let { im ->
                im.displayName(Component.text("아이템 복사기", NamedTextColor.YELLOW, TextDecoration.BOLD))
                im
            }
            it
        }).also {
            it.shape("***", "*$*", "*B*")
            it.setIngredient('*', Material.IRON_INGOT)
            it.setIngredient('$', Material.DIAMOND)
            it.setIngredient('B', Material.OBSIDIAN)
        }
        server.addRecipe(icRecipe)

        server.pluginManager.registerEvents(CustomEntitySpawn(), this)
        server.pluginManager.registerEvents(Salhae(), this)
        server.pluginManager.registerEvents(GodOfTheTree(), this)
        server.pluginManager.registerEvents(DongjokNoBreak(), this)
        server.pluginManager.registerEvents(ShareInventory(), this)
        server.pluginManager.registerEvents(ItemCopier(), this)
    }

}