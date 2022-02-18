package ru.nsu.ccfit.Matus.Task2.exceptions;

public class WrongArguments extends CommandException{
    public WrongArguments(){
        super("wrong number of arguments");
    }
}
