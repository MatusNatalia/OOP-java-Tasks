import ru.nsu.ccfit.Matus.Task2.exceptions.CommandNotFound;
import ru.nsu.ccfit.Matus.Task2.factory.*;
import org.junit.jupiter.api.Test;

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