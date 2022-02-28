package ru.nsu.ccfit.matus.task2.tests.calculator;

import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.matus.task2.calculator.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testCalculate() throws IOException {
        OutputStream output = new ByteArrayOutputStream();
        Calculator calculator = new Calculator(CalculatorTest.class.getResourceAsStream("test.txt"));
        System.setOut(new PrintStream(output));
        calculator.calculate();
        assertTrue(output.toString().startsWith("3,000000"), "3,000000 vs "+output.toString());
        System.setOut(null);
    }
}