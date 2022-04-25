package ru.nsu.ccfit.matus.task4.factory;


import static java.lang.Thread.*;

public class EngineSupplier implements Runnable{
    private volatile int time = 2000;
    private final EngineStorage storage;
    private boolean stop = false;

    public EngineSupplier(EngineStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        while(!stop){
            storage.put(new Engine(storage.getId()));
            try {
                sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeTime(int time){
        this.time = time;
    }

    public void stop() {
        stop = true;
        Thread.currentThread().interrupt();
    }
}
