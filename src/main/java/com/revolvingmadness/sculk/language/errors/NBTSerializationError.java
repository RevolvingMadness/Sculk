package com.revolvingmadness.sculk.language.errors;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTSerializationError extends Error {
    public NBTSerializationError(BuiltinClassType type) {
        super("Type '" + type + "' does not support NBT serialization");
    }

    public NBTSerializationError(String message) {
        super(message);
    }
}
