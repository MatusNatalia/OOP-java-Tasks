package ru.nsu.ccfit.matus.task4.factory;

import ru.nsu.ccfit.matus.task4.threadpool.MyThreadPool;

public class Controller implements Runnable{

    private final MyThreadPool workers;
    private final EngineStorage engineStorage;
    private final CarBodyStorage carBodyStorage;
    private final AccessoryStorage accessoryStorage;
    private final CarStorage carStorage;
    private boolean stop = false;

    public Controller(MyThreadPool workers, EngineStorage engineStorage, CarBodyStorage carBodyStorage, AccessoryStorage accessoryStorage, CarStorage carStorage) {
        this.workers = workers;
        this.engineStorage = engineStorage;
        this.carBodyStorage = carBodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        while(!stop){
            synchronized (carStorage) {
                if (carStorage.getFreePlace() > 0) {
                    //System.out.println("controller add task");
                    workers.addTask(new Worker(carStorage, engineStorage, carBodyStorage, accessoryStorage));
                    //System.out.println("controller going to wait");
                }
                    try {
                        carStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public void stop() {
        stop = true;
        Thread.currentThread().interrupt();
    }
}

