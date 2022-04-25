package ru.nsu.ccfit.matus.task4.factory;

public class Worker implements Runnable{

    private final CarStorage carStorage;
    private final EngineStorage engineStorage;
    private final CarBodyStorage carBodyStorage;
    private final AccessoryStorage accessoryStorage;

    public Worker(CarStorage carStorage, EngineStorage engineStorage, CarBodyStorage carBodyStorage, AccessoryStorage accessoryStorage){
        //System.out.println("making worker");
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.carBodyStorage = carBodyStorage;
        this.accessoryStorage = accessoryStorage;
    }

    @Override
    public void run() {
        //System.out.println("work");
        Car car = new Car(carStorage.getId(), engineStorage.get(), carBodyStorage.get(), accessoryStorage.get());
        carStorage.put(car);
    }


}
