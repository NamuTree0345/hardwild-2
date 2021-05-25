package xyz.namutree0345.hardwild

import org.bukkit.entity.Animals
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Salhae : Listener {

    @EventHandler
    fun salhae(event: EntityDamageByEntityEvent) {
        if(event.damager is Player) {
            event.damage /= 2
        }
        if(event.entityType == EntityType.PLAYER) {
            event.damage *= 5
        }
    }

}