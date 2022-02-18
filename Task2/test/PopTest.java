import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.commands.Pop;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {
    private Pop pop = new Pop();
    @Test
    void testExecute() throws WrongArguments, StackException {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {};
        pop.execute(args, context);
        assertEquals(2.0, context.peek());
    }

    @Test
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> pop.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        String[] args = new String[] {};
        assertThrows(StackException.class, () -> pop.execute(args, context));
    }

}