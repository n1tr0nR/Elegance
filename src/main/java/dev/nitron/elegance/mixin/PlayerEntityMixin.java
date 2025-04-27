package dev.nitron.elegance.mixin;

import dev.nitron.elegance.Elegance;
import dev.nitron.elegance.registration.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract SoundCategory getSoundCategory();

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Shadow protected abstract float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource);

    @Shadow public abstract void spawnSweepAttackParticles();

    @Shadow public abstract void addCritParticles(Entity target);

    @Shadow public abstract void resetLastAttackedTicks();

    @Shadow public abstract void addEnchantedHitParticles(Entity target);

    @Shadow public abstract void increaseStat(Identifier stat, int amount);

    @Shadow public abstract void addExhaustion(float exhaustion);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void elegance$modifyAttackSound(Entity target, CallbackInfo ci){
        if (this.getMainHandStack().isIn(Elegance.ROSE_QUARTZ_WEAPONRY)){
            if (target.isAttackable()) {
                if (!target.handleAttack(this)) {
                    float f = this.isUsingRiptide() ? this.riptideAttackDamage : (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    ItemStack itemStack = this.getWeaponStack();
                    DamageSource damageSource = this.getDamageSources().playerAttack((PlayerEntity) (Object) this);
                    float g = this.getDamageAgainst(target, f, damageSource) - f;
                    float h = this.getAttackCooldownProgress(0.5F);
                    f *= 0.2F + h * h * 0.8F;
                    g *= h;
                    this.resetLastAttackedTicks();
                    if (target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && target instanceof ProjectileEntity) {
                        ProjectileEntity projectileEntity = (ProjectileEntity)target;
                        if (projectileEntity.deflect(ProjectileDeflection.REDIRECTED, this, this, true)) {
                            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, this.getSoundCategory());
                            return;
                        }
                    }

                    if (f > 0.0F || g > 0.0F) {
                        boolean bl = h > 0.9F;
                        boolean bl2;
                        if (this.isSprinting() && bl) {
                            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), ModSounds.ROSE_QUARTZ_ATTACK_KNOCKBACK, this.getSoundCategory(), 1.0F, 1.0F);
                            bl2 = true;
                        } else {
                            bl2 = false;
                        }

                        f += itemStack.getItem().getBonusAttackDamage(target, f, damageSource);
                        boolean bl3 = bl && this.fallDistance > 0.0F && !this.isOnGround() && !this.isClimbing() && !this.isTouchingWater() && !this.hasStatusEffect(StatusEffects.BLINDNESS) && !this.hasVehicle() && target instanceof LivingEntity && !this.isSprinting();
                        if (bl3) {
                            f *= 1.5F;
                        }

                        float i = f + g;
                        boolean bl4 = false;
                        double d = (double)(this.horizontalSpeed - this.prevHorizontalSpeed);
                        if (bl && !bl3 && !bl2 && this.isOnGround() && d < (double)this.getMovementSpeed()) {
                            ItemStack itemStack2 = this.getStackInHand(Hand.MAIN_HAND);
                            if (itemStack2.getItem() instanceof SwordItem) {
                                bl4 = true;
                            }
                        }

                        float j = 0.0F;
                        if (target instanceof LivingEntity) {
                            LivingEntity livingEntity = (LivingEntity)target;
                            j = livingEntity.getHealth();
                        }

                        Vec3d vec3d = target.getVelocity();
                        boolean bl5 = target.damage(damageSource, i);
                        if (bl5) {
                            float k = this.getKnockbackAgainst(target, damageSource) + (bl2 ? 1.0F : 0.0F);
                            if (k > 0.0F) {
                                if (target instanceof LivingEntity) {
                                    LivingEntity livingEntity2 = (LivingEntity)target;
                                    livingEntity2.takeKnockback((double)(k * 0.5F), (double)MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
                                } else {
                                    target.addVelocity((double)(-MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)) * k * 0.5F), 0.1, (double)(MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F)) * k * 0.5F));
                                }

                                this.setVelocity(this.getVelocity().multiply(0.6, (double)1.0F, 0.6));
                                this.setSprinting(false);
                            }

                            if (bl4) {
                                float l = 1.0F + (float)this.getAttributeValue(EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO) * f;

                                for(LivingEntity livingEntity3 : this.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand((double)1.0F, (double)0.25F, (double)1.0F))) {
                                    if (livingEntity3 != this && livingEntity3 != target && !this.isTeammate(livingEntity3) && (!(livingEntity3 instanceof ArmorStandEntity) || !((ArmorStandEntity)livingEntity3).isMarker()) && this.squaredDistanceTo(livingEntity3) < (double)9.0F) {
                                        float m = this.getDamageAgainst(livingEntity3, l, damageSource) * h;
                                        livingEntity3.takeKnockback((double)0.4F, (double)MathHelper.sin(this.getYaw() * ((float)Math.PI / 180F)), (double)(-MathHelper.cos(this.getYaw() * ((float)Math.PI / 180F))));
                                        livingEntity3.damage(damageSource, m);
                                        World var24 = this.getWorld();
                                        if (var24 instanceof ServerWorld) {
                                            ServerWorld serverWorld = (ServerWorld)var24;
                                            EnchantmentHelper.onTargetDamaged(serverWorld, livingEntity3, damageSource);
                                        }
                                    }
                                }

                                this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), ModSounds.ROSE_QUARTZ_ATTACK_SWEEP, this.getSoundCategory(), 1.0F, 1.0F);
                                this.spawnSweepAttackParticles();
                            }

                            if (target instanceof ServerPlayerEntity && target.velocityModified) {
                                ((ServerPlayerEntity)target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
                                target.velocityModified = false;
                                target.setVelocity(vec3d);
                            }

                            if (bl3) {
                                this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), ModSounds.ROSE_QUARTZ_ATTACK_CRIT, this.getSoundCategory(), 1.0F, 1.0F);
                                this.addCritParticles(target);
                            }

                            if (!bl3 && !bl4) {
                                if (bl) {
                                    this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), ModSounds.ROSE_QUARTZ_ATTACK, this.getSoundCategory(), 1.0F, 1.0F);
                                } else {
                                    this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, this.getSoundCategory(), 1.0F, 1.0F);
                                }
                            }

                            if (g > 0.0F) {
                                this.addEnchantedHitParticles(target);
                            }

                            this.onAttacking(target);
                            Entity entity = target;
                            if (target instanceof EnderDragonPart) {
                                entity = ((EnderDragonPart)target).owner;
                            }

                            boolean bl6 = false;
                            World var36 = this.getWorld();
                            if (var36 instanceof ServerWorld) {
                                ServerWorld serverWorld2 = (ServerWorld)var36;
                                if (entity instanceof LivingEntity) {
                                    LivingEntity livingEntity3 = (LivingEntity)entity;
                                    bl6 = itemStack.postHit(livingEntity3, (PlayerEntity) (Object) this);
                                }

                                EnchantmentHelper.onTargetDamaged(serverWorld2, target, damageSource);
                            }

                            if (!this.getWorld().isClient && !itemStack.isEmpty() && entity instanceof LivingEntity) {
                                if (bl6) {
                                    itemStack.postDamageEntity((LivingEntity)entity, (PlayerEntity) (Object) this);
                                }

                                if (itemStack.isEmpty()) {
                                    if (itemStack == this.getMainHandStack()) {
                                        this.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                    } else {
                                        this.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                                    }
                                }
                            }

                            if (target instanceof LivingEntity) {
                                float n = j - ((LivingEntity)target).getHealth();
                                this.increaseStat(Stats.DAMAGE_DEALT, Math.round(n * 10.0F));
                                if (this.getWorld() instanceof ServerWorld && n > 2.0F) {
                                    int o = (int)((double)n * (double)0.5F);
                                    ((ServerWorld)this.getWorld()).spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY((double)0.5F), target.getZ(), o, 0.1, (double)0.0F, 0.1, 0.2);
                                }
                            }

                            this.addExhaustion(0.1F);
                        } else {
                            this.getWorld().playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, this.getSoundCategory(), 1.0F, 1.0F);
                        }
                    }

                }
            }
            ci.cancel();
        }
    }
}
