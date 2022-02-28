package ru.nsu.ccfit.matus.task2.exceptions;

public class ConstantNotFound extends CommandException{
    public ConstantNotFound(String name){
        super("constant '" + name + "' not found");
    }
}
