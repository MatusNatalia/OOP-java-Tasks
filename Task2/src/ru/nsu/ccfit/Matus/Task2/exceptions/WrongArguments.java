package ru.nsu.ccfit.matus.task2.exceptions;

public class WrongArguments extends CommandException{
    public WrongArguments(){
        super("wrong number of arguments");
    }
}
