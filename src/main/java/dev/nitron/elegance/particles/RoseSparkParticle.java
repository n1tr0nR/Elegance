package dev.nitron.elegance.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class RoseSparkParticle extends SpriteBillboardParticle {

    protected RoseSparkParticle(ClientWorld clientWorld, double d, double e, double f, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f);

        int textureIndex = this.random.nextInt(10);
        this.setSprite(spriteProvider.getSprite(textureIndex, 7));
        this.setMaxAge(30);
        this.scale = this.random.nextBoolean() ? 0.2F : 0.3F;
        this.setBoundingBoxSpacing(scale, scale);
        this.velocityMultiplier = 0;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        this.scale -= 0.01F;
        if(this.scale <= 0){
            this.markDead();
        }
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType effect, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new RoseSparkParticle(world, x, y, z, spriteProvider);
        }
    }
}
