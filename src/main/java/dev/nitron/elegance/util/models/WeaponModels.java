package dev.nitron.elegance.util.models;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;
import net.minecraft.util.Identifier;

public record WeaponModels(Identifier guiModel, Identifier handModel) {
    public static final Codec<WeaponModels> CODEC = RecordCodecBuilder.create(weaponModelsInstance -> weaponModelsInstance.group(
            Identifier.CODEC.fieldOf("gui_model").forGetter(WeaponModels::guiModel),
            Identifier.CODEC.fieldOf("hand_model").forGetter(WeaponModels::handModel)
    ).apply(weaponModelsInstance, WeaponModels::new));

    public static final PacketCodec<ByteBuf, WeaponModels> PACKET_CODEC = new PacketCodec<ByteBuf, WeaponModels>() {
        @Override
        public WeaponModels decode(ByteBuf buf) {
            Identifier guiModel = Identifier.of(StringEncoding.decode(buf, 256), StringEncoding.decode(buf, 256));
            Identifier handModel = Identifier.of(StringEncoding.decode(buf, 256), StringEncoding.decode(buf, 256));
            return new WeaponModels(guiModel, handModel);
        }

        @Override
        public void encode(ByteBuf buf, WeaponModels value) {
            StringEncoding.encode(buf, value.guiModel.getNamespace(), 256);
            StringEncoding.encode(buf, value.guiModel.getPath(), 256);
            StringEncoding.encode(buf, value.handModel.getNamespace(), 256);
            StringEncoding.encode(buf, value.handModel.getPath(), 256);
        }
    };

    public WeaponModels(Identifier guiModel){
        this(guiModel, guiModel);
    }

    public Identifier handModel(){
        return handModel == null ? guiModel : handModel;
    }
}
