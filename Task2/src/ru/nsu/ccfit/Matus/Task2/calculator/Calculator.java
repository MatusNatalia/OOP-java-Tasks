package ru.nsu.ccfit.matus.task2.calculator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.matus.task2.factory.Factory;
import ru.nsu.ccfit.matus.task2.commands.*;
import ru.nsu.ccfit.matus.task2.exceptions.CommandNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Calculator {

    private final static Logger log = LogManager.getLogger(Calculator.class);

    private Scanner scanner;

    public Calculator(InputStream in) {
        scanner = new Scanner(in);
        scanner.useDelimiter("\n");
    }

    public Calculator(String file) {
        try {
            scanner = new Scanner(new File(file));
            scanner.useDelimiter(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("File '" + file + "' not found");
        }
    }


    public void calculate() throws IOException {
        Factory factory = new Factory();
        Context context = new Context();
        //scanner.useDelimiter("\r\n");
        while (scanner.hasNext()) {
            String[] line = scanner.next().split(" ");
            if (line[0].charAt(0) == '#') {
                continue;
            }
            if (line[0].charAt(0) == 'q') {
                break;
            }
            Command command = null;
            try {
                command = factory.makeCommand(line[0].trim());
            } catch (CommandNotFound e) {
                System.out.println("Can't execute " + line[0] + ": " + e.getMessage());
                log.error("Exception: " + e.getMessage());
                continue;
            }
            String[] args = new String[line.length - 1];
            System.arraycopy(line, 1, args, 0, line.length - 1);
            try {
                command.execute(args, context);
                log.info("Executing line: " + Arrays.toString(line));
            } catch (Exception e) {
                System.out.println("Can't execute " + line[0] + ": " + e.getMessage());
                log.error("Exception: " + e.getMessage());
            }
        }
    }
}
