package com.revolvingmadness.sculk.language.builtins.classes.types.item;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClass;
import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.item.SwordItemInstance;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.FloatClassType;
import com.revolvingmadness.sculk.language.builtins.classes.types.data_types.StringClassType;
import com.revolvingmadness.sculk.language.interpreter.Interpreter;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class SwordItemClassType extends BuiltinClassType {
    public static final SwordItemClassType TYPE = new SwordItemClassType();

    private SwordItemClassType() {
        super("Item", ItemClassType.TYPE);
    }

    @Override
    public BuiltinClass call(Interpreter interpreter, List<BuiltinClass> arguments) {
        this.validateCall("init", arguments, List.of(StringClassType.TYPE, ToolMaterialClassType.TYPE, FloatClassType.TYPE, ItemSettingsClassType.TYPE));

        String id = arguments.get(0).toString();

        ToolMaterial material = arguments.get(1).toToolMaterial();

        float attackSpeed = (float) arguments.get(2).toFloat();

        BuiltinClass settings = arguments.get(3);

        return new SwordItemInstance(new Identifier(interpreter.identifier.getNamespace(), id), new SwordItem(material, (int) material.getAttackDamage(), attackSpeed, settings.toItemSettings()) {
            @Override
            public Text getName() {
                return Text.literal(settings.variableScope.getOrThrow("name").value.toString());
            }
        });
    }
}
