package commands;

import exceptions.ConstantNotFound;
import exceptions.StackException;
import exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PushTest {
    private Push push = new Push();
    @Test
    void testExecute() throws WrongArguments, StackException, ConstantNotFound {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {"3.0"};
        push.execute(args, context);
        assertEquals(3.0, context.peek());
    }

    @Test
    void testAgrs() throws ConstantNotFound{
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        try {
            push.execute(args, context);
            assert(false);
        } catch (WrongArguments e) {
            assert(true);
        }
    }

    @Test
    void testStack() throws WrongArguments{
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {"a"};
        try {
            push.execute(args, context);
            assert(false);
        } catch (ConstantNotFound e) {
            assert(true);
        }
    }

}