package com.revolvingmadness.sculk.language.builtins.classes;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.*;
import com.revolvingmadness.sculk.language.errors.NBTDeserializationError;

public abstract class NBTBuiltinClassType extends BuiltinClassType {
    public NBTBuiltinClassType(String name) {
        super(name);
    }

    public NBTBuiltinClassType(String name, BuiltinClassType superClass) {
        super(name, superClass);
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
}
