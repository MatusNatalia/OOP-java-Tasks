package ru.nsu.ccfit.matus.task4.factory;


import static java.lang.Thread.sleep;

public class CarBodySupplier implements Runnable{
    private volatile int time = 3000;
    private final CarBodyStorage storage;
    private boolean stop = false;

    public CarBodySupplier(CarBodyStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        while(!stop){
            storage.put(new CarBody(storage.getId()));
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
