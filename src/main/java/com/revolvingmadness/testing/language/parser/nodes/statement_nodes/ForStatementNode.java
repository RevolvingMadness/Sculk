package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.interpreter.errors.Break;
import com.revolvingmadness.testing.language.interpreter.errors.Continue;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class ForStatementNode implements StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;
    public final StatementNode initialization;
    public final StatementNode update;

    public ForStatementNode(StatementNode initialization, ExpressionNode condition, StatementNode update, List<StatementNode> body) {
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        int loops = 0;
        long maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);

        initialization.interpret(script);

        while_loop:
        while (condition.interpret(script).isTruthy()) {
            for (StatementNode statement : this.body) {
                try {
                    statement.interpret(script);
                } catch (Break ignored) {
                    break while_loop;
                } catch (Continue ignored) {
                    break;
                }
            }
            update.interpret(script);

            if (++loops > maxLoops) {
                throw new StackOverflowError("Loop ran more than " + maxLoops + " times");
            }
        }
    }
}
