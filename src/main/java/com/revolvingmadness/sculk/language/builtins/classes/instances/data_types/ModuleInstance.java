package com.revolvingmadness.sculk.language.builtins.classes.instances.data_types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.ModuleType;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;

public class ModuleInstance extends BuiltinClass {
    public ModuleInstance(VariableScope variableScope) {
        super(ModuleType.TYPE, variableScope);
    }
}
