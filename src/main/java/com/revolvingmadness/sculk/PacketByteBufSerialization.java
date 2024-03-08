package com.revolvingmadness.sculk;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

public class PacketByteBufSerialization {
    public static FabricBlockSettings readBlockSettings(PacketByteBuf buf) {
        FabricBlockSettings settings = FabricBlockSettings.create();

        settings.hardness(buf.readFloat());
        settings.resistance(buf.readFloat());
        settings.collidable(buf.readBoolean());
        settings.luminance(buf.readInt());
        settings.slipperiness(buf.readFloat());
        if (buf.readBoolean()) {
            settings.burnable();
        }
        settings.pistonBehavior(buf.readEnumConstant(PistonBehavior.class));
        if (!buf.readBoolean()) {
            settings.noBlockBreakParticles();
        }
        settings.instrument(buf.readEnumConstant(Instrument.class));
        if (buf.readBoolean()) {
            settings.requiresTool();
        }

        return settings;
    }

    public static Item readItemSettings(PacketByteBuf buf) {
        FabricItemSettings settings = new FabricItemSettings();

        settings.maxCount(buf.readInt());
        settings.maxDamage(buf.readInt());
        Text name = buf.readText();
        if (buf.readBoolean()) {
            settings.fireproof();
        }

        return new Item(settings) {
            @Override
            public Text getName() {
                return name;
            }
        };
    }

    public static InputUtil.Key readKey(PacketByteBuf buf) {
        InputUtil.Type type = buf.readEnumConstant(InputUtil.Type.class);
        int code = buf.readInt();

        return type.createFromCode(code);
    }

    public static void writeBlockSettings(PacketByteBuf buf, AbstractBlock.Settings settings) {
        buf.writeFloat(settings.hardness);
        buf.writeFloat(settings.resistance);
        buf.writeBoolean(settings.collidable);
        buf.writeInt(settings.luminance.applyAsInt(Blocks.AIR.getDefaultState()));
        buf.writeFloat(settings.slipperiness);
        buf.writeBoolean(settings.burnable);
        buf.writeEnumConstant(settings.pistonBehavior);
        buf.writeBoolean(settings.blockBreakParticles);
        buf.writeEnumConstant(settings.instrument);
        buf.writeBoolean(settings.toolRequired);
    }

    public static void writeItemSettings(PacketByteBuf buf, Item item) {
        buf.writeInt(item.getMaxCount());
        buf.writeInt(item.getMaxDamage());
        buf.writeText(item.getName());
        buf.writeBoolean(item.isFireproof());
    }

    public static void writeKey(InputUtil.Key key, PacketByteBuf buf) {
        buf.writeEnumConstant(key.getCategory());
        buf.writeInt(key.getCode());
    }
}
