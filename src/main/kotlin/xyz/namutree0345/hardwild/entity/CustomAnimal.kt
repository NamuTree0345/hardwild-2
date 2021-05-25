package xyz.namutree0345.hardwild.entity

import net.minecraft.server.v1_16_R3.*
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity
import org.bukkit.entity.Entity
import java.lang.reflect.Field


class CustomAnimal(private val type: EntityTypes<out EntityAnimal>, loc: Location) : EntityAnimal(type, (loc.world as CraftWorld).handle) {


    companion object {
        private var attributeField: Field? = null


        init {
            try {
                attributeField = net.minecraft.server.v1_16_R3.AttributeMapBase::class.java.getDeclaredField("b")
                attributeField!!.isAccessible = true
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            }
        }

        @Throws(IllegalAccessException::class)
        fun registerGenericAttribute(entity: Entity, attribute: Attribute?) {
            val attributeMapBase: AttributeMapBase = (entity as CraftLivingEntity).getHandle().getAttributeMap()
            val map = attributeField!![attributeMapBase] as MutableMap<AttributeBase, AttributeModifiable>
            val attributeBase: AttributeBase =
                org.bukkit.craftbukkit.v1_16_R3.attribute.CraftAttributeMap.toMinecraft(attribute)
            val attributeModifiable =
                AttributeModifiable(attributeBase, net.minecraft.server.v1_16_R3.AttributeModifiable::getAttribute)
            map[attributeBase] = attributeModifiable
        }
    }

    init {
        try {
            registerGenericAttribute(bukkitEntity, Attribute.GENERIC_ATTACK_DAMAGE)
            registerGenericAttribute(bukkitEntity, Attribute.GENERIC_FOLLOW_RANGE)
        } catch (e: IllegalAccessException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        setPosition(loc.x, loc.y, loc.z) // Sets the location of the CustomPig when we spawn it
        attributeMap.b().add(AttributeModifiable(
            GenericAttributes.ATTACK_DAMAGE
        ) { a: AttributeModifiable -> a.value = 20.0 })
        attributeMap.b().add(AttributeModifiable(
            GenericAttributes.FOLLOW_RANGE
        ) { a: AttributeModifiable -> a.value = 16.0 })
        this.health = 20.0f // Sets Health
    }

    override fun createChild(p0: WorldServer?, p1: EntityAgeable?): EntityAgeable? {
        return type.a(p0)
    }

    override fun initPathfinder() {
        // These are the two attributes we need. We can actually add any attribute we want like this.
        //  current attributes            add an attribute       the attribute to add            |lambda|        attribute value(acts very weird)
        // These are the two attributes we need. We can actually add any attribute we want like this.
        //  current attributes            add an attribute       the attribute to add            |lambda|        attribute value(acts very weird)

        goalSelector.a(0, PathfinderGoalMeleeAttack(this, 1.0, false))


        targetSelector.a(2, PathfinderGoalHurtByTarget(this, *arrayOfNulls(0)))
        goalSelector.a(0, PathfinderGoalFloat(this))
        goalSelector.a(2, PathfinderGoalLookAtPlayer(this, EntityHuman::class.java, 8.0f))
    }

}