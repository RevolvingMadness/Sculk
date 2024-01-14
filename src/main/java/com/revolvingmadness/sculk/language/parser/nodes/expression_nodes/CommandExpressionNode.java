package com.revolvingmadness.sculk.language.parser.nodes.expression_nodes;

import java.util.Objects;

public class CommandExpressionNode extends ExpressionNode {
    public final String command;

    public CommandExpressionNode(String command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CommandExpressionNode that = (CommandExpressionNode) o;
        return Objects.equals(this.command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.command);
    }
}
