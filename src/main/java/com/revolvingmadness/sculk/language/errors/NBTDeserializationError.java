package com.revolvingmadness.sculk.language.errors;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;

public class NBTDeserializationError extends Error {
    public NBTDeserializationError(BuiltinClassType type) {
        this("Type '" + type + "' does not support NBT de-serialization");
    }

    public NBTDeserializationError(String message) {
        super(message);
    }
}
