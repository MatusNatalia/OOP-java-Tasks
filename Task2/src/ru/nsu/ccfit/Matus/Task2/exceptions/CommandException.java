package ru.nsu.ccfit.matus.task2.exceptions;

public class CommandException extends Exception{
    public CommandException(String message){
        super(message);
    }

    public CommandException(String name, Exception e) {
        super(name, e);
    }
}
