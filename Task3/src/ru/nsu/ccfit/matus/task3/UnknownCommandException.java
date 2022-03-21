package ru.nsu.ccfit.matus.task3;

public class UnknownCommandException extends Exception{
    public UnknownCommandException(){
        super("I don't understand you!");
    }
}
