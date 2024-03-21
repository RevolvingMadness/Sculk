package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.*;
import com.revolvingmadness.sculk.language.errors.NBTDeserializationError;
import com.revolvingmadness.sculk.language.errors.TypeError;
import net.minecraft.nbt.*;

import java.util.*;

public abstract class NBTBuiltinClass extends BuiltinClass {
    public NBTBuiltinClass(BuiltinClassType type) {
        super(type);
    }

    public static NBTBuiltinClass fromNbtElement(NbtElement result) {
        if (result == null) {
            throw new RuntimeException("Result cannot be null");
        }

        if (result instanceof NbtByteArray nbtByteArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtByteArray.forEach(nbtByte -> list.add(NBTBuiltinClass.fromNbtElement(nbtByte)));

            return new ListInstance(list);
        } else if (result instanceof NbtIntArray nbtIntArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtIntArray.forEach(nbtInt -> list.add(NBTBuiltinClass.fromNbtElement(nbtInt)));

            return new ListInstance(list);
        } else if (result instanceof NbtList nbtList) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtList.forEach(nbtElement -> list.add(NBTBuiltinClass.fromNbtElement(nbtElement)));

            return new ListInstance(list);
        } else if (result instanceof NbtLongArray nbtLongArray) {
            List<BuiltinClass> list = new ArrayList<>();

            nbtLongArray.forEach(nbtLong -> list.add(NBTBuiltinClass.fromNbtElement(nbtLong)));

            return new ListInstance(list);
        } else if (result instanceof NbtByte nbtByte) {
            return new IntegerInstance(nbtByte.byteValue());
        } else if (result instanceof NbtDouble nbtDouble) {
            return new FloatInstance(nbtDouble.doubleValue());
        } else if (result instanceof NbtFloat nbtFloat) {
            return new FloatInstance(nbtFloat.floatValue());
        } else if (result instanceof NbtInt nbtInt) {
            return new IntegerInstance(nbtInt.intValue());
        } else if (result instanceof NbtLong nbtLong) {
            return new IntegerInstance(nbtLong.longValue());
        } else if (result instanceof NbtShort nbtShort) {
            return new IntegerInstance(nbtShort.shortValue());
        } else if (result instanceof NbtCompound nbtCompound) {
            Map<BuiltinClass, BuiltinClass> compound = new HashMap<>();

            Set<String> keys = nbtCompound.getKeys();

            keys.forEach(key -> {
                NBTBuiltinClass value = NBTBuiltinClass.fromNbtElement(nbtCompound.get(key));

                compound.put(new StringInstance(key), value);
            });

            return new DictionaryInstance(compound);
        } else if (result instanceof NbtString nbtString) {
            return new StringInstance(nbtString.asString());
        }

        throw new TypeError("Cannot convert nbt element '" + result + "' to class");
    }

    public BuiltinClass fromNBT(NBTBuiltinClass element) {
        if (element instanceof BooleanInstance booleanInstance) {
            return this.fromNBTBoolean(booleanInstance);
        }

        if (element instanceof DictionaryInstance dictionaryInstance) {
            return this.fromNBTDictionary(dictionaryInstance);
        }

        if (element instanceof FloatInstance floatInstance) {
            return this.fromNBTFloat(floatInstance);
        }

        if (element instanceof IntegerInstance integerInstance) {
            return this.fromNBTInteger(integerInstance);
        }

        if (element instanceof ListInstance listInstance) {
            return this.fromNBTList(listInstance);
        }

        if (element instanceof StringInstance stringInstance) {
            return this.fromNBTString(stringInstance);
        }

        throw Sculk.unreachable();
    }

    public BuiltinClass fromNBTBoolean(BooleanInstance boolean_) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'Boolean'");
    }

    public BuiltinClass fromNBTDictionary(DictionaryInstance dictionary) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'Dictionary'");
    }

    public BuiltinClass fromNBTFloat(FloatInstance float_) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'Float'");
    }

    public BuiltinClass fromNBTInteger(IntegerInstance integer) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'Integer'");
    }

    public BuiltinClass fromNBTList(ListInstance list) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'List'");
    }

    public BuiltinClass fromNBTString(StringInstance string) {
        throw new NBTDeserializationError("Type '" + this.type + "' does not support de-serializing from type 'String'");
    }

    public boolean isPrimaryNBT() {
        return this instanceof BooleanInstance || this instanceof DictionaryInstance || this instanceof FloatInstance || this instanceof IntegerInstance || this instanceof ListInstance || this instanceof StringInstance;
    }

    public abstract NbtElement toNBTElement();
}
