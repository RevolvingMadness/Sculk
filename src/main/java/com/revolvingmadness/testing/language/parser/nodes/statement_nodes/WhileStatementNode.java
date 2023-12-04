package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class WhileStatementNode implements StatementNode {
    public final ExpressionNode condition;
    public final List<StatementNode> body;

    public WhileStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        int loops = 0;
        long maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);

        while (condition.interpret(script).isTruthy()) {
            if (loops > maxLoops) {
                throw new StackOverflowError("Loop ran more than " + maxLoops + " times");
            }

            body.forEach(statement -> statement.interpret(script));
            loops++;
        }
    }
}
