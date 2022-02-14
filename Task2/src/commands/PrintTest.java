package commands;

import exceptions.StackException;
import exceptions.WrongArguments;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Print print = new Print();

    @Test
    void testExecute() throws WrongArguments, StackException {
        System.setOut(new PrintStream(output));
        Context context = new Context();
        context.push(2.0);
        String[] args = new String[] {};
        print.execute(args, context);
        assertEquals("2,000000\n", output.toString());
        System.setOut(null);
    }

    @Test
    void testAgrs() throws StackException{
        Context context = new Context();
        context.push(2.0);
        context.push(3.0);
        String[] args = new String[] {"2.0"};
        try {
            print.execute(args, context);
            assert(false);
        } catch (WrongArguments e) {
            assert(true);
        }
    }

    @Test
    void testStack() throws WrongArguments{
        Context context = new Context();
        String[] args = new String[] {};
        try {
            print.execute(args, context);
            assert(false);
        } catch (StackException e) {
            assert(true);
        }
    }

}