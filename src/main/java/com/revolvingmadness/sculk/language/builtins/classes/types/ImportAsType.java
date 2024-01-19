package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.interpreter.VariableScope;

public class ImportAsType extends BuiltinType {
    public ImportAsType(String importAs, VariableScope variableScope) {
        super(importAs);

        this.variableScope.addAll(variableScope);
    }
}
