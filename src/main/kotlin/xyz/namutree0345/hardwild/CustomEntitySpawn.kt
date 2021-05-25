package xyz.namutree0345.hardwild

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.inventory.ItemStack
import xyz.namutree0345.hardwild.entity.CustomAnimal
import kotlin.random.Random

class CustomEntitySpawn : Listener {

    @EventHandler
    fun spawn(event: EntitySpawnEvent) {
        when(event.entityType) {
            EntityType.CREEPER -> (event.entity as Creeper).explosionRadius = 30
            EntityType.SKELETON -> (event.entity as Skeleton).equipment?.setItemInMainHand(ItemStack(Material.BOW).let {
                it.itemMeta = it.itemMeta.let { meta ->
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true)
                    meta.addEnchant(Enchantment.KNOCKBACK, 2, true)
                    meta.addEnchant(Enchantment.ARROW_FIRE, 1, true)
                    meta
                }
                it
            })
            EntityType.WITHER_SKELETON -> (event.entity as Skeleton).equipment?.setItemInMainHand(ItemStack(Material.BOW).let {
                it.itemMeta = it.itemMeta.let { meta ->
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true)
                    meta.addEnchant(Enchantment.KNOCKBACK, 2, true)
                    meta
                }
                it
            })
        }
        if(event.entity is Animals) {
            if(HardWild.animalNmsMap.containsKey(event.entityType)) {
                val world = (event.entity.world as CraftWorld).handle
                val custom = CustomAnimal(HardWild.animalNmsMap[event.entityType]!!, event.entity.location)
                val entity = event.entity as Animals
                world.addEntity(custom)
                val customBukkit = Bukkit.getEntity(custom.uniqueID)!! as Animals
                customBukkit.breedCause = entity.breedCause
                customBukkit.loveModeTicks = entity.loveModeTicks
                customBukkit.age = entity.age
                customBukkit.ageLock = entity.ageLock
                customBukkit.canPickupItems = entity.canPickupItems
                if(entity is Tameable && customBukkit is Tameable) {
                    (customBukkit as Tameable).isTamed = entity.isTamed
                    customBukkit.owner = entity.owner
                }
                entity.remove()
            }
        }
        if(event.entity is LivingEntity) {
            val random = Random.nextInt(1, 100000)
            // 0.001%
            if(random == 1) {
                event.entity.world.spawnEntity(event.entity.location, EntityType.ILLUSIONER)
            }
        }
    }

}