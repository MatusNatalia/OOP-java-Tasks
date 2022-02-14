package commands;

import exceptions.StackException;
import exceptions.WrongArguments;
import exceptions.WrongConstant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefineTest {
    private Define define = new Define();
    @Test
    void testExecute() throws WrongArguments, WrongConstant {
        Context context = new Context();
        String[] args = new String[] {"a", "4"};
        define.execute(args, context);
        assertEquals(4, context.get("a"));
    }

    @Test
    void testAgrs() throws WrongConstant{
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        try {
            define.execute(args, context);
            assert(false);
        } catch (WrongArguments e) {
            assert(true);
        }
    }

    @Test
    void testConstant() throws WrongArguments{
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {"a", "b"};
        try {
            define.execute(args, context);
            assert(false);
        } catch (WrongConstant e) {
            assert(true);
        }
    }
}