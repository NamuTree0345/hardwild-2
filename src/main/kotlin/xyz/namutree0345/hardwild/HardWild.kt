package xyz.namutree0345.hardwild

import net.minecraft.server.v1_16_R3.EntityAnimal
import net.minecraft.server.v1_16_R3.EntityTypes
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.entity.Mule
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
        server.pluginManager.registerEvents(CustomEntitySpawn(), this)
        server.pluginManager.registerEvents(Salhae(), this)
        server.pluginManager.registerEvents(GodOfTheTree(), this)
        server.pluginManager.registerEvents(DongjokNoBreak(), this)
        server.pluginManager.registerEvents(ShareInventory(), this)
        server.pluginManager.registerEvents(ItemCopier(), this)
    }

}