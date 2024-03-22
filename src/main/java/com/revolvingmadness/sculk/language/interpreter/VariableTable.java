package com.revolvingmadness.sculk.language.interpreter;

import com.revolvingmadness.sculk.Sculk;
import com.revolvingmadness.sculk.language.ErrorHolder;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.GameRulesInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.MinecraftServerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.PlayerManagerInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.WorldInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.FloatInstance;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.NullInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.block.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.entity.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.item.*;
import com.revolvingmadness.sculk.language.builtins.classes.types.particle.ParticleClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.particle.ParticlesClassType;
import com.revolvingmadness.sculk.language.builtins.enums.AttributesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.DifficultiesEnumType;
import com.revolvingmadness.sculk.language.builtins.enums.EnumClassType;
import com.revolvingmadness.sculk.language.builtins.enums.GameModesEnumType;
import com.revolvingmadness.sculk.language.builtins.functions.*;
import com.revolvingmadness.sculk.language.errors.SyntaxError;
import com.revolvingmadness.sculk.language.errors.TypeError;
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
            throw ErrorHolder.cannotChangeValueOfVariableBecauseItIsAConstant(variable.name);
        }

        if (variable.isNonNull() && value.equals(new NullInstance())) {
            throw new TypeError("Variable '" + name + "' is non-null and was assigned a null value");
        }

        if (!value.instanceOf(NullClassType.TYPE)) {
            if (!value.instanceOf(variable.type)) {
                throw new SyntaxError("Cannot assign a value with type '" + value.type.name + "' to a variable that requires type '" + variable.type.name + "'");
            }
        }

        variable.value = value;
    }

    public void declare(List<TokenType> accessModifiers, BuiltinClassType type, String name, BuiltinClass value) {
        this.variableScopes.peek().declare(accessModifiers, type, name, value);
    }

    public void declare(List<TokenType> accessModifiers, String name, BuiltinClass value) {
        this.declare(accessModifiers, value.type, name, value);
    }

    private void declareClasses() {
        this.declare(List.of(TokenType.CONST), "BlockHitResult", BlockHitResultClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "BlockPos", BlockPosClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Blocks", BlocksClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Block", BlockClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Boolean", BooleanClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "CommandResult", CommandResultClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Dictionary", DictionaryClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Difficulties", DifficultiesEnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "Entity", EntityClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "EntityTypes", EntityTypesClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "EntityType", EntityTypeClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Enum", EnumClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Events", EventsClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Float", FloatClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Function", FunctionClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "GameModes", GameModesEnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "GameRules", GameRulesClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "GUI", GUIClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Module", ModuleClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Integer", IntegerClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Inventory", InventoryClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "ItemStack", ItemStackClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Items", ItemsClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Item", ItemClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "List", ListClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "LivingEntity", LivingEntityClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Method", MethodClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "MinecraftServer", MinecraftServerClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Null", NullClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Number", NumberClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Object", ObjectClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "PlayerEntity", PlayerEntityClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "PlayerManager", PlayerManagerClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "ServerPlayerEntity", ServerPlayerEntityClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "String", StringClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Type", TypeClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Vec3d", Vec3DClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "World", WorldClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Attributes", AttributesEnumType.TYPE);
        this.declare(List.of(TokenType.CONST), "ItemSettings", ItemSettingsClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "BlockSettings", BlockSettingsClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "SlabBlock", SlabBlockClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "WallBlock", WallBlockClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "StairBlock", StairBlockClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Keybinds", KeybindsClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Keys", KeysClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "SwordItem", SwordItemClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "ToolMaterial", ToolMaterialClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Particle", ParticleClassType.TYPE);
        this.declare(List.of(TokenType.CONST), "Particles", ParticlesClassType.TYPE);
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

        if (Sculk.server != null) {
            this.declare(List.of(TokenType.CONST), "server", new MinecraftServerInstance(Sculk.server));
            this.declare(List.of(TokenType.CONST), "playerManager", new PlayerManagerInstance(Sculk.server.getPlayerManager()));

            if (Sculk.server.getOverworld() != null) {
                this.declare(List.of(TokenType.CONST), "gameRules", new GameRulesInstance(Sculk.server.getGameRules()));
                this.declare(List.of(TokenType.CONST), "overworld", new WorldInstance(Sculk.server.getWorld(World.OVERWORLD)));
                this.declare(List.of(TokenType.CONST), "nether", new WorldInstance(Sculk.server.getWorld(World.NETHER)));
                this.declare(List.of(TokenType.CONST), "end", new WorldInstance(Sculk.server.getWorld(World.END)));
            } else {
                this.declare(List.of(TokenType.CONST), "gameRules", new NullInstance());
                this.declare(List.of(TokenType.CONST), "overworld", new NullInstance());
                this.declare(List.of(TokenType.CONST), "nether", new NullInstance());
                this.declare(List.of(TokenType.CONST), "end", new NullInstance());
            }
        } else {
            this.declare(List.of(TokenType.CONST), "server", new NullInstance());
            this.declare(List.of(TokenType.CONST), "playerManager", new NullInstance());
        }
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
