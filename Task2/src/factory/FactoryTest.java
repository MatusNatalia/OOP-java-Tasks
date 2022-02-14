package factory;

import exceptions.CommandNotFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    private Factory factory = new Factory();

    @Test
    void testCommandNotFound() {
        try {
            factory.make_command("PUSHH");
            assert(false);
        } catch(CommandNotFound e){
            assert(true);
        }
    }
}