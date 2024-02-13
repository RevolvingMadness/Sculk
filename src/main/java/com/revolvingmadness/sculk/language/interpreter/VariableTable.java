package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.builtins.functions.*;
import com.revolvingmadness.sculk.language.lexer.TokenType;
import net.minecraft.world.World;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Stack;

public class VariableTable {
    public final Stack<VariableScope> variableScopes;

    public VariableTable() {
        this.variableScopes = new Stack<>();
        this.variableScopes.add(new VariableScope());

        this.declareClasses();
        this.declareFunctions();
        this.declareVariables();
    }

    public void assign(String name, BuiltinClass value) {
        Optional<Variable> optionalVariable = this.getOptional(name);

        if (optionalVariable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        Variable variable = optionalVariable.get();

        if (variable.isConstant()) {
            throw ErrorHolder.cannotAssignValueToVariableBecauseItIsAConstant(variable.name);
        }

        variable.value = value;
    }

    public void declare(List<TokenType> accessModifiers, String name, BuiltinClass value) {
        this.variableScopes.peek().declare(accessModifiers, name, value);
    }

    private void declareClasses() {
        this.declare(List.of(TokenType.CONST), "BlockHitResult", BlockHitResultType.TYPE);
        this.declare(List.of(TokenType.CONST), "BlockPos", BlockPosType.TYPE);
        this.declare(List.of(TokenType.CONST), "Blocks", BlocksType.TYPE);
        this.declare(List.of(TokenType.CONST), "Block", BlockType.TYPE);
        this.declare(List.of(TokenType.CONST), "Boolean", BooleanType.TYPE);
        this.declare(List.of(TokenType.CONST), "CommandResult", CommandResultType.TYPE);
        this.declare(List.of(TokenType.CONST), "Dictionary", DictionaryType.TYPE);
        this.declare(List.of(TokenType.CONST), "Difficulties", DifficultiesEnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "Entity", EntityType.TYPE);
        this.declare(List.of(TokenType.CONST), "EntityTypes", EntityTypesType.TYPE);
        this.declare(List.of(TokenType.CONST), "EntityType", EntityTypeType.TYPE);
        this.declare(List.of(TokenType.CONST), "Enum", EnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "Events", EventsType.TYPE);
        this.declare(List.of(TokenType.CONST), "Float", FloatType.TYPE);
        this.declare(List.of(TokenType.CONST), "Function", FunctionType.TYPE);
        this.declare(List.of(TokenType.CONST), "GameModes", GameModesEnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "GameRules", GameRulesType.TYPE);
        this.declare(List.of(TokenType.CONST), "Module", ModuleType.TYPE);
        this.declare(List.of(TokenType.CONST), "Integer", IntegerType.TYPE);
        this.declare(List.of(TokenType.CONST), "ItemStack", ItemStackType.TYPE);
        this.declare(List.of(TokenType.CONST), "Items", ItemsType.TYPE);
        this.declare(List.of(TokenType.CONST), "Item", ItemType.TYPE);
        this.declare(List.of(TokenType.CONST), "List", ListType.TYPE);
        this.declare(List.of(TokenType.CONST), "LivingEntity", LivingEntityType.TYPE);
        this.declare(List.of(TokenType.CONST), "Method", MethodType.TYPE);
        this.declare(List.of(TokenType.CONST), "MinecraftServer", MinecraftServerType.TYPE);
        this.declare(List.of(TokenType.CONST), "Null", NullType.TYPE);
        this.declare(List.of(TokenType.CONST), "Object", ObjectType.TYPE);
        this.declare(List.of(TokenType.CONST), "PlayerEntity", PlayerEntityType.TYPE);
        this.declare(List.of(TokenType.CONST), "PlayerManager", PlayerManagerType.TYPE);
        this.declare(List.of(TokenType.CONST), "ServerPlayerEntity", ServerPlayerEntityType.TYPE);
        this.declare(List.of(TokenType.CONST), "String", StringType.TYPE);
        this.declare(List.of(TokenType.CONST), "Type", TypeType.TYPE);
        this.declare(List.of(TokenType.CONST), "Vec3d", Vec3dType.TYPE);
        this.declare(List.of(TokenType.CONST), "World", WorldType.TYPE);
    }

    private void declareFunctions() {
        this.declare(List.of(TokenType.CONST), "abs", new AbsFunction());
        this.declare(List.of(TokenType.CONST), "base64decode", new Base64DecodeFunction());
        this.declare(List.of(TokenType.CONST), "base64encode", new Base64EncodeFunction());
        this.declare(List.of(TokenType.CONST), "ceil", new CeilFunction());
        this.declare(List.of(TokenType.CONST), "floor", new FloorFunction());
        this.declare(List.of(TokenType.CONST), "print", new PrintFunction());
        this.declare(List.of(TokenType.CONST), "randomFloat", new RandomFloatFunction());
        this.declare(List.of(TokenType.CONST), "randomInteger", new RandomIntegerFunction());
        this.declare(List.of(TokenType.CONST), "type", new TypeFunction());
    }

    private void declareVariables() {
        this.declare(List.of(TokenType.CONST), "PI", new FloatInstance(Math.PI));
        this.declare(List.of(TokenType.CONST), "server", new MinecraftServerInstance(Sculk.server));
        this.declare(List.of(TokenType.CONST), "playerManager", new PlayerManagerInstance(Sculk.server.getPlayerManager()));
        this.declare(List.of(TokenType.CONST), "gameRules", new GameRulesInstance(Sculk.server.getGameRules()));
        this.declare(List.of(TokenType.CONST), "overworld", new WorldInstance(Sculk.server.getWorld(World.OVERWORLD)));
        this.declare(List.of(TokenType.CONST), "nether", new WorldInstance(Sculk.server.getWorld(World.NETHER)));
        this.declare(List.of(TokenType.CONST), "end", new WorldInstance(Sculk.server.getWorld(World.END)));
    }

    public void deleteOrThrow(String name) {
        ListIterator<VariableScope> variableScopeIterator = this.variableScopes.listIterator();

        while (variableScopeIterator.hasNext()) {
            variableScopeIterator.next();
        }

        while (variableScopeIterator.hasPrevious()) {
            VariableScope variableScope = variableScopeIterator.previous();

            if (variableScope.exists(name)) {
                variableScope.deleteOrThrow(name);
                return;
            }
        }

        throw ErrorHolder.variableHasNotBeenDeclared(name);
    }

    public void enterScope() {
        this.variableScopes.add(new VariableScope());
    }

    public VariableScope exitScope() {
        return this.variableScopes.pop();
    }

    private Optional<Variable> getOptional(String name) {
        ListIterator<VariableScope> variableScopeIterator = this.variableScopes.listIterator();

        while (variableScopeIterator.hasNext()) {
            variableScopeIterator.next();
        }

        while (variableScopeIterator.hasPrevious()) {
            VariableScope variableScope = variableScopeIterator.previous();

            Optional<Variable> variable = variableScope.getOptional(name);

            if (variable.isPresent()) {
                return variable;
            }
        }

        return Optional.empty();
    }

    public Variable getOrThrow(String name) {
        Optional<Variable> variable = this.getOptional(name);

        if (variable.isEmpty()) {
            throw ErrorHolder.variableHasNotBeenDeclared(name);
        }

        return variable.get();
    }
}
