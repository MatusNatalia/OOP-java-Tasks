import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.commands.Push;
import ru.nsu.ccfit.Matus.Task2.exceptions.ConstantNotFound;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        assertThrows(WrongArguments.class, () -> push.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {"a"};
        assertThrows(ConstantNotFound.class, () -> push.execute(args, context));
    }

}