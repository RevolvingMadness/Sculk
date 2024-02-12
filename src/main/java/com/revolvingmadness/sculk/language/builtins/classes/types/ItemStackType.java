package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinMethod;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class ItemStackType extends BuiltinType {
    public static final ItemStackType TYPE = new ItemStackType();

    private ItemStackType() {
        super("ItemStack");
        this.typeVariableScope.declare(List.of(TokenType.CONST), "decrement", new Decrement());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getCount", new GetCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getDamage", new GetDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getHolder", new GetHolder());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getItem", new GetItem());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxCount", new GetMaxCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getMaxDamage", new GetMaxDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getName", new GetName());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "getRepairCost", new GetRepairCost());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "hasEnchantments", new HasEnchantments());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "increment", new Increment());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDamageable", new IsDamageable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isDamaged", new IsDamaged());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isEnchantable", new IsEnchantable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isFood", new IsFood());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isInFrame", new IsInFrame());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "isStackable", new IsStackable());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setCount", new SetCount());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setDamage", new SetDamage());
        this.typeVariableScope.declare(List.of(TokenType.CONST), "setRepairCost", new SetRepairCost());
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        if (arguments.size() != 1) {
            throw ErrorHolder.invalidArgumentCount("init", 1, arguments.size());
        }

        BuiltinClass itemStackClass = arguments.get(0);

        if (!itemStackClass.instanceOf(ItemType.TYPE)) {
            throw ErrorHolder.argumentRequiresType(1, "init", ItemType.TYPE, itemStackClass.getType());
        }

        return new ItemStackInstance(itemStackClass.toItem().getDefaultStack());
    }

    private static class Decrement extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("decrement", 1, arguments.size());
            }

            BuiltinClass amountClass = arguments.get(0);

            if (amountClass.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "decrement", IntegerType.TYPE, amountClass.getType());
            }

            long amount = amountClass.toInteger();

            this.boundClass.toItemStack().decrement((int) amount);

            return new NullInstance();
        }
    }

    private static class GetCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItemStack().getCount());
        }
    }

    private static class GetDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getDamage", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItemStack().getDamage());
        }
    }

    private static class GetHolder extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getHolder", 0, arguments.size());
            }

            return new EntityInstance(this.boundClass.toItemStack().getHolder());
        }
    }

    private static class GetItem extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getItem", 0, arguments.size());
            }

            return new ItemInstance(this.boundClass.toItemStack().getItem());
        }
    }

    private static class GetMaxCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxCount", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItemStack().getMaxCount());
        }
    }

    private static class GetMaxDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getMaxDamage", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItemStack().getMaxDamage());
        }
    }

    private static class GetName extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getName", 0, arguments.size());
            }

            return new StringInstance(this.boundClass.toItemStack().getName().getString());
        }
    }

    private static class GetRepairCost extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("getRepairCost", 0, arguments.size());
            }

            return new IntegerInstance(this.boundClass.toItemStack().getRepairCost());
        }
    }

    private static class HasEnchantments extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("hasEnchantments", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().hasEnchantments());
        }
    }

    private static class Increment extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("increment", 1, arguments.size());
            }

            BuiltinClass amountClass = arguments.get(0);

            if (amountClass.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "increment", IntegerType.TYPE, amountClass.getType());
            }

            long amount = amountClass.toInteger();

            this.boundClass.toItemStack().increment((int) amount);

            return new NullInstance();
        }
    }

    private static class IsDamageable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDamageable", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isDamageable());
        }
    }

    private static class IsDamaged extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isDamaged", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isDamaged());
        }
    }

    private static class IsEnchantable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isEnchantable", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isEnchantable());
        }
    }

    private static class IsFood extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isFood", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isFood());
        }
    }

    private static class IsInFrame extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isInFrame", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isInFrame());
        }
    }

    private static class IsStackable extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 0) {
                throw ErrorHolder.invalidArgumentCount("isStackable", 0, arguments.size());
            }

            return new BooleanInstance(this.boundClass.toItemStack().isStackable());
        }
    }

    private static class SetCount extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setCount", 1, arguments.size());
            }

            BuiltinClass countClass = arguments.get(0);

            if (countClass.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setCount", IntegerType.TYPE, countClass.getType());
            }

            long count = countClass.toInteger();

            this.boundClass.toItemStack().setCount((int) count);

            return new NullInstance();
        }
    }

    private static class SetDamage extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setDamage", 1, arguments.size());
            }

            BuiltinClass damageClass = arguments.get(0);

            if (damageClass.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setDamage", IntegerType.TYPE, damageClass.getType());
            }

            long damage = damageClass.toInteger();

            this.boundClass.toItemStack().setDamage((int) damage);

            return new NullInstance();
        }
    }

    private static class SetRepairCost extends BuiltinMethod {
        @Override
        public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
            if (arguments.size() != 1) {
                throw ErrorHolder.invalidArgumentCount("setRepairCost", 1, arguments.size());
            }

            BuiltinClass repairCostClass = arguments.get(0);

            if (repairCostClass.instanceOf(IntegerType.TYPE)) {
                throw ErrorHolder.argumentRequiresType(1, "setRepairCost", IntegerType.TYPE, repairCostClass.getType());
            }

            long repairCost = repairCostClass.toInteger();

            this.boundClass.toItemStack().setRepairCost((int) repairCost);

            return new NullInstance();
        }
    }
}
