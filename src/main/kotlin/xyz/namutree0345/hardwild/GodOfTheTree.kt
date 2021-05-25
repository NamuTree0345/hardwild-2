package xyz.namutree0345.hardwild

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.EntityType
import org.bukkit.entity.IronGolem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class GodOfTheTree : Listener {

    @EventHandler
    fun breakT(event: BlockBreakEvent) {
        if(event.block.type.toString().contains("LOG") || event.block.type.toString().contains("SAPLING") || event.block.type.toString().contains("PLANKS")) {
            val golem = event.block.world.spawnEntity(event.block.location, EntityType.IRON_GOLEM) as IronGolem
            golem.target = event.player
            event.player.sendMessage(
                Component.text(
                    "당신은 나무를 부숴서 나무의 신이 나타났습니다.",
                    NamedTextColor.DARK_RED,
                    TextDecoration.BOLD
                )
            )
        }
    }


}