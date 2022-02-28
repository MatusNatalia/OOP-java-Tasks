
package ru.nsu.ccfit.matus.task2.factory;

import ru.nsu.ccfit.matus.task2.exceptions.CommandNotFound;
import ru.nsu.ccfit.matus.task2.commands.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Factory {

    private Properties commandList = new Properties();

    public Factory() throws IOException {
        String config = "Config.txt";
        InputStream stream = Factory.class.getResourceAsStream(config);
        if (stream == null) {
            System.out.println("File '" + config + "' not found");
        } else {
            commandList.load(stream);
        }
    }

    public Command makeCommand(String name) throws CommandNotFound {
        String className = commandList.getProperty(name);
        try {
            return (Command) Class.forName("ru.nsu.ccfit.matus.task2.commands." + className).newInstance();
        } catch (Exception e) {
            throw new CommandNotFound(name, e);
        }
    }
}

