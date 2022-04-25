package ru.nsu.ccfit.matus.task4.factory;

public class Car {

    private final Engine engine;
    private final CarBody carBody;
    private final Accessory accessory;
    private final long id;

    public Car(long id, Engine engine, CarBody carBody, Accessory accessory){
        //System.out.println("making car");
        this.id = id;
        this.engine = engine;
        this.carBody = carBody;
        this.accessory = accessory;
    }

    public int getId(){
        return (int)id;
    }

    public int getBodyId() {
        return carBody.getId();
    }

    public int getEngineId() {
        return engine.getId();
    }

    public int getAccessoryId() {
        return (int) accessory.getId();
    }
}
