package ru.nsu.ccfit.matus.task4.factory;

public class CarBody {
    private final long id;

    public CarBody(long id){
        this.id = id;
    }

    public int getId() {
        return (int)id;
    }
}
