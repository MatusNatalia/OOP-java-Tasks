package ru.nsu.ccfit.matus.task2.tests.commands;

import ru.nsu.ccfit.matus.task2.calculator.Context;
import ru.nsu.ccfit.matus.task2.commands.Plus;
import ru.nsu.ccfit.matus.task2.exceptions.StackException;
import ru.nsu.ccfit.matus.task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlusTest {
    private Plus plus = new Plus();
    @Test
    void testExecute() throws WrongArguments, StackException {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        plus.execute(args, context);
        assertEquals(5.0, context.peek());
    }

    @Test
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> plus.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        assertThrows(StackException.class, () -> plus.execute(args, context));
    }

}