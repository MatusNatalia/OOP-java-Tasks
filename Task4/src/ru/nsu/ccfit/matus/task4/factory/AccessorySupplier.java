package ru.nsu.ccfit.matus.task4.factory;

import static java.lang.Thread.sleep;

public class AccessorySupplier implements Runnable{
    private volatile int time = 5000;
    private final AccessoryStorage storage;
    private boolean stop = false;

    public AccessorySupplier(AccessoryStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        while(!stop){
            storage.put(new Accessory(storage.getId()));
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
