package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class GUIType extends BuiltinType {
    public static final GUIType TYPE = new GUIType();

    private GUIType() {
        super("GUI");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "getInventory", new GetInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setInventory", new SetInventory());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getTitle", new GetTitle());
        this.typeVariableScope.declare(List.of(), CallableType.TYPE, "onSlotClick", new OnSlotClick());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass nameClass = arguments.get(0);

        if (!nameClass.instanceOf(StringType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", nameClass.getType(), StringType.TYPE);
        }

        String name = nameClass.toString();

        return new GUIInstance(name);
    }

    private static class GetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getInventory", arguments, List.of());

            return new InventoryInstance(this.boundClass.toGUI().inventory);
        }
    }

    private static class GetTitle extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("getTitle", arguments, List.of());

            return new StringInstance(this.boundClass.toGUI().title);
        }
    }

    private static class OnSlotClick extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("onSlotClick", arguments, List.of(IntegerType.TYPE, IntegerType.TYPE, PlayerEntityType.TYPE));

            return new BooleanInstance(true);
        }
    }

    private static class SetInventory extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            this.validate("setInventory", arguments, List.of(InventoryType.TYPE));

            this.boundClass.toGUI().inventory = arguments.get(0).toInventory();

            return new NullInstance();
        }
    }
}
