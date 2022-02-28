package ru.nsu.ccfit.matus.task2.tests.factory;

import ru.nsu.ccfit.matus.task2.exceptions.CommandNotFound;
import org.junit.jupiter.api.Test;
import ru.nsu.ccfit.matus.task2.factory.Factory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FactoryTest {

    @Test
    void testCommandNotFound() {
        assertThrows(CommandNotFound.class, () -> {
            Factory factory = new Factory();
            factory.makeCommand("PUSHH");
        });
    }
    @Test
    void testCommandFound() throws IOException, CommandNotFound {
            Factory factory = new Factory();
            factory.makeCommand("+");
    }
}