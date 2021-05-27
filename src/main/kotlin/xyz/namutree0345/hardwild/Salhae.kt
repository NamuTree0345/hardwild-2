package xyz.namutree0345.hardwild

import org.bukkit.entity.Animals
import org.bukkit.entity.EntityType
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class Salhae : Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    fun salhae(event: EntityDamageByEntityEvent) {
        if(event.damager is Player) {
            event.damage /= 2
            if(event.entity is Monster) {
                event.damager.location.getNearbyLivingEntities(50.0).forEach {
                    if(it.type == EntityType.PLAYER) it.world.strikeLightning(it.location)
                }
            }
        }
        if(event.entityType == EntityType.PLAYER) {
            event.damage *= 5
        }
    }

    /*
    @EventHandler
    fun damage(event: EntityDamageEvent) { if(event.entityType == EntityType.PLAYER && (event.cause == EntityDamageEvent.DamageCause.LIGHTNING)) even }
     */

}