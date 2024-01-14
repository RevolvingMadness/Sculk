package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BlockPosInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.BooleanInstance;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class BlockPosType extends BuiltinType {
    public BlockPosType() {
        super("BlockPos");

        this.typeVariableScope.declare(List.of(TokenType.CONST), "equalTo", new EqualTo());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 3) {
            throw ErrorHolder.invalidArgumentCount("init", 3, arguments.size());
        }

        BuiltinClass xClass = arguments.get(0);
        BuiltinClass yClass = arguments.get(1);
        BuiltinClass zClass = arguments.get(2);

        if (!xClass.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(1, "init", new IntegerType(), xClass.getType());
        }

        if (!yClass.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(2, "init", new IntegerType(), yClass.getType());
        }

        if (!zClass.instanceOf(new IntegerType())) {
            throw ErrorHolder.argumentRequiresType(3, "init", new IntegerType(), zClass.getType());
        }

        long x = xClass.toInteger();
        long y = yClass.toInteger();
        long z = zClass.toInteger();

        return new BlockPosInstance(new BlockPos((int) x, (int) y, (int) z));
    }

    private static class EqualTo extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("equalTo", 1, arguments.size());
            }

            BuiltinClass other = arguments.get(0);

            if (other.instanceOf(new BlockPosType())) {
                return new BooleanInstance(other.toBlockPos().equals(this.boundClass.toBlockPos()));
            }

            return new BooleanInstance(false);
        }
    }
}
