package ru.nsu.ccfit.Matus.Task2.exceptions;

public class ConstantNotFound extends CommandException{
    public ConstantNotFound(String name){
        super("constant '" + name + "' not found");
    }
}
