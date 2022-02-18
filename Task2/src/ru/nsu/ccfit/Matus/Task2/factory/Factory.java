
package ru.nsu.ccfit.Matus.Task2.factory;

import ru.nsu.ccfit.Matus.Task2.exceptions.CommandNotFound;
import ru.nsu.ccfit.Matus.Task2.commands.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Factory {

    private HashMap<String, String> commandList;

    public Factory() {
        String config = null;
        InputStream stream = null;
        try {
            config = "Config.txt";
            stream = Factory.class.getResourceAsStream(config);
            if (stream == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("File '" + config + "' not found");
        } finally {
            if (stream != null) {
                Scanner scanner = new Scanner(stream);
                scanner.useDelimiter("\s|\r\n");
                commandList = new HashMap<>();
                while (scanner.hasNext()) {
                    commandList.put(scanner.next(), scanner.next());
                }
            }
        }
    }

    public Command make_command(String name) throws CommandNotFound {
        if (commandList == null || commandList.isEmpty()) {
            throw new NullPointerException();
        }
        else {
            String className = commandList.get(name);
            if (className == null) {
                throw new CommandNotFound(name);
            }
            else {
                Command command = null;
                try {
                    command = (Command) Class.forName("commands." + className).newInstance();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return command;
            }
        }
    }

}

