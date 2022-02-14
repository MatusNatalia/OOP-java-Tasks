package commands;

import exceptions.StackException;
import exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testAgrs() throws StackException{
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        try {
            minus.execute(args, context);
            assert(false);
        } catch (WrongArguments e) {
            assert(true);
        }
    }

    @Test
    void testStack() throws WrongArguments{
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        try {
            minus.execute(args, context);
            assert(false);
        } catch (StackException e) {
            assert(true);
        }
    }

}