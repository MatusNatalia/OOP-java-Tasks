package ru.nsu.ccfit.matus.task2.exceptions;

public class WrongConstant extends CommandException{
    public WrongConstant(String name, String value){
        super("can't assign value '" + value + "' to constant '" + name + "'");
    }
}
