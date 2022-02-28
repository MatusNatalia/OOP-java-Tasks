package ru.nsu.ccfit.matus.task2.tests.commands;

import ru.nsu.ccfit.matus.task2.calculator.Context;
import ru.nsu.ccfit.matus.task2.commands.Minus;
import ru.nsu.ccfit.matus.task2.exceptions.StackException;
import ru.nsu.ccfit.matus.task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinusTest {
    private Minus minus = new Minus();
    @Test
    void testExecute() throws WrongArguments, StackException {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        minus.execute(args, context);
        assertEquals(1.0, context.peek());
    }

    @Test
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> minus.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        assertThrows(StackException.class, () -> minus.execute(args, context));
    }

}