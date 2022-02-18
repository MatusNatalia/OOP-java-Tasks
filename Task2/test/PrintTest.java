import ru.nsu.ccfit.Matus.Task2.calculator.Context;
import ru.nsu.ccfit.Matus.Task2.commands.Print;
import ru.nsu.ccfit.Matus.Task2.exceptions.StackException;
import ru.nsu.ccfit.Matus.Task2.exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintTest {
    private OutputStream output = new ByteArrayOutputStream();
    private Print print = new Print();

    @Test
    void testExecute() throws WrongArguments, StackException {
        System.setOut(new PrintStream(output));
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        print.execute(args, context);
        String str = output.toString();
        assertTrue(output.toString().startsWith("2,000000"));
        System.setOut(null);
    }

    @Test
    void testAgrs() {
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        assertThrows(WrongArguments.class, () -> print.execute(args, context));
    }

    @Test
    void testStack() {
        Context context = new Context();
        String[] args = new String[] {};
        assertThrows(StackException.class, () -> print.execute(args, context));
    }

}