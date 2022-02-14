package commands;

import exceptions.StackException;
import exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultTest {
    private Mult mult = new Mult();
    @Test
    void testExecute() throws WrongArguments, StackException {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        mult.execute(args, context);
        assertEquals(6.0, context.peek());
    }

    @Test
    void testAgrs() throws StackException{
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        try {
            mult.execute(args, context);
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
           mult.execute(args, context);
            assert(false);
        } catch (StackException e) {
            assert(true);
        }
    }

}