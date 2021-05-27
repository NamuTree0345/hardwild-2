package xyz.namutree0345.hardwild

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageEvent

class DongjokNoBreak : Listener {

    @EventHandler
    fun breakBlock(event: BlockBreakEvent) {
        if(event.player.inventory.itemInMainHand.type.toString().contains(event.block.type.toString().split("_")[0])) {
            event.isCancelled = true
            event.player.sendMessage(Component.text("동족을 캐지 마세요.", NamedTextColor.DARK_RED))
            event.player.damage(10.0)
        }

        if(event.player.inventory.itemInMainHand.type == Material.AIR && event.block.type != Material.GRASS) {
            Bukkit.getServer().sendMessage(Component.text("누군가 맨손으로 무언갈 부숴서 손을 다쳐서 죽었습니다.", NamedTextColor.RED))
            event.player.damage(20.0)
        }
    }

}