package calculator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import exceptions.*;
import factory.*;
import commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Calculator {

    private static Logger calcLog = LogManager.getLogger("calcLog");


    private String filename;

    public Calculator(String file) {
        filename = file;
    }

    public void calculate() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' not found");
        }
        if (scanner != null) {
            Factory factory = new Factory();
            Context context = new Context();
            scanner.useDelimiter("\r\n");
            while (scanner.hasNext()) {
                String[] line = scanner.next().split(" ");
                if (line[0].charAt(0) == '#') {
                    continue;
                }
                Command command = null;
                try {
                    command = factory.make_command(line[0]);
                } catch (NullPointerException e) {
                    System.out.println("List of commands is empty");
                    calcLog.error("Exception: " + e.getMessage());
                    break;
                } catch (CommandNotFound e) {
                    System.out.println("Can't execute " + line[0] + ": " + e.getMessage());
                    calcLog.error("Exception: " + e.getMessage());
                }
                if (command != null) {
                    String[] args = new String[line.length - 1];
                    for (int i = 1; i < line.length; i++) {
                        args[i - 1] = line[i];
                    }
                    try {
                        command.execute(args, context);
                        calcLog.info("Executing line: " + Arrays.toString(line));
                    } catch (Exception e) {
                        System.out.println("Can't execute " + line[0] + ": " + e.getMessage());
                        calcLog.error("Exception: " + e.getMessage());
                    }
                }
            }
        }
    }
}
