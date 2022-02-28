package ru.nsu.ccfit.matus.task2.exceptions;

public class CommandNotFound extends CommandException{
    public CommandNotFound(String name){
        super("command '" + name + "' not found");
    }

    public CommandNotFound(String name, Exception e) {
        super(name, e);
    }
}
