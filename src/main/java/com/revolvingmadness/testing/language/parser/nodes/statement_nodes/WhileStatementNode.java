package com.revolvingmadness.testing.language.parser.nodes.statement_nodes;

import com.revolvingmadness.testing.Testing;
import com.revolvingmadness.testing.gamerules.TestingGamerules;
import com.revolvingmadness.testing.language.interpreter.errors.Break;
import com.revolvingmadness.testing.language.interpreter.errors.Continue;
import com.revolvingmadness.testing.language.interpreter.errors.StackOverflowError;
import com.revolvingmadness.testing.language.parser.nodes.ScriptNode;
import com.revolvingmadness.testing.language.parser.nodes.expression_nodes.ExpressionNode;

import java.util.List;

public class WhileStatementNode implements StatementNode {
    public final List<StatementNode> body;
    public final ExpressionNode condition;

    public WhileStatementNode(ExpressionNode condition, List<StatementNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void interpret(ScriptNode script) {
        int loops = 0;
        long maxLoops = Testing.server.getGameRules().getInt(TestingGamerules.MAX_LOOPS);

        while_loop:
        while (this.condition.interpret(script).toBooleanType().value) {
            for (StatementNode statement : this.body) {
                try {
                    statement.interpret(script);
                } catch (Break ignored) {
                    break while_loop;
                } catch (Continue ignored) {
                    break;
                }
            }

            if (++loops > maxLoops) {
                throw new StackOverflowError("While-loop ran more than " + maxLoops + " times");
            }
        }
    }
}
