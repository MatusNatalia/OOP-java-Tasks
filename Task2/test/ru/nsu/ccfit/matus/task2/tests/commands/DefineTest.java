package ru.nsu.ccfit.matus.task2.tests.commands;

import ru.nsu.ccfit.matus.task2.calculator.Context;
import ru.nsu.ccfit.matus.task2.commands.Define;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.matus.task2.exceptions.CommandException;
import ru.nsu.ccfit.matus.task2.exceptions.WrongArguments;
import ru.nsu.ccfit.matus.task2.exceptions.WrongConstant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefineTest {
    private Define define = new Define();
    @Test
    void testExecute() throws CommandException {
        Context context = new Context();
        String[] args = new String[] {"a", "4"};
        define.execute(args, context);
        assertEquals(4, context.get("a"));
    }

    @Test
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> define.execute(args, context));
    }

    @Test
    void testConstant() {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {"a", "b"};
        assertThrows(WrongConstant.class, () -> define.execute(args, context));
    }
}