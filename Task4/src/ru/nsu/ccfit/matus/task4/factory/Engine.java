package ru.nsu.ccfit.matus.task4.factory;

public class Engine {
    private final long id;

    public Engine(long id){
        this.id = id;
    }

    public int getId() {
        return (int)id;
    }
}
