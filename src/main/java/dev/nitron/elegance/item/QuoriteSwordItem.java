package dev.nitron.elegance.item;

import com.nitron.nitrogen.util.ColorableItem;
import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.registration.ModComponentTypes;
import dev.nitron.elegance.registration.ModEnchantments;
import dev.nitron.elegance.registration.ModParticles;
import dev.nitron.elegance.util.EnchantmentHelper;
import dev.nitron.elegance.util.models.ModModelLoaders;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class QuoriteSwordItem extends SwordItem implements ColorableItem {
    public QuoriteSwordItem(Settings settings) {
        super(ToolMaterials.DIAMOND, settings
                .component(ModComponentTypes.WEAPON_MODELS_COMPONENT, ModModelLoaders.QUORITE_SWORD));
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)7.5), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)-2.6, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    @Override
    public Text getName(ItemStack stack) {
        return Elegance.withName(super.getName(stack), 0xff767e);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xffff767e;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xff7c052a;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xf01d0010;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getWorld() instanceof ServerWorld world){
            world.spawnParticles(ModParticles.ROSE_SPARK, target.getX(), target.getY() + (target.getHeight() / 2), target.getZ()
            , (int) (5 * target.getHeight()), 0.5 * target.getHeight(), 0.5 * target.getHeight(), 0.5 * target.getHeight(), 0);
        }
        return super.postHit(stack, target, attacker);
    }
}
