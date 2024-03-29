package com.revolvingmadness.sculk.language.builtins.classes.types;

import com.revolvingmadness.sculk.language.builtins.classes.BuiltinClassType;
import com.revolvingmadness.sculk.language.builtins.classes.instances.data_types.IntegerInstance;
import com.revolvingmadness.sculk.language.lexer.TokenType;

import java.util.List;

public class KeysClassType extends BuiltinClassType {
    public static final KeysClassType TYPE = new KeysClassType();

    private KeysClassType() {
        super("Keys");

        this.variableScope.declare(List.of(TokenType.CONST), "KEY_SPACE", new IntegerInstance(32));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_APOSTROPHE", new IntegerInstance(39));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_COMMA", new IntegerInstance(44));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_MINUS", new IntegerInstance(45));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_PERIOD", new IntegerInstance(46));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_SLASH", new IntegerInstance(47));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_0", new IntegerInstance(48));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_1", new IntegerInstance(49));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_2", new IntegerInstance(50));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_3", new IntegerInstance(51));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_4", new IntegerInstance(52));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_5", new IntegerInstance(53));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_6", new IntegerInstance(54));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_7", new IntegerInstance(55));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_8", new IntegerInstance(56));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_9", new IntegerInstance(57));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_SEMICOLON", new IntegerInstance(59));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_EQUAL", new IntegerInstance(61));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_A", new IntegerInstance(65));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_B", new IntegerInstance(66));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_C", new IntegerInstance(67));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_D", new IntegerInstance(68));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_E", new IntegerInstance(69));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F", new IntegerInstance(70));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_G", new IntegerInstance(71));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_H", new IntegerInstance(72));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_I", new IntegerInstance(73));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_J", new IntegerInstance(74));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_K", new IntegerInstance(75));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_L", new IntegerInstance(76));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_M", new IntegerInstance(77));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_N", new IntegerInstance(78));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_O", new IntegerInstance(79));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_P", new IntegerInstance(80));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_Q", new IntegerInstance(81));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_R", new IntegerInstance(82));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_S", new IntegerInstance(83));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_T", new IntegerInstance(84));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_U", new IntegerInstance(85));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_V", new IntegerInstance(86));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_W", new IntegerInstance(87));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_X", new IntegerInstance(88));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_Y", new IntegerInstance(89));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_Z", new IntegerInstance(90));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT_BRACKET", new IntegerInstance(91));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_BACKSLASH", new IntegerInstance(92));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT_BRACKET", new IntegerInstance(93));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_GRAVE_ACCENT", new IntegerInstance(96));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_WORLD_1", new IntegerInstance(161));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_WORLD_2", new IntegerInstance(162));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_ESCAPE", new IntegerInstance(256));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_ENTER", new IntegerInstance(257));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_TAB", new IntegerInstance(258));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_BACKSPACE", new IntegerInstance(259));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_INSERT", new IntegerInstance(260));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_DELETE", new IntegerInstance(261));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT", new IntegerInstance(262));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT", new IntegerInstance(263));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_DOWN", new IntegerInstance(264));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_UP", new IntegerInstance(265));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_PAGE_UP", new IntegerInstance(266));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_PAGE_DOWN", new IntegerInstance(267));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_HOME", new IntegerInstance(268));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_END", new IntegerInstance(269));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_CAPS_LOCK", new IntegerInstance(280));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_SCROLL_LOCK", new IntegerInstance(281));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_NUM_LOCK", new IntegerInstance(282));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_PRINT_SCREEN", new IntegerInstance(283));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_PAUSE", new IntegerInstance(284));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F1", new IntegerInstance(290));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F2", new IntegerInstance(291));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F3", new IntegerInstance(292));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F4", new IntegerInstance(293));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F5", new IntegerInstance(294));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F6", new IntegerInstance(295));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F7", new IntegerInstance(296));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F8", new IntegerInstance(297));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F9", new IntegerInstance(298));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F10", new IntegerInstance(299));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F11", new IntegerInstance(300));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F12", new IntegerInstance(301));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F13", new IntegerInstance(302));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F14", new IntegerInstance(303));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F15", new IntegerInstance(304));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F16", new IntegerInstance(305));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F17", new IntegerInstance(306));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F18", new IntegerInstance(307));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F19", new IntegerInstance(308));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F20", new IntegerInstance(309));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F21", new IntegerInstance(310));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F22", new IntegerInstance(311));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F23", new IntegerInstance(312));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F24", new IntegerInstance(313));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_F25", new IntegerInstance(314));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_0", new IntegerInstance(320));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_1", new IntegerInstance(321));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_2", new IntegerInstance(322));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_3", new IntegerInstance(323));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_4", new IntegerInstance(324));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_5", new IntegerInstance(325));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_6", new IntegerInstance(326));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_7", new IntegerInstance(327));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_8", new IntegerInstance(328));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_9", new IntegerInstance(329));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_DECIMAL", new IntegerInstance(330));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_DIVIDE", new IntegerInstance(331));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_MULTIPLY", new IntegerInstance(332));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_SUBTRACT", new IntegerInstance(333));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_ADD", new IntegerInstance(334));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_ENTER", new IntegerInstance(335));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_KP_EQUAL", new IntegerInstance(336));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT_SHIFT", new IntegerInstance(340));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT_CONTROL", new IntegerInstance(341));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT_ALT", new IntegerInstance(342));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_LEFT_SUPER", new IntegerInstance(343));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT_SHIFT", new IntegerInstance(344));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT_CONTROL", new IntegerInstance(345));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT_ALT", new IntegerInstance(346));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_RIGHT_SUPER", new IntegerInstance(347));
        this.variableScope.declare(List.of(TokenType.CONST), "KEY_MENU", new IntegerInstance(348));
    }
}
