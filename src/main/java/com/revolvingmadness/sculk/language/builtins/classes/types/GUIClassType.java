package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.BooleanInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.StringInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.InventoryInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.IntegerClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.PlayerEntityClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class GUIClassType extends BuiltinClassType {
    public static final GUIClassType TYPE = new GUIClassType();

    private GUIClassType() {
        super("GUI");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "getInventory", new GetInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setInventory", new SetInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTitle", new GetTitle());
        this.typeVariableScope.declare(List.of(), CallableClassType.TYPE, "onSlotClick", new OnSlotClick());
        this.typeVariableScope.declare(List.of(), CallableClassType.TYPE, "onClose", new OnClose());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE));

        return new GUIInstance(arguments.get(0).toString());
    }

    private static class GetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getInventory", arguments, List.of());

            return new InventoryInstance(this.boundClass.toGUI().inventory);
        }
    }

    private static class GetTitle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("getTitle", arguments, List.of());

            return new StringInstance(this.boundClass.toGUI().title);
        }
    }

    private static class OnClose extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("onClose", arguments, List.of(PlayerEntityClassType.TYPE, GUIClassType.TYPE));

            return new NullInstance();
        }
    }

    private static class OnSlotClick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("onSlotClick", arguments, List.of(IntegerClassType.TYPE, IntegerClassType.TYPE, GUIClassType.TYPE, PlayerEntityClassType.TYPE));

            return new BooleanInstance(true);
        }
    }

    private static class SetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validateCall("setInventory", arguments, List.of(InventoryClassType.TYPE));

            this.boundClass.toGUI().inventory = arguments.get(0).toInventory();

            return new NullInstance();
        }
    }
}
