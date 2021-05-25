package xyz.namutree0345.hardwild

import net.minecraft.server.v1_16_R3.EntityAnimal
import net.minecraft.server.v1_16_R3.EntityTypes
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.entity.Mule
import org.bukkit.plugin.java.JavaPlugin

class HardWild : JavaPlugin() {

    companion object {
        val animalNmsMap = HashMap<EntityType, EntityTypes<out EntityAnimal>>()

        init {
            animalNmsMap[EntityType.BEE] = EntityTypes.BEE
            animalNmsMap[EntityType.CHICKEN] = EntityTypes.CHICKEN
            animalNmsMap[EntityType.COW] = EntityTypes.COW
            animalNmsMap[EntityType.PIG] = EntityTypes.PIG
            animalNmsMap[EntityType.FOX] = EntityTypes.FOX
            animalNmsMap[EntityType.CAT] = EntityTypes.CAT
            animalNmsMap[EntityType.PARROT] = EntityTypes.PARROT
            animalNmsMap[EntityType.WOLF] = EntityTypes.WOLF
            animalNmsMap[EntityType.OCELOT] = EntityTypes.OCELOT
            animalNmsMap[EntityType.PANDA] = EntityTypes.PANDA
            animalNmsMap[EntityType.POLAR_BEAR] = EntityTypes.POLAR_BEAR
            animalNmsMap[EntityType.RABBIT] = EntityTypes.RABBIT
            animalNmsMap[EntityType.SHEEP] = EntityTypes.SHEEP
            animalNmsMap[EntityType.TURTLE] = EntityTypes.TURTLE
            animalNmsMap[EntityType.HORSE] = EntityTypes.HORSE
            animalNmsMap[EntityType.SKELETON_HORSE] = EntityTypes.SKELETON_HORSE
            animalNmsMap[EntityType.ZOMBIE_HORSE] = EntityTypes.ZOMBIE_HORSE
            animalNmsMap[EntityType.MULE] = EntityTypes.MULE
            animalNmsMap[EntityType.DONKEY] = EntityTypes.DONKEY
            animalNmsMap[EntityType.LLAMA] = EntityTypes.LLAMA
        }
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(CustomEntitySpawn(), this)
        server.pluginManager.registerEvents(Salhae(), this)
        server.pluginManager.registerEvents(GodOfTheTree(), this)
    }

}