import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.commands.Mult;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> mult.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        assertThrows(StackException.class, () -> mult.execute(args, context));
    }

}