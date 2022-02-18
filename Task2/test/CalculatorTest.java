import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.Matus.Task2.calculator.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testCalculate() {
        OutputStream output = new ByteArrayOutputStream();
        Calculator calculator = new Calculator(CalculatorTest.class.getResourceAsStream("test.txt"));
        System.setOut(new PrintStream(output));
        calculator.calculate();
        assertTrue(output.toString().startsWith("-0,222222"));
        System.setOut(null);
    }
}