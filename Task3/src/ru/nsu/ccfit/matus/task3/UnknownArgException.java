package ru.nsu.ccfit.matus.task3;

public class UnknownArgException extends Exception{
    public UnknownArgException(String arg){
        super("Unknown argument: " + arg + "./nnPlease type 't' for text mode or 'g' for graphic mode.");
    }
}
