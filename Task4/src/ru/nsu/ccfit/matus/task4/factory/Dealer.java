package ru.nsu.ccfit.matus.task4.factory;

import static java.lang.Thread.sleep;

public class Dealer implements Runnable{
    private volatile int time = 10000;
    private final CarStorage storage;
    private final int id;
    private boolean stop = false;

    public Dealer(CarStorage storage, int id){
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run() {
        while(!stop){
            storage.get(id);
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
    }
}

