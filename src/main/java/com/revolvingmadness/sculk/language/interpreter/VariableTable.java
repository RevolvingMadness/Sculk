package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.instances.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.builtins.functions.Base64DecodeFunction;
import com.revolvingmadness.sculk.language.builtins.functions.Base64EncodeFunction;
import com.revolvingmadness.sculk.language.builtins.functions.PrintFunction;
import com.revolvingmadness.sculk.language.builtins.functions.TypeFunction;
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
        this.declare(List.of(TokenType.CONST), "BlockPos", new BlockPosType());
        this.declare(List.of(TokenType.CONST), "Blocks", new BlocksType());
        this.declare(List.of(TokenType.CONST), "Block", new BlockType());
        this.declare(List.of(TokenType.CONST), "Boolean", new BooleanType());
        this.declare(List.of(TokenType.CONST), "CommandResult", new CommandResultType());
        this.declare(List.of(TokenType.CONST), "Dictionary", new DictionaryType());
        this.declare(List.of(TokenType.CONST), "Difficulties", new DifficultiesEnumType());
        this.declare(List.of(TokenType.CONST), "Entity", new EntityType());
        this.declare(List.of(TokenType.CONST), "EntityTypes", new EntityTypesType());
        this.declare(List.of(TokenType.CONST), "EntityType", new EntityTypeType());
        this.declare(List.of(TokenType.CONST), "Enum", new EnumType());
        this.declare(List.of(TokenType.CONST), "Events", new EventsType());
        this.declare(List.of(TokenType.CONST), "Float", new FloatType());
        this.declare(List.of(TokenType.CONST), "Function", new FunctionType());
        this.declare(List.of(TokenType.CONST), "GameModes", new GameModesEnumType());
        this.declare(List.of(TokenType.CONST), "GameRules", new GameRulesType());
        this.declare(List.of(TokenType.CONST), "Module", new ModuleType());
        this.declare(List.of(TokenType.CONST), "Integer", new IntegerType());
        this.declare(List.of(TokenType.CONST), "ItemStack", new ItemStackType());
        this.declare(List.of(TokenType.CONST), "Items", new ItemsType());
        this.declare(List.of(TokenType.CONST), "Item", new ItemType());
        this.declare(List.of(TokenType.CONST), "List", new ListType());
        this.declare(List.of(TokenType.CONST), "LivingEntity", new LivingEntityType());
        this.declare(List.of(TokenType.CONST), "Method", new MethodType());
        this.declare(List.of(TokenType.CONST), "MinecraftServer", new MinecraftServerType());
        this.declare(List.of(TokenType.CONST), "Null", new NullType());
        this.declare(List.of(TokenType.CONST), "Object", new ObjectType());
        this.declare(List.of(TokenType.CONST), "PlayerEntity", new PlayerEntityType());
        this.declare(List.of(TokenType.CONST), "PlayerManager", new PlayerManagerType());
        this.declare(List.of(TokenType.CONST), "Resource", new ResourceType());
        this.declare(List.of(TokenType.CONST), "ServerPlayerEntity", new ServerPlayerEntityType());
        this.declare(List.of(TokenType.CONST), "String", new StringType());
        this.declare(List.of(TokenType.CONST), "Type", new TypeType());
        this.declare(List.of(TokenType.CONST), "Vec3d", new Vec3dType());
        this.declare(List.of(TokenType.CONST), "World", new WorldType());
    }

    private void declareFunctions() {
        this.declare(List.of(TokenType.CONST), "base64decode", new Base64DecodeFunction());
        this.declare(List.of(TokenType.CONST), "base64encode", new Base64EncodeFunction());
        this.declare(List.of(TokenType.CONST), "print", new PrintFunction());
        this.declare(List.of(TokenType.CONST), "type", new TypeFunction());
    }

    private void declareVariables() {
        this.declare(List.of(TokenType.CONST), "PI", new FloatInstance(Math.PI));
        this.declare(List.of(TokenType.CONST), "server", new MinecraftServerInstance(Sculk.server));
        this.declare(List.of(TokenType.CONST), "playerManager", new PlayerManagerInstance(Sculk.server.getPlayerManager()));
        this.declare(List.of(TokenType.CONST), "gameRules", new GameRulesInstance(Sculk.server.getGameRules()));
        this.declare(List.of(TokenType.CONST), "events", new EventsInstance());
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
