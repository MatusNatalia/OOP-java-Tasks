package calculator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private Calculator calculator = new Calculator("C:\\Users\\nemat\\IdeaProjects\\Task2\\src\\calculator\\test.txt");

    @Test
    void testCalculate() {
        System.setOut(new PrintStream(output));
        calculator.calculate();
        assertEquals("-0,222222\n", output.toString());
        System.setOut(null);
    }
}